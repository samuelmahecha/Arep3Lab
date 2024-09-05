package org.example.JunitTest;

import org.example.Controllers.Test;

/**
 * A class used for testing purposes.
 * This class contains several static methods annotated with the {@link Test} annotation.
 * Each method is a unit test that is executed by the {@link JUnitEci} class.
 *
 * The class includes methods that are designed to either pass or fail to demonstrate the test functionality.
 *
 */
public class ClassToBeTested {

    /**
     * A test method that prints "ok." to the console.
     * This method is expected to pass.
     */
    @Test
    public static void m1() {
        System.out.println("ok.");
    }

    /**
     * A test method that throws an exception with the message "Error form m2".
     * This method is expected to fail.
     *
     * @throws Exception if an error occurs
     */
    @Test
    public static void m2() throws Exception {
        throw new Exception("Error form m2");
    }

    /**
     * A test method that prints "ok." to the console.
     * This method is expected to pass.
     */
    @Test
    public static void m3() {
        System.out.println("ok.");
    }

    /**
     * A test method that throws an exception with the message "Error from m4".
     * This method is expected to fail.
     *
     * @throws Exception if an error occurs
     */
    @Test
    public static void m4() throws Exception {
        throw new Exception("Error from m4");
    }

    /**
     * A test method that prints "ok." to the console.
     * This method is expected to pass.
     */
    @Test
    public static void m5() {
        System.out.println("ok.");
    }
}
