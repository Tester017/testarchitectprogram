package utils;

public class ApiLogger {
    // ThreadLocal variables to store logs per test thread
    private static ThreadLocal<String> requestLog = new ThreadLocal<>();
    private static ThreadLocal<String> responseLog = new ThreadLocal<>();

    public static void setRequestLog(String log) {
        requestLog.set(log);
    }

    public static String getRequestLog() {
        return requestLog.get();
    }

    public static void setResponseLog(String log) {
        responseLog.set(log);
    }

    public static String getResponseLog() {
        return responseLog.get();
    }
}
