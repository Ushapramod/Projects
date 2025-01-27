package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    private static RequestSpecification requestSpecification;
    private static Properties properties;

    /**
     * Returns a singleton instance of RequestSpecification with logging and base URI setup.
     *
     * @return RequestSpecification object
     * @throws IOException if an error occurs while reading the properties file or setting up the logs
     */
    public static RequestSpecification getRequestSpecification() throws IOException {
        // Check if the RequestSpecification is already initialized
        if (requestSpecification == null) {
            synchronized (Utils.class) { // Synchronize for thread-safety
                if (requestSpecification == null) {
                    // Load properties from the global properties file
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream("src/test/java/resources/global.properties");
                        properties = new Properties();
                        properties.load(fileInputStream);

                        // Create logs directory if it doesn't exist
                        File logsDir = new File("target/logs");
                        if (!logsDir.exists()) {
                            logsDir.mkdirs();
                        }

                        // Setup log output file (append mode)
                        PrintStream printStream = new PrintStream(new FileOutputStream("target/logs/logs.txt", true));

                        // Build the RequestSpecification
                        requestSpecification = new RequestSpecBuilder()
                                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                                
                                .setContentType(ContentType.JSON)
                                .build();
                    } catch (IOException e) {
                        throw new IOException("Failed to load properties or set up logging: " + e.getMessage(), e);
                    } finally {
                        // Ensure fileInputStream is closed
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    }
                }
            }
        }
        return requestSpecification;
    }

    /**
     * Loads a property value from the global properties file.
     *
     * @param key the key for the property
     * @return the property value as a String
     * @throws IOException if the properties file cannot be loaded
     */
    public static String getProperty(String key) throws IOException {
        if (properties == null) {
            synchronized (Utils.class) {
                if (properties == null) {
                    try (FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/global.properties")) {
                        properties = new Properties();
                        properties.load(fileInputStream);
                    }
                }
            }
        }
        return properties.getProperty(key);
    }
}
