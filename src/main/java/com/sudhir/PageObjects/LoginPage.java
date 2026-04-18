package com.sudhir.PageObjects;

import com.microsoft.playwright.*;

public class LoginPage {

    private final Page page;
    private Locator usernameField; // Locator for the username field
    private Locator passwordField; // Locator for the password field
    private Locator loginButton; // Locator for the login button

    public LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("#username"); // Replace with actual selector
        this.passwordField = page.locator("#password"); // Replace with actual selector
        this.loginButton = page.locator("#loginButton"); // Replace with actual selector
    }

    // Method to perform login action
    public void login(String username, String password) {
        usernameField.fill(username); // Replace with actual selector
        passwordField.fill(password); // Replace with actual selector
        loginButton.click(); // Replace with actual selector
    }
}
