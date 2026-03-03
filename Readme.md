# JBehave Login Test with Selenium and TestMu AI (Formerly LambdaTest) 🌐

This project demonstrates Behavior-Driven Development (BDD) using **JBehave** and **Selenium WebDriver**, executed on the **TestMu AI cloud grid**. It automates a login scenario for a sample e-commerce site and generates HTML reports for test results.

---

## Prerequisites
- Java 17+ (tested with Java 21, compatible with Java 17)
- Spring Boot 3.3.4
- JBehave 5.0.0
- Selenium 4.19.1
- Maven 3.9+

## 🚀 Features

- ✅ BDD-style test flow using `.story` files
- ✅ Remote browser execution on TestMu AI
- ✅ HTML report generation via JBehave
- ✅ W3C-compliant capabilities for Selenium 4
- ✅ Beginner-friendly structure with reusable steps

---

## 📝 Project Structure

```
src/
└── test/
    ├── java/
    │   └── com/example/jbehave/lambdatest/
    │       ├── config/
    │       │   └── WebDriverConfig.java       # LambdaTest WebDriver bean
    │       ├── steps/
    │       │   └── LoginSteps.java            # Step definitions
    │       └── runner/
    │           └── JBehaveRunnerTest.java     # JBehave runner
    └── resources/
        └── stories/
            └── login.story                    # BDD story file
           
```


### Setup
### 1. Clone the Repository
```bash
git clone https://github.com/your-username/jbehave-login-test.git
cd jbehave-login-test
```

### 2. Configure TestMu AI Credentials

You can either:
- Set environment variables:
  ```bash
  set LT_USERNAME=rk76912
  set LT_ACCESS_KEY=your_access_key
  ```
- Or hardcode them inside `LoginSteps.java` (for quick testing only)

### 3. Install Dependencies
```bash
mvn clean install
```

---

### 4. Run the Test
```bash
mvn test
```

This will execute the JBehave story and generate both console output and an HTML report.

---

## 📊 View Test Report


After execution, open the following file in your browser:

```
target/jbehave/view/reports.html
```

This report shows:
- ✅ Which scenarios were executed
- ✅ Which steps passed or failed
- ✅ Any exceptions or errors encountered


---

## 🌐 TestMu AI Dashboard


If the test runs successfully, you can view the session details, logs, and screenshots on your TestMu AI dashboard:
```markdown
[TestMu AI Automation Dashboard](https://automation.lambdatest.com)
```

## 📌 Notes

```markdown

- This project uses W3C-compliant capabilities (`LT:Options`) for Selenium 4.
- If you face network issues, consider using [TestMu AI Tunnel](https://www.lambdatest.com/support/docs/real-time-testing-troubleshooting/).
- You can find your TestMu AI credentials on the [Username and Access Key page](https://accounts.lambdatest.com/security/username-accesskey).
```







