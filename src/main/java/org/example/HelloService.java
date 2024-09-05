package org.example;

import org.example.Controllers.GetMapping;
import org.example.Controllers.RequestParam;
import org.example.Controllers.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

/**
 * A simple REST controller that provides several endpoints.
 */
@RestController
public class HelloService {

    private static final String STATIC_FILES_DIR = "src/main/resources";


    /**
     * Handles GET requests to "/hello".
     *
     * @return a simple greeting message
     */
    @GetMapping("/hello")
    public static String hello() {
        return "Hello World";
    }

    /**
     * Handles GET requests to "/time".
     *
     * @return the current time
     */
    @GetMapping("/time")
    public static String time() {
        return "The current time is: " + java.time.LocalTime.now();
    }

    /**
     * Handles GET requests to "/greeting".
     *
     * @param name the name to greet
     * @return a greeting message
     */
    @GetMapping("/greeting")
    public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }


    /**
     * Handles GET requests to "/uuid".
     *
     * @return a unique identifier
     */
    @GetMapping("/uuid")
    public static String uuid() {
        return "Your unique identifier is: " + UUID.randomUUID().toString();
    }

    /**
     * Handles GET requests to "/bye".
     *
     * @return a goodbye message
     */
    @GetMapping("/bye")
    public static String bye() {
        return "Bye!";
    }

    /**
     * Handles GET requests to "/dayofweek".
     *
     * @return the current day of the week
     */
    @GetMapping("/dayofweek")
    public static String dayOfWeek() {
        return "Today is: " + java.time.LocalDate.now().getDayOfWeek();
    }

    /**
     * Handles GET requests to "/".
     * Serves the index.html file.
     *
     * @return the content of index.html
     */
    @GetMapping("/")
    public static String serveIndex() {
        return serveStaticFile("index.html");
    }

    /**
     * Handles GET requests to "/staticfile".
     * Serves the specified static file.
     *
     * @param fileName the name of the file to serve
     * @return the content of the specified file
     */
    @GetMapping("/staticfile")
    public static String serveStatic(@RequestParam(value = "file", defaultValue = "index.html") String fileName) {
        return serveStaticFile(fileName);
    }


    static String serveStaticFile(String fileName) {
        File file = new File(STATIC_FILES_DIR + File.separator + fileName);

        if (file.exists() && !file.isDirectory()) {
            try {
                String contentType = determineContentType(fileName);
                byte[] fileContent = Files.readAllBytes(file.toPath());

                if (contentType.startsWith("image")) {
                    String base64Image = Base64.getEncoder().encodeToString(fileContent);
                    String htmlResponse = "<!DOCTYPE html>\r\n"
                            + "<html>\r\n"
                            + "    <head>\r\n"
                            + "        <title>Image</title>\r\n"
                            + "    </head>\r\n"
                            + "    <body>\r\n"
                            + "        <center><img src=\"data:" + contentType + ";base64," + base64Image + "\" alt=\"image\"></center>\r\n"
                            + "    </body>\r\n"
                            + "</html>";
                    return "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: " + htmlResponse.length() + "\r\n\r\n" + htmlResponse;
                } else {
                    return "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-Length: " + fileContent.length + "\r\n\r\n" + new String(fileContent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "HTTP/1.1 500 Internal Server Error\r\n\r\nError reading file";
            }
        } else {
            return "HTTP/1.1 404 Not Found\r\n\r\nFile not found";
        }
    }

    private static String determineContentType(String fileName) {
        if (fileName.endsWith(".html")) {
            return "text/html";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        return "application/octet-stream";
    }
}
