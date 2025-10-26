package com.solvd.demo.ui.pages;

import com.solvd.demo.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("img.logo");
    private final By womenMenu = By.linkText("Women");
    private final By tshirtsSubMenu = By.linkText("T-shirts");
    private final By searchInput = By.id("search_query_top");
    private final By searchButton = By.name("submit_search");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://automationpractice.pl/index.php");
    }

    public void clickLogo() {
        click(logo);
    }

    public void hoverWomenMenu() {
        scrollToTop();
        wait.until(ExpectedConditions.presenceOfElementLocated(womenMenu));
        new org.openqa.selenium.interactions.Actions(driver)
                .moveToElement(driver.findElement(womenMenu))
                .pause(java.time.Duration.ofMillis(500))
                .perform();
    }

    public void clickTshirts() {
        click(tshirtsSubMenu);
    }

    public void search(String term) {
        type(searchInput, term);
        click(searchButton);
    }
}
