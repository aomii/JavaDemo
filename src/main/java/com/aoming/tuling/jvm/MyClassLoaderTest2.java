package com.aoming.tuling.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 
 *
 * @param  // TODO: 打破双亲委派机制，模拟实现Tomcat的webappClassLoader加载自己war包应用内不同版本类实现相互共存与隔
 * 离
 * @author ao921 
 * @date 2022/3/29 18:21 
 * @return 
 */
public class MyClassLoaderTest2 {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }


        /**
         *
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         * @param name
         * @param resolve
         * @author ao921
         * @date 2022/3/29 18:23
         * @return java.lang.Class<?>
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        if (!name.startsWith("com.aoming.tuling.jvm")){
                            c=this.getParent().loadClass(name);
                        }else {
                            c = findClass(name);
                        }

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

    }




    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        Class clazz = classLoader.loadClass("com.aoming.tuling.jvm.User1");
         Object obj = clazz.newInstance();
         Method method= clazz.getDeclaredMethod("sout", null);
         method.invoke(obj, null);
         System.out.println(clazz.getClassLoader());

         System.out.println();
        MyClassLoader classLoader1 = new MyClassLoader("D:/test1");
        Class clazz1 = classLoader1.loadClass("com.aoming.tuling.jvm.User1");
        Object obj1 = clazz1.newInstance();
         Method method1= clazz1.getDeclaredMethod("sout1", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader());
        }
}