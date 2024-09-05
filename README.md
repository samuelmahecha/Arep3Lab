## Taller de Arquitecturas de Servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión

### Overview

This project involves developing a simple HTTP server in Java that can handle HTTP requests, serve static files, and manage RESTful services using POJOs. The server must be able to deliver HTML pages and images (such as PNG), and support a basic Inversion of Control (IoC) framework to instantiate and manage web service components. Additionally, the project includes implementing unit tests to ensure the functionality of the server and its components.

### Key Features

1. **Static File Serving**: The server can serve static files (HTML, CSS, JavaScript, images) from a designated directory.
   
2. **RESTful Service Handling**: The server supports HTTP GET requests and can route them to methods annotated with custom annotations (@GetMapping).

3. **Dynamic Content Generation**: It handles requests to generate dynamic content, such as time and unique identifiers.
   
4. **Inversion of Control (IoC)**: The server can instantiate and manage web service components annotated with @RestController.
   
5. **Base64 Image Encoding**: For image files, the server can encode them in Base64 and serve them embedded in HTML for display.

### Components

- **SimpleHttpServer**: The core server class responsible for handling HTTP requests, routing them to the appropriate service methods, and serving static files.
- **HelloService**: A sample service class annotated with @RestController, which includes various endpoints for testing.
- **RequestDetails**: A helper class used to parse HTTP requests, extract the path, and query parameters.
- **Custom Annotations**:
    * @RestController: Marks a class as a controller for handling web requests. 
    * @GetMapping: Maps HTTP GET requests to methods.
    * @RequestParam: Binds query parameters to method parameters.

## Project Setup

This guide outlines the steps to set up and run your project.

**Prerequisites**

Before proceeding, ensure you have the following software installed on your system:

* **Java** (version 1.8.0 or higher): Download and install Java from the official website: https://www.oracle.com/java/technologies/downloads/
    * Verify your installation by running:
        ```bash
        mvn -version
        ```
      You should see output similar to:

        ```
        Apache Maven 3.x.x (unique identifier)
        Maven home: /path/to/maven
        Java version: 1.8.0, vendor: Oracle Corporation
        Java home: /path/to/java/jdk1.8.0.jdk/Contents/Home
        ...
        ```
* **Git:** Install Git by following the instructions on the official Git website: https://git-scm.com/downloads
    * Verify your installation by running:
        ```bash
        git --version
        ```
      This should output your Git version, for example:

        ```
        git version 2.34.1
        ```

**Installing the Project**

1. **Clone the Repository:**
    ```bash
    https://github.com/samuelmahecha/Arep3Lab.git
    ```
2. **Navigate to the Project Directory:**
    ```bash
    cd Arep3Lab
    ```

**Building the Project**

1. **Compile and Package:**
    * Run the following command to build the project using Maven:
        ```bash
        mvn package
        ```
    * This will compile your code and create a JAR file in the `target` directory. You should see output similar to:

        ```
        [INFO] Building jar:(https://github.com/samuelmahecha/Lab1Arep.git-1.0-SNAPSHOT.jar
        [INFO] BUILD SUCCESS
        ```

**Running the Application and Test**

1. **Execute the JAR:**
    * Run the following command to run the JUnit tests:
        ```bash
        java -cp target/lab03Arep-1.0-SNAPSHOT.jar org.example.JunitTest.JUnitEci org.example.JunitTest.ClassToBeTested

        ```
    *The command runs JUnit tests from a specified JAR file using a test runner class, and the expected output is a summary of test results, including which tests passed or failed.
   
1. **Execute the JAR:**
    * Run the following command to run the SpringEci class:
        ```bash
        java -cp target/lab03Arep-1.0-SNAPSHOT.jar org.example.SpringECI org.example.HelloService

        ```
    *The command runs JUnit tests from a specified JAR file using a test runner class, and the expected output is a summary of test results, including which tests passed or failed.
   
1. **Execute the JAR:**
    * Run the following command to launch your application:
        ```bash
       java -cp target/lab03Arep-1.0-SNAPSHOT.jar org.example.SimpleHttpServer

        ```
    * Alternatively, run the SimpleWebServer class directly from your IDE or execute the compiled JAR file.
  
**Testing**

1. **Open your web browser:**
    * Navigate to `(http://localhost:8080/hello))` in your browser.
    * If everything is set up correctly, you should see "Hello World".
2. **Open your web browser:**
    * Navigate to `((http://localhost:8080/time))` in your browser.
    * If everything is set up correctly, you should see your current time.
3. **Open your web browser:**
    * Navigate to `((http://localhost:8080/greeting?name=Samuel))` in your browser.
    * If everything is set up correctly, you should see "Hello, Samuel!".
4. **Open your web browser:**
    * Navigate to `((http://localhost:8080/uuid))` in your browser.
    * If everything is set up correctly, you should see "Your unique identifier is: [UUID]".
5. **Open your web browser:**
    * Navigate to `((http://localhost:8080/dayofweek))` in your browser.
    * If everything is set up correctly, you should see "Today is: [Day of the Week]".
