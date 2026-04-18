# JavaWithPlaywright

Automation framework using Java, Maven, Playwright, TestNG, and Allure.

## 1. Prerequisites

Install the following tools:

1. Java 11+
2. Maven 3.9+
3. Node.js (installed in this environment)
4. Allure commandline (npm package)

Check versions:

- java -version
- mvn -v
- node -v
- npm -v

## 2. Project Structure

- src/main/java -> Page objects and framework classes
- src/test/java -> Test classes and base test setup
- testng.xml -> TestNG suite
- pom.xml -> Maven dependencies and plugins
- target -> Maven build output and generated reports

## 3. Install Dependencies

Run from project root:

- mvn clean install

## 4. Install Playwright Browsers

This project is configured with exec-maven-plugin to run Playwright CLI.

Use this command:

- mvn exec:java "-Dexec.args=install"

If your shell still has argument parsing issues, use explicit plugin coordinates:

- mvn org.codehaus.mojo:exec-maven-plugin:3.1.0:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install"

## 5. Run Tests

Run suite from testng.xml:

- mvn test

Run clean + tests:

- mvn clean test

## 6. Generate Allure Report (Maven)

Run tests and generate report in one command:

- mvn clean test allure:report

Report will be created under:

- target/allure-report/<timestamp>

## 7. Open Allure Report

Recommended: open via Allure web server (not direct file open).

Option A: Use Allure command (if working in your environment)

- allure open target/allure-report/<timestamp>

Option B: Use direct Allure batch path (works when Node wrapper fails)

- %APPDATA%\\npm\\node_modules\\allure-commandline\\dist\\bin\\allure.bat open target/allure-report/<timestamp>

## 8. Known Environment Notes

### 8.1 Playwright CLI via Maven argument parsing on Windows

In this environment, unquoted -Dexec.* arguments may be misparsed by Maven.
Use quoted arguments as shown in section 4.

### 8.2 Allure npm wrapper spawn EINVAL

If you run:

- allure serve target/allure-results

and see spawn EINVAL, use direct batch command:

- %APPDATA%\\npm\\node_modules\\allure-commandline\\dist\\bin\\allure.bat serve target/allure-results

### 8.3 Directly opening index.html can look empty

Allure report is a web app and should be served over HTTP.
Use Allure open/serve commands instead of opening index.html via file://.

## 9. Common Commands Cheat Sheet

- mvn clean install
- mvn exec:java "-Dexec.args=install"
- mvn test
- mvn clean test allure:report
- %APPDATA%\\npm\\node_modules\\allure-commandline\\dist\\bin\\allure.bat open target/allure-report/<timestamp>

## 10. Troubleshooting Quick Fixes

1. Compilation errors:
   - Run: mvn clean compile
   - Check imports and package names in page objects/tests.

2. Test compilation errors:
   - Run: mvn test
   - Verify method names between tests and page objects match exactly.

3. Allure report skipped due missing results:
   - Ensure tests ran first.
   - Ensure results are generated in target/allure-results.

4. Allure page blank:
   - Open report through Allure server command, not direct index.html.
