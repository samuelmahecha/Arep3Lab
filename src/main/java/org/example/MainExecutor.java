package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainExecutor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalAccessError, InvocationTargetException {
        Class c = Class.forName(args[0]);

        Class[] mainParamTypes = {String[].class};

        Method main = c.getDeclaredMethod("main", mainParamTypes);

        String[] params = {args[1], args[2]};

        main.invoke(null, (Object) params);

    }
}