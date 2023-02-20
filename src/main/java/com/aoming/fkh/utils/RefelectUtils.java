package com.aoming.fkh.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射操作类,通过bean工具类更简单的操作get、set方法
 * 
 * @author chinoukin
 *
 */
public class RefelectUtils {

	public static void setPropertyVal(String propertyName, Object s, Object newVal)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, s.getClass());
		Method setMethod = pd.getWriteMethod();
		setMethod.invoke(s, newVal);
	}

    public static Object getPropertyVal(String propertyName, Object s)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, s.getClass());
		Method getMethod = pd.getReadMethod();
		Object retVal = getMethod.invoke(s);
		return retVal;
	}
 
}