package com.example.administrator.day_10_10.utils;

import java.lang.reflect.ParameterizedType;

/**
 * 通过反射获取对象
 * Created by Administrator on 2016/10/13.
 */

public class ClassUtil {


    /**
     * 通过类对象获取第 i 个变量的对象，用于父类获取泛型对象
     * @param obj 类对象
     * @param i 第几个对象 , 注意：从0开始
     * @param <T> 返回对象
     * @return 成功返回对象，失败返回 null
     */
    public static <T> T getT(Object obj, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (obj.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
