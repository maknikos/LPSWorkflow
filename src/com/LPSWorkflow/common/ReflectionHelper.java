package com.LPSWorkflow.common;

import java.lang.reflect.Field;

/**
 * Helper class for performing reflection operations
 */
public class ReflectionHelper {
    public static Object getHiddenField(Object obj, String fieldName) {
        Field f;
        Object result = null;
        try {
            // We use reflection to access hidden variables in JLPS library
            f = obj.getClass().getDeclaredField(fieldName);
            if (f != null) {
                f.setAccessible(true);
                result = f.get(obj);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
