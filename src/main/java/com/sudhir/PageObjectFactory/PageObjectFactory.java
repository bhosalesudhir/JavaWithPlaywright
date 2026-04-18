package com.sudhir.PageObjectFactory;

import com.microsoft.playwright.*;
import com.sudhir.PageObjects.LoginPage;
import com.sudhir.PageObjects.Inventory;

public class PageObjectFactory {
    private LoginPage loginPage;
    private Inventory inventoryPage;

    public PageObjectFactory(Page page) {
        this.loginPage = new LoginPage(page);
        this.inventoryPage = new Inventory(page);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public Inventory getInventoryPage() {
        return inventoryPage;
    }

}
