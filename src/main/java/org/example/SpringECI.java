package org.example;


import org.example.Controllers.GetMapping;
import org.example.Controllers.RequestParam;
import org.example.Controllers.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple Spring-like framework for educational purposes.
 */
public class SpringECI {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        Class<?> c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap<>();

        if (c.isAnnotationPresent(RestController.class)) {
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(GetMapping.class)) {
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }


        URL serviceurl = new URL("http://localhost:8080/App/greeting?name=Samuel Mahecha");
        String path = serviceurl.getPath();
        String query = serviceurl.getQuery();
        System.out.println("Path: " + path);
        System.out.println("Query: " + query);


        String serviceName = path.substring(4);
        System.out.println("Service Name: " + serviceName);


        Method ms = services.get(serviceName);
        if (ms != null) {
            Object[] argsToPass = extractArguments(ms, query); // Extraer parámetros de la consulta
            System.out.println("Respuesta servicio: " + ms.invoke(null, argsToPass));
        } else {
            System.out.println("Servicio no encontrado: " + serviceName);
        }
    }

    /**
     * Extracts method arguments from a query string based on method parameters and annotations.
     *
     * @param method the method to extract arguments for
     * @param query the query string from the URL
     * @return an array of arguments to pass to the method
     */
    private static Object[] extractArguments(Method method, String query) {
        Map<String, String> queryParams = parseQuery(query);
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestParam.class)) {
                RequestParam annotation = parameters[i].getAnnotation(RequestParam.class);
                String paramName = annotation.value();
                String value = queryParams.getOrDefault(paramName, annotation.defaultValue());
                args[i] = value;
            }
        }
        return args;
    }

    /**
     * Parses a query string into a map of key-value pairs.
     *
     * @param query the query string to parse
     * @return a map of query parameters
     */
    private static Map<String, String> parseQuery(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return queryParams;
    }
}
