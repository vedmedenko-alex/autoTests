package com.solvd.demo.ui.pages;

import com.solvd.demo.ui.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

    private final By formContainer = By.id("contact-form-box");   
    private final By subjectHeading = By.id("id_contact");
    private final By emailField = By.id("email");
    private final By messageBox = By.id("message");
    private final By sendButton = By.id("submitMessage");
    private final By errorAlert = By.cssSelector(".alert");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageReady() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(formContainer),
                ExpectedConditions.presenceOfElementLocated(subjectHeading)
        ));
        scrollToTop();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectHeading));
    }

    public void submitForm(String email, String message) {
        waitUntilPageReady();

        Select select = new Select(driver.findElement(subjectHeading));
        select.selectByVisibleText("CUSTOMER SERVICE - CONTACT US");

        type(emailField, email);
        type(messageBox, message);
        click(sendButton);
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
        return error.getText().trim();
    }
}
