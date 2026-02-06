package utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.StringWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Map;

public class RestClientWrapper {

    private Response sendRequest(String method, String endpoint, Map<String, String> headers, Object body) {

        StringWriter requestWriter = new StringWriter();
        StringWriter responseWriter = new StringWriter();

        try (
            PrintStream requestCapture = new PrintStream(new WriterOutputStreamAdapter(requestWriter), true);
            PrintStream responseCapture = new PrintStream(new WriterOutputStreamAdapter(responseWriter), true)
        ) {
            var requestSpec = RestAssured.given()
                    .filter(new RequestLoggingFilter(requestCapture))
                    .filter(new ResponseLoggingFilter(responseCapture))
                    .contentType(ContentType.JSON)
                    .headers(headers);

            if (body != null) {
                requestSpec.body(body);
            }

            Response response;
            switch (method.toUpperCase()) {
                case "GET":
                    response = requestSpec.when().get(endpoint);
                    break;
                case "POST":
                    response = requestSpec.when().post(endpoint);
                    break;
                case "PATCH":
                    response = requestSpec.when().patch(endpoint);
                    break;
                case "DELETE":
                    response = requestSpec.when().delete(endpoint);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }

            // Set request/response logs for reporting
            ApiLogger.setRequestLog(requestWriter.toString());
            ApiLogger.setResponseLog(responseWriter.toString());

            return response.then().extract().response();

        } catch (Exception e) {
            throw new RuntimeException("Error during REST API call", e);
        }
    }

    // Public methods
    public Response get(String endpoint, Map<String, String> headers) {
        return sendRequest("GET", endpoint, headers, null);
    }

    public Response post(String endpoint, Object body, Map<String, String> headers) {
        return sendRequest("POST", endpoint, headers, body);
    }

    public Response patch(String endpoint, Object body, Map<String, String> headers) {
        return sendRequest("PATCH", endpoint, headers, body);
    }

    public Response delete(String endpoint, Map<String, String> headers) {
        return sendRequest("DELETE", endpoint, headers, null);
    }

    /**
     * Adapter to replace deprecated WriterOutputStream.
     */
    private static class WriterOutputStreamAdapter extends java.io.OutputStream {
        private final Writer writer;

        public WriterOutputStreamAdapter(Writer writer) {
            this.writer = writer;
        }

        @Override
        public void write(int b) {
            try {
                writer.write(b);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void write(byte[] b, int off, int len) {
            try {
                writer.write(new String(b, off, len));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void flush() {
            try {
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() {
            try {
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
