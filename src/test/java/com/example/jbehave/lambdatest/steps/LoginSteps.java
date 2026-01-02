package com.example.jbehave.lambdatest.steps;

import org.jbehave.core.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@Component
public class LoginSteps {

    @Autowired
    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeScenario
    public void beforeScenario() {
        driver.manage().window().maximize(); // ✅ maximize window so video shows full page
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ltLog("🚀 Starting scenario: Valid login");
    }

    @AfterScenario
    public void tearDown() {
    	 if (driver != null) {
    	        ltLog("🛑 Closing WebDriver after scenario");
    	        driver.quit();
    	    }
    }

    @Given("the user is on the login page")
    public void openLoginPage() throws InterruptedException {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
//        Thread.sleep(1000); // ✅ short pause so LambdaTest video captures page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        ltLog("Navigated to login page and email field visible");
    }

    @When("the user enters valid credentials")
    public void enterCredentials() {
        driver.findElement(By.id("input-email")).sendKeys("ravi12345@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Ravil@1234");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        ltLog("Entered credentials and submitted login form");
    }

    @Then("the user should see the dashboard")
    public void verifyDashboard() {
        try {
            By heading = By.cssSelector("h2");
            By breadcrumbLast = By.cssSelector("ul.breadcrumb li:last-child");

            wait.until(ExpectedConditions.or(
                ExpectedConditions.textToBePresentInElementLocated(heading, "My Account"),
                ExpectedConditions.textToBePresentInElementLocated(breadcrumbLast, "Account")
            ));

            ltLog("✅ Dashboard verified (heading/breadcrumb matched)");
            ltMarkStatus("passed", "Login scenario completed successfully");
        } catch (TimeoutException e) {
            ltLog("❌ Dashboard verification timed out");
            ltMarkStatus("failed", "Dashboard not visible after login");
            throw e;
        }
    }

    // ---------- LambdaTest logging helpers ----------

    private void ltLog(String message) {
        try {
            ((JavascriptExecutor) driver).executeScript("lambda-comment=" + message);
        } catch (Exception ignored) { }
    }

    private void ltMarkStatus(String status, String reason) {
        try {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            ((JavascriptExecutor) driver).executeScript("lambda-comment=" + reason);
        } catch (Exception ignored) { }
    }
}
