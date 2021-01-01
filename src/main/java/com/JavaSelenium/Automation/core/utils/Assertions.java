package com.JavaSelenium.Automation.core.utils;

import com.JavaSelenium.Automation.core.logger.Logs;
import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;

public class Assertions {

    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert;

    private SoftAssert getSoftAssert() {
        return softAssert;
    }
    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }


    public void hardAssertTrue(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription ;
        stepDescription = pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        hardAssert.assertTrue(pageAssert);
    }

    public void hardAssertEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage){
        boolean result = actualValue.equals(expectedValue);
        String stepDescription = result? strSuccessMessage: strErrorMessage;
        hardAssertEquals(actualValue, expectedValue, stepDescription);
    }

    @Step("{2}")
    private void hardAssertNotEquals(String actualValue, String expectedValue, String stepDescription){
        hardAssert.assertNotEquals(actualValue, expectedValue, stepDescription);
    }

    public void hardAssertNotEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage){
        boolean result = !actualValue.equals(expectedValue);
        String stepDescription = result? strSuccessMessage: strErrorMessage;
        logAssert(stepDescription);
        hardAssertNotEquals(actualValue, expectedValue, stepDescription);
    }

    @Step("{0}")
    public void logInformation(String stepDescription){
        Reporter.log(stepDescription);
    }

    public void hardAssertFalse(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        hardAssert.assertFalse(pageAssert);
    }

    public void hardAssertEquals(String actualValue, String expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription
                + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        hardAssert.assertEquals(actualValue,expectedValue);
    }

    public void softAssertTrue(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertTrue(pageAssert);
    }

    public void softAssertTrue(boolean pageAssert, String strSuccessMessage, String strErrorMessage){
        String stepDescription = pageAssert? strSuccessMessage:strErrorMessage;
        softAssertTrue(pageAssert, stepDescription);
    }

    public void hardAssertTrue(boolean pageAssert, String strSuccessMessage, String strErrorMessage){
        String stepDescription = pageAssert? strSuccessMessage:strErrorMessage;
        hardAssertTrue(pageAssert, stepDescription);
    }

    public void softAssertFalse(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertFalse(pageAssert);
    }

    public void softAssertFalse(boolean pageAssert, String strSuccessMessage, String strErrorMessage){
        String stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        softAssertFalse( pageAssert, stepDescription);
    }

   /* @Step("{2}")
    public void softAssertEquals(String actualValue, String expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = "Expected value '"+expectedValue+"', Retrieved value '"+actualValue+"' -- "+stepDescription;
        logAssert(actualValue.equals(expectedValue),stepDescription);
        getSoftAssert().assertEquals(actualValue,expectedValue);
    }*/

    @Step("{0}")
    public void addStepDescription(String stepDescription){
        logAssert(stepDescription);
    }

    public void softAssertEquals(String actualValue, String expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription
                + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertEquals(actualValue,expectedValue);
    }


    public void softAssertEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage){
        strSuccessMessage += actualValue;
        strErrorMessage += ". Expected value '"+expectedValue+"', Retrieved value '"+actualValue;
        String stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        softAssertEquals(actualValue, expectedValue, stepDescription);
    }

    public void assertAll(){
        getSoftAssert().assertAll();
    }

    private void logAssert(String stepDescription){
        Logs.info("*** " + stepDescription + "' ***");
    }

    public void softAssertEquals(int actualValue, int expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue == expectedValue? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        softAssertEquals(String.valueOf(actualValue), String.valueOf(expectedValue), stepDescription);
    }
}
