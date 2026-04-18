package com.sudhir;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import java.io.ByteArrayInputStream;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.sudhir.PageObjectFactory.PageObjectFactory;

import io.qameta.allure.Allure;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class BaseTest {

    public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;

    // page object for login page
    protected PageObjectFactory pages;

    @BeforeMethod
    public void setup() {
        System.out.println("Setting up the test environment...");
        playwright = Playwright.create(); // Start Playwright
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Launch the browser in headed mode
        this.context = browser.newContext(); // Create a new browser context for isolation between tests
        this.page = context.newPage(); // Create a new page in the context for the test
        page.navigate("https://www.saucedemo.com/"); // Replace with the actual URL

        // Fill in the username and password fields and click the login button
        Locator usernameField = page.locator("#user-name"); // Locator for the username field
        Locator passwordField = page.locator("#password"); // Locator for the password field
        Locator loginButton = page.locator("#login-button"); // Locator for the login button

        usernameField.fill("standard_user"); // Replace with actual username
        passwordField.fill("secret_sauce"); // Replace with actual password
        loginButton.click(); // Click the login button
        page.waitForLoadState(LoadState.DOMCONTENTLOADED); // Wait for the page to load after login
        page.waitForLoadState(LoadState.NETWORKIDLE); // Wait for network to be idle after login

        // Initialize page objects
        pages = new PageObjectFactory(page);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("Tearing down the test environment...");
        this.getScreenshot(result);
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    public void getScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Allure.addAttachment(
                "Failure Screenshot",
                new ByteArrayInputStream(page.screenshot())
            );

            if (result.getThrowable() != null) {
                Allure.addAttachment(
                    "Failure Reason",
                    result.getThrowable().toString()
                );
            }
        }
    }
}
