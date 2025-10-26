package com.solvd.demo.ui.pages;

import com.solvd.demo.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By email = By.id("email");
    private final By password = By.id("passwd");
    private final By signInButton = By.id("SubmitLogin");
    private final By errorBox = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginInvalid(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(signInButton);
    }

    public String getErrorText() {
        return getText(errorBox);
    }
}
