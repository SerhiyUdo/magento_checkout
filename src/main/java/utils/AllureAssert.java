package utils;

import com.epam.jdi.light.logger.AllureLogData;
import com.epam.jdi.light.logger.AllureLogger;
import org.testng.Assert;

import static com.epam.http.JdiHttpSettings.logger;

public class AllureAssert extends AllureLogger {

    public static void assertTrue(String stepDescription, boolean condition, String errorMessage) {
        assertEquals(stepDescription, condition, Boolean.TRUE, errorMessage);
    }

    public static void assertTrue(String stepDescription, boolean condition) {
        assertTrue(stepDescription, condition, null);
    }

    public static void assertTrue(boolean condition) {
        assertTrue("Assert true", condition, null);
    }

    public static void assertEquals(String stepDescription, Object actual, Object expected, String errorMessage) {
        logAllureStep(stepDescription + " (expected: " + expected + ", actual: " + actual + ")", actual.equals(expected));
        logger.step(stepDescription + " (expected: " + expected + ", actual: " + actual + ")");
        Assert.assertEquals(actual, expected, errorMessage);
    }

    private static void logAllureStep(String stepDescription, boolean condition) {
        String stepId = startStep(stepDescription);
        if (condition) {
            passStep(stepId);
        } else {
            String screenPath = null;
            try {
                screenPath = makeScreenshot("After test");
            } catch (Exception ex) {
                attachText("Screenshot failed", "text/plain", ex.getMessage());
            }
            failStep(stepId, new AllureLogData(screenPath, null, null));
        }
    }
}
