package org.example.JunitTest;

import org.example.Controllers.Test;

import java.lang.reflect.Method;

/**
 * A utility class for running tests annotated with @Test.
 * Demonstrates how to invoke test methods and handle their results.
 */
public class JUnitEci {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the class name as an argument.");
            return;
        }

        try {

            Class<?> c = Class.forName(args[0]);

            int passed = 0;
            int failed = 0;


            Method[] methods = c.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {

                        method.invoke(null);
                        System.out.println("Test " + method.getName() + " passed.");
                        passed++;
                    } catch (Exception e) {

                        System.out.println("Test " + method.getName() + " failed: " + e.getCause().getMessage());
                        failed++;
                    }
                }
            }

            System.out.println("Total Passed: " + passed);
            System.out.println("Total Failed: " + failed);

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