6. **Open your web browser:**
    * Navigate to `((http://localhost:8080/staticfile?file=example.png))` in your browser.
    * If everything is set up correctly, you should see an image. 
    
## Dependencies
- **Maven**: The project uses Maven to manage dependencies and build the project.
- **JUnit 4**: The project uses JUnit 4 for unit testing.

---------

## Usability and unit testing ###

### Unit Test Descriptions for `HelloService`

#### `testHello()`
- **Purpose**: Verifies that the `hello()` method returns the expected greeting `"Hello World"`.
- **Expected Behavior**: The returned string should match the expected greeting.

#### `testTime()`
- **Purpose**: Checks that the `time()` method returns a string that starts with `"The current time is: "`.
- **Expected Behavior**: The returned string should begin with the specified prefix.

#### `testGreeting()`
- **Purpose**: Ensures that the `greeting()` method correctly greets a given name.
- **Expected Behavior**: The returned string should contain the greeting `"Hello"` followed by the provided name.

#### `testUUID()`
- **Purpose**: Tests that the `uuid()` method generates a valid UUID.
- **Expected Behavior**: 
  - The returned string should start with `"Your unique identifier is: "`.
  - The remaining part of the string should be a valid UUID format.
  - The `UUID.fromString()` method should not throw an exception when parsing the UUID.

#### `testBye()`
- **Purpose**: Verifies that the `bye()` method returns the expected farewell message `"Bye!"`.
- **Expected Behavior**: The returned string should match the expected farewell.

#### `testServeStaticFile()`
- **Purpose**: Checks that the `serveStaticFile()` method correctly serves a static file.
- **Expected Behavior**: 
  - The returned string should start with the HTTP status code `200 OK`.
  - The returned string should include the content type for the served file (e.g., `"text/html"`).

#### `testServeStaticFileNotFound()`
- **Purpose**: Tests that the `serveStaticFile()` method handles the case where a file is not found.
- **Expected Behavior**: 
  - The returned string should start with the HTTP status code `404 Not Found`.
![image](https://github.com/user-attachments/assets/a44d3e64-afcd-4405-b9e3-8a7b02803783)


### Usability Test ###
      
**Get hello service**
![image](https://github.com/user-attachments/assets/02d7442e-4cb4-4059-83d8-dc0dba17821b)

---------
**Get JUnittest**
![image](https://github.com/user-attachments/assets/2d05e98c-53df-4238-8f23-b38469700269)

---------
**Example Static File**
![image](https://github.com/user-attachments/assets/00b22521-6ce5-462c-bb0d-9bba54c61bff)

---------
**Example Static File**
![image](https://github.com/user-attachments/assets/4a318fde-7e89-4ac0-b96b-5c776e1e4170)

---------

**Example Get Time**
![image](https://github.com/user-attachments/assets/c3d883f6-2f46-4f14-816a-bde2fd6792bf)

------------------


### Interaction Flow

***Request Handling***:

* The SimpleHttpServer listens for incoming HTTP requests on a specified port (e.g., 8080).
  When a request is received, the server parses the request to determine the path and query parameters.
  Based on the path, the server either invokes the appropriate method from HelloService or serves a static file from the resources directory.
  Dynamic Content Generation:

* For endpoints like /time, /uuid, and /greeting, the server generates dynamic content based on the current time, a unique identifier, or query parameters.
  The content is returned as an HTTP response with the appropriate headers and body.
  Static File Serving:

* For requests to paths that correspond to static files, the server reads the file from the resources directory.
  If the file is an image, it is encoded in Base64 and embedded in an HTML response for display.
  For other static files (e.g., HTML, CSS, JavaScript), the server serves them with the appropriate MIME type.
  
### Relationships Between Classes

***SimpleHttpServer and HelloService**:

* SimpleHttpServer interacts with HelloService by using reflection to discover and invoke methods annotated with @GetMapping.
  HelloService contains methods that handle various HTTP GET requests and generate responses.
  SimpleHttpServer and RequestDetails:

* RequestDetails is used by SimpleHttpServer to parse incoming requests and extract path and query parameters.
  HelloService and Custom Annotations:

* HelloService uses custom annotations like @RestController, @GetMapping, and @RequestParam to define RESTful endpoints and handle request parameters.
----------

## Generating Project Documentation

1. **Generate the Site**
    - Run the following command to generate the site documentation:
      ```sh
      mvn site
      ```

2. **Add Javadoc Plugin for Documentation**
    - Add the Javadoc plugin to the `reporting` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <reporting>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </reporting>
        ...
      </project>
      ```

    - To generate Javadoc as an independent element, add the plugin in the `build` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <build>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </build>
        ...
      </project>
      ```

3. **Generate Javadoc Commands**
    - Use the following commands to generate Javadocs:
      ```sh
      mvn javadoc:javadoc
      mvn javadoc:jar
      mvn javadoc:aggregate
      mvn javadoc:aggregate-jar
      mvn javadoc:test-javadoc
      mvn javadoc:test-jar
      mvn javadoc:test-aggregate
      mvn javadoc:test-aggregate-jar
      ```

---------

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.

----------
## Author
Jose Samuel Mahecha Alarcon - @samuelmahecha
