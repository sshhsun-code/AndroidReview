package com.review.sunqi.iamss.androidreview.ashmem_test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by sunqi on 2018/6/22.
 */

public abstract class ReflectUtil {

    private static final Logger LOG = Logger.getLogger(ReflectUtil.class.getName());


    public static Object invoke(Object target, String methodName, Object[] args) {
        try {
            Class<? extends Object> clazz = target.getClass();
            Method method = findMethod(clazz, methodName, args);
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Returns the field of the given class or null if it doesnt exist.
     */
    public static Field getField(String fieldName, Class<?> clazz) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (SecurityException e) {
        } catch (NoSuchFieldException e) {
            // for some reason getDeclaredFields doesnt search superclasses
            // (which getFields() does ... but that gives only public fields)
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getField(fieldName, superClass);
            }
        }
        return field;
    }


    private static Method findMethod(Class< ? extends Object> clazz, String methodName, Object[] args) {
        for (Method method : clazz.getDeclaredMethods()) {
            // TODO add parameter matching
            if ( method.getName().equals(methodName)
                    && matches(method.getParameterTypes(), args)
                    ) {
                return method;
            }
        }
        Class< ? > superClass = clazz.getSuperclass();
        if (superClass!=null) {
            return findMethod(superClass, methodName, args);
        }
        return null;
    }



    private static boolean matches(Class< ? >[] parameterTypes, Object[] args) {
        if ( (parameterTypes==null)
                || (parameterTypes.length==0)
                ) {
            return ( (args==null)
                    || (args.length==0)
            );
        }
        if ( (args==null)
                || (parameterTypes.length!=args.length)
                ) {
            return false;
        }
        for (int i=0; i<parameterTypes.length; i++) {
            if ( (args[i]!=null)
                    && (! parameterTypes[i].isAssignableFrom(args[i].getClass()))
                    ) {
                return false;
            }
        }
        return true;
    }
}