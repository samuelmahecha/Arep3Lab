package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class HelloServiceTest {

    @Test
    public void testHello() {
        String response = HelloService.hello();
        assertEquals("Hello World", response);
    }

    @Test
    public void testTime() {
        String response = HelloService.time();
        assertTrue(response.startsWith("The current time is: "));
    }

    @Test
    public void testGreeting() {
        String response = HelloService.greeting("Samuel");
        assertEquals("Hello, Samuel!", response);
    }

    @Test
    public void testUUID() {
        String response = HelloService.uuid();
        assertTrue(response.startsWith("Your unique identifier is: "));
        String uuidString = response.substring("Your unique identifier is: ".length());
        assertDoesNotThrow(() -> UUID.fromString(uuidString));
    }

    @Test
    public void testBye() {
        String response = HelloService.bye();
        assertEquals("Bye!", response);
    }

    @Test
    public void testServeStaticFile() {
        String response = HelloService.serveStaticFile("index.html");
        assertTrue(response.startsWith("HTTP/1.1 200 OK\r\nContent-Type: text/html"));
    }

    @Test
    public void testServeStaticFileNotFound() {
        String response = HelloService.serveStaticFile("nonexistentfile.html");
        assertTrue(response.startsWith("HTTP/1.1 404 Not Found"));
    }
}
