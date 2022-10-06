package ru.sdk.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;



public class SdkTests {

    static WebDriver driver;
    static JavascriptExecutor jse;

    @BeforeAll
    static void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        jse = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }


    public boolean elementIsExist(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ignore) {
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return false;
    }



    @Test
    public void test() {

        driver.get("http://www.rgs.ru");
        driver.manage().window().maximize();

        WebElement forCompanies = driver.findElement(By.xpath("//div[@id='__layout']/div/header/div/div/section/div/nav/ul/li[2]/a"));
        forCompanies.click();


        By myFrame = By.xpath("//iframe[@title='Flocktory widget']");


        if (elementIsExist(myFrame)) {
            driver.switchTo().frame(driver.findElement(myFrame));
            new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='widget__close js-collapse-login']"))).click();
            driver.switchTo().defaultContent();
        }


        List<WebElement> padding= driver.findElements(By.xpath("//div[@id='__layout']/div/header/div/div/section[2]/div/div/div/div/ul/li[2]/span"));
        padding.get(0).click();


        WebElement health = driver.findElement(By.xpath("//div[@id='__layout']/div/header/div/div[2]/div[2]/div/div/div/section/div/div[2]/div/div/div/ul/li[3]/a"));
        health.click();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        Assertions.assertEquals("Добровольное медицинское страхование", driver.findElement(By.xpath("//h1[@class='title word-breaking title--h2'][contains(.,'Добровольное медицинское страхование')]")).getAttribute("textContent"));


        WebElement form = driver.findElement(By.className("action-item"));
        form.click();


        WebElement nameInput = driver.findElement(By.xpath("//div[@id='__layout']/div/main/div/div/div[4]/section/div/div/div/div/div/div[2]/div/form/div/div/div/div/div/input"));
        String name = "qwertyqwerty qwertyqwerty qwertyqwerty";
        nameInput.sendKeys(name);
        Assertions.assertEquals(name, nameInput.getAttribute("value"));


        String telephone = "+7 (999) 999-9999";
        WebElement telInput = driver.findElement(By.xpath("//div[@id='__layout']/div/main/div/div/div[4]/section/div/div/div/div/div/div[2]/div/form/div/div[2]/div/div/div/input"));
        telInput.sendKeys("9999999999");
        Assertions.assertEquals(telephone, telInput.getAttribute("value"));


        String email = "234234234234";
        WebElement mailInput = driver.findElement(By.xpath("//div[@id='__layout']/div/main/div/div/div[4]/section/div/div/div/div/div/div[2]/div/form/div/div[3]/div/div/div/input"));
        mailInput.sendKeys(email);
        Assertions.assertEquals(email, mailInput.getAttribute("value"));


        String address = "234234234234";
        WebElement addressInput = driver.findElement(By.xpath("//div[@id='__layout']/div/main/div/div/div[4]/section/div/div/div/div/div/div[2]/div/form/div/div[4]/div/div/div/div/div/div/input"));
        addressInput.sendKeys(address);
        Assertions.assertEquals(address, addressInput.getAttribute("value"));


        jse.executeScript("document.getElementsByClassName('checkbox')[0].click()");

        jse.executeScript("document.getElementsByClassName('form__button-submit')[0].click()");

        Assertions.assertTrue(mailInput.getAttribute("class").contains("error-field"));
    }

    @AfterAll
        static void finish() {
        driver.quit();
    }
    }
