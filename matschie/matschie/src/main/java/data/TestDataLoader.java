package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestDataLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T loadTestData(String filePath, Class<T> clazz) {
        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from file: " + filePath, e);
        }
    }
}
