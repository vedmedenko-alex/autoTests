package com.solvd.demo.ui.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
        WebElement results = driver.findElement(By.cssSelector(".product_list"));
        Assert.assertTrue(results.isDisplayed(), "Search results are visible");
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
        WebElement newsletter = driver.findElement(By.id("newsletter-input"));
        newsletter.clear();
        newsletter.sendKeys("not-an-email");
        driver.findElement(By.name("submitNewsletter")).click();
        WebElement alert = driver.findElement(By.cssSelector(".alert"));
        Assert.assertTrue(alert.getText().toLowerCase().contains("invalid"), "Newsletter error shown");
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
}
