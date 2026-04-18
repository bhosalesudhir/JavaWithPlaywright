package com.sudhir;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
   
  @Test
  public void AddToCard() {
    pages.getInventoryPage().addToCart("Sauce Labs Backpack"); // Replace with actual product name
  }
}
