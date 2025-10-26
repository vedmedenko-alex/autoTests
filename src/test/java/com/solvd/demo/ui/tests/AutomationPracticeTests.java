package com.solvd.demo.ui.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.solvd.demo.ui.pages.ContactUsPage;
import com.solvd.demo.ui.pages.HomePage;
import com.solvd.demo.ui.pages.LoginPage;

import java.time.Duration;
import java.util.List;
public class AutomationPracticeTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationpractice.pl/index.php");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() {
        Assert.assertTrue(driver.getTitle().contains("My Shop"));
    }

    @Test(priority = 2)
    public void searchForProduct() {
        WebElement search = driver.findElement(By.id("search_query_top"));
        search.clear();
        search.sendKeys("dress");
        search.sendKeys(Keys.ENTER);
        By resultsHeader = By.cssSelector(".page-heading.product-listing");
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsHeader));

        Assert.assertTrue(header.isDisplayed(), "Search results header visible");
    }

    @Test(priority = 3)
    public void clickOnProductAndVerifyDetails() {
        WebElement firstProduct = driver.findElement(By.cssSelector(".product_list .product-container"));
        firstProduct.click();
        WebElement productTitle = driver.findElement(By.cssSelector("h1[itemprop='name']"));
        Assert.assertTrue(productTitle.isDisplayed(), "Product title is visible");
    }

    @Test(priority = 4)
    public void navigateToContactUsForm() {
        driver.findElement(By.linkText("Contact us")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=contact"));
    }

    @Test(priority = 5)
    public void fillAndValidateContactFormError() {
        Select subject = new Select(driver.findElement(By.id("id_contact")));
        subject.selectByVisibleText("Customer service");

        driver.findElement(By.id("email")).sendKeys("invalid-email");
        driver.findElement(By.id("message")).sendKeys("Test message for automation.");

        driver.findElement(By.id("submitMessage")).click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
        Assert.assertTrue(alert.getText().toLowerCase().contains("invalid"), "Error message appears");
    }

    @Test(priority = 6)
    public void scrollToFooterAndVerifyNewsletterField() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement newsletter = driver.findElement(By.id("newsletter-input"));
        Assert.assertTrue(newsletter.isDisplayed(), "Newsletter input visible");
    }

    @Test(priority = 7)
    public void trySubscribeToNewsletterInvalidEmail() {
        By newsletterInput = By.id("newsletter-input");
        By submitButton = By.name("submitNewsletter");
        By alertBox = By.cssSelector(".alert");

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(newsletterInput));
        input.clear();
        input.sendKeys("not-an-email");
        driver.findElement(submitButton).click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(alertBox));

        String alertText = alert.getText().trim().toLowerCase();
        Assert.assertTrue(
                alertText.contains("invalid email address"),
                "Newsletter error shown for invalid input"
        );
    }

    @Test(priority = 8)
    public void hoverOverCategoryMenu() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        driver.findElement(By.cssSelector("img.logo")).click();
        WebElement womenTab = driver.findElement(By.linkText("WOMEN"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenTab).pause(Duration.ofSeconds(1)).perform();

        By tshirtsLocator = By.linkText("T-shirts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tshirtsLocator));

        WebElement subCategory = driver.findElement(tshirtsLocator);
        Assert.assertTrue(subCategory.isDisplayed(), "Subcategory visible on hover");
    }

    @Test(priority = 9)
    public void openTShirtsCategoryAndCheckProducts() {
        driver.findElement(By.linkText("T-SHIRTS")).click();
        List<WebElement> products = driver.findElements(By.cssSelector(".product_list .product-container"));
        Assert.assertTrue(products.size() > 0, "T-shirts category shows at least one product");
    }

    @Test(priority = 10)
    public void openLoginPageAndCheckFields() {
        driver.findElement(By.className("login")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=authentication"));

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("passwd"));
        Assert.assertTrue(emailField.isDisplayed() && passwordField.isDisplayed(), "Login fields are visible");
    }

    @Test(priority = 11)
    public void searchUsingPageObject() {
        HomePage home = new HomePage(driver);
        home.open();
        home.search("blouse");
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product_list")));
        Assert.assertTrue(results.isDisplayed(), "Search results visible");
    }

    @Test(priority = 12)
    public void hoverAndOpenTshirtsCategory() {
        HomePage home = new HomePage(driver);
        home.open();
        home.hoverWomenMenu();
        home.clickTshirts();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("t-shirts"));
    }

    @Test(priority = 13)
    public void loginWithInvalidCredentials() {
        driver.findElement(By.className("login")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInvalid("fake@mail.com", "wrongpass");
        Assert.assertTrue(loginPage.getErrorText().contains("Authentication failed"));
    }

    @Test(priority = 14)
    public void contactFormInvalidEmailShowsError() {
        driver.get("https://automationpractice.pl/index.php?controller=contact");

        ContactUsPage contactPage = new ContactUsPage(driver);
        contactPage.submitForm("invalid-email", "message");

        String error = contactPage.getErrorMessage().toLowerCase();
        Assert.assertTrue(error.contains("invalid email address"), "Error shown for invalid contact form email");
    }

    @Test(priority = 15)
    public void scrollAndVerifyFooterVisible() {
        HomePage home = new HomePage(driver);
        home.open();
        By footer = By.cssSelector("#footer");
        home.scrollToElement(footer);
        WebElement footerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(footer));
        Assert.assertTrue(footerElement.isDisplayed(), "Footer is visible");
    }

}
