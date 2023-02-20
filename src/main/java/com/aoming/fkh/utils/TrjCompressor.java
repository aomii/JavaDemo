
package com.aoming.fkh.utils;




import com.aoming.fkh.entity.zj.Compress;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * @author aoming
 * @Date 2023/0/17
 */
public class TrjCompressor<E> {
    private Class<E> clazz;

    private int compressFieldCount=0;

    private List<Field> compressFields=new ArrayList<Field>();

    //默认 保留6位小数
    private int precision=6;
    private double factor;

    public TrjCompressor(Class<E> c) {
        factor = Math.pow(10, precision);
        init(c);
    }

    public TrjCompressor(int precision,Class<E> c) {
        factor = Math.pow(10, precision);
        init(c);
    }

    private void init(Class c) {
        this.clazz=c;
        Class targetClass=c;
        do {
            Field[] declaredFields = targetClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Compress.class)) {
                    checkTypeAndReturnDefault(field);
                    compressFields.add(field);
                }
            }
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);
        this.compressFieldCount=compressFields.size();
    }



    public  String encode(List<E> points) {
        log.info("轨迹条数{}",points.size());
        List<Long> output = new ArrayList<>();
        Object prev = getObject();
        for (Field field : compressFields) {
            Object defaultValue=checkTypeAndReturnDefault(field);
            try {
                RefelectUtils.setPropertyVal(field.getName(), prev, defaultValue);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < points.size(); i++) {
            if (i > 0) {
                prev = points.get(i - 1);
            }

            for (Field field : compressFields) {
                Object val;
                try {
                    val = RefelectUtils.getPropertyVal(field.getName(), points.get(i));
                    Object offsetVal = RefelectUtils.getPropertyVal(field.getName(), prev);
                    write(output, val, offsetVal);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return toASCII(output);
    }

    private Object checkTypeAndReturnDefault(Field field) {
        // 如果类型是Double
        if (field.getGenericType().toString().equals(
            "class java.lang.Double")) {
            return (Double)0.0;
        }else  if (field.getGenericType().toString().equals(
            "class java.lang.Integer")) {
            return (Integer)0;
        }else if (field.getGenericType().toString().equals(
            "class java.lang.Long")) {
            return (Long)0L;
        }else {
            throw new RuntimeException("压缩数据类型不对");
        }

    }

    public <E> List<E> decode(String trjCode) {
        long [] vals=new long[compressFields.size()];
        long [] valDs=new long[compressFields.size()];
        List points = new ArrayList<>();
        for (int i = 0; i < trjCode.length(); ) {
            Tuple tuple=null;
            Object point=getObject();
            for (int j = 0; j < compressFieldCount; j++) {
                tuple = read(trjCode, i);
                valDs[j] = tuple.result;
                i = tuple.index;
                vals[j] += valDs[j];
                try {
                    if (checkIsDouble(compressFields.get(j))) {
                        double val=(double) vals[j] / factor;
                        RefelectUtils.setPropertyVal(compressFields.get(j).getName(), point, val);
                    }else if (checkIsInteger(compressFields.get(j))){
                        int val=(int)vals[j];
                        RefelectUtils.setPropertyVal(compressFields.get(j).getName(), point, val);
                    }else {
                        RefelectUtils.setPropertyVal(compressFields.get(j).getName(), point, vals[j]);
                    }
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            points.add(point);
        }
        return points;
    }

    private Boolean checkIsDouble(Field field) {
        return  field.getGenericType().toString().equals(
            "class java.lang.Double");
    }

    private boolean checkIsInteger(Field field) {
        return  field.getGenericType().toString().equals(
            "class java.lang.Integer");
    }

    private Object getObject() {
        Object point = null;
        try {
            point = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return point;
    }

    private void write(List<Long> output, Object currValue, Object prevValue) {
        long currV, prevV;
        if (currValue instanceof Long) {
            currV = (Long) currValue;
            prevV = (Long) prevValue;
        } else if (currValue instanceof Double) {
            currV = getRound((Double) currValue * factor);
            prevV = getRound((Double) prevValue * factor);
        } else if (currValue instanceof Integer){
            currV = ((Integer)currValue).longValue();
            prevV = ((Integer)prevValue).longValue();
        } else {
            throw new IllegalArgumentException("The type parameters must be long or double!");
        }
        long offset = currV - prevV;
        offset <<= 1;
        if (offset < 0) {
            offset = ~offset;
        }
        while (offset >= 0x20) {
            output.add((0x20 | (offset & 0x1f)) + 63);
            offset >>= 5;
        }
        output.add((offset + 63));
    }

    private Tuple read(String s, int i) {
        long b = 0x20;
        long result = 0, shift = 0, comp = 0;
        while (b >= 0x20) {
            b = s.charAt(i) - 63;
            i++;
            result |= (b & 0x1f) << shift;
            shift += 5;
            comp = result & 1;
        }
        result >>= 1;
        if (comp == 1) {
            result = ~result;
        }
        return new Tuple(i, result);
    }

    private String toASCII(List<Long> nums) {

        StringBuilder result = new StringBuilder();
        for (long i : nums) {
            result.append((char) i);
        }
        return result.toString();
    }

    private long getRound(double x) {
        return (long) Math.copySign(Math.round(Math.abs(x)), x);
    }
}

class Tuple {
    long result;
    int index;

    Tuple(int index, long result) {
        this.index = index;
        this.result = result;
    }
}