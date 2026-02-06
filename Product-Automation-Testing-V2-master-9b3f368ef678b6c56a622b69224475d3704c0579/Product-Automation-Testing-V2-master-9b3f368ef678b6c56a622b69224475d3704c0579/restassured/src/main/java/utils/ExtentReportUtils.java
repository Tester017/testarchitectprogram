package utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;
import java.lang.reflect.Field;

public class ExtentReportUtils {
    public static void updateTestName(ExtentTest extentTest, String newName) {
        try {
            Field modelField = extentTest.getClass().getDeclaredField("model");
            modelField.setAccessible(true);
            Test testModel = (Test) modelField.get(extentTest);
            testModel.setName(newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
