package com.example.jbehave.lambdatest.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebDriverConfig {

    @Value("${app.lambdatest.username}")
    private String username;

    @Value("${app.lambdatest.accessKey}")
    private String accessKey;

    @Value("${app.lambdatest.gridUrl}")
    private String gridUrl;

    @Value("${app.lambdatest.platformName}")
    private String platformName;

    @Value("${app.lambdatest.browserVersion}")
    private String browserVersion;

    @Value("${app.lambdatest.project}")
    private String project;

    @Value("${app.lambdatest.build}")
    private String build;

    @Bean
    @Scope("prototype")   // ✅ Each scenario gets a fresh driver
    public WebDriver remoteWebDriver() throws Exception {
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("platformName", platformName);
        ltOptions.put("project", project);
        ltOptions.put("build", build);
        ltOptions.put("name", "Login Scenario");

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion(browserVersion);
        options.setCapability("LT:Options", ltOptions);

        String hub = String.format("https://%s:%s@%s", username, accessKey, gridUrl.replace("https://", ""));
        System.out.println("🔗 Connecting to LambdaTest hub: " + hub);

        RemoteWebDriver driver = new RemoteWebDriver(URI.create(hub).toURL(), options);
        System.out.println("✅ RemoteWebDriver session started: " + driver.getSessionId());
        return driver;
    }
}
