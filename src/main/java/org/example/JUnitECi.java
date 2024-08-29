package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JUnitECi {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessError {
        Class c = Class.forName(args[0]);

        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                    System.out.println("Test " + method.getName() + " passed.");
                } catch (Exception e) {
                    System.out.println("Test " + method.getName() + " failed: " + e.getCause());
                }
            }
        }
    }
}
