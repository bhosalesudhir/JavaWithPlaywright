package com.sudhir.PageObjects;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import io.qameta.allure.Step;

public class Inventory {
    private final Page page;

    public Inventory(Page page) {
        this.page = page;
    }

    @Step("Add {productName} to the cart")
    public void addToCart(String productName) {
        page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();
    }
}
