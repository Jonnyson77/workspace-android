package com.java.demo.util;

public class Utils {
    public Class<?>[] getAllInterface(Class<?> clazz){
        //获取自身的所有接口
        Class<?>[] interSelf = clazz.getInterfaces();
        //递规调用getAllInterface获取超类的所有接口
        Class<?> superClazz = clazz.getSuperclass();
        Class<?>[] interParent = null;
        if (null != superClazz) {
            interParent = getAllInterface(superClazz);
        }
        //返回值
        if (interParent == null && interSelf != null){
            return interSelf;
        }else if (interParent == null && interSelf == null){
            return null;
        }else if (interParent != null && interSelf == null){
            return interParent;
        }else {
            final int length = interParent.length + interSelf.length;
            Class<?>[] result = new Class[length];
            System.arraycopy(interSelf,0,result,0,interSelf.length);
            System.arraycopy(interParent,0,result,interSelf.length,interParent.length);
            return result;
        }
    }

    public static void myPrint(Object o){
        System.out.println(String.valueOf(o));
    }
}
