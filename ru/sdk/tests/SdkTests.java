package ru.sdk.tests;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver.Timeouts;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SdkTests {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("http://www.rgs.ru");

        WebElement forCompanies = driver.findElement(By.linkText("Компаниям"));
        forCompanies.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.switchTo().frame(driver.findElement(By.id("fl-616371")));
        WebElement annoy = driver.findElement(By.className("widget__close"));
        annoy.click();

        driver.switchTo().defaultContent();

        List<WebElement> padding= driver.findElements(By.className("padding"));
        padding.get(1).click();


        WebElement health = driver.findElement(By.linkText("Добровольное медицинское страхование"));
        health.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Boolean isPresent = driver.findElements(By.linkText("Добровольное медицинское страхование")).size() > 0;
        System.out.println(isPresent);

        WebElement form = driver.findElement(By.className("action-item"));
        form.click();

        WebElement nameInput = driver.findElement(By.name("userName"));
        String name = "qwertyqwerty qwertyqwerty qwertyqwerty";
        nameInput.sendKeys(name);
        System.out.println(name.equals(nameInput.getAttribute("value")));

        String telephone = "+7 (999) 999-9999";
        WebElement telInput = driver.findElement(By.name("userTel"));
        telInput.sendKeys("9999999999");
        System.out.println(telephone.equals(telInput.getAttribute("value")));

        String email = "234234234234";
        WebElement mailInput = driver.findElement(By.name("userEmail"));
        mailInput.sendKeys(email);
        System.out.println(email.equals(mailInput.getAttribute("value")));

        String adress = "234234234234";
        WebElement adressInput = driver.findElement(By.className("vue-dadata__input"));
        adressInput.sendKeys(adress);
        System.out.println(adress.equals(adressInput.getAttribute("value")));

        jse.executeScript("document.getElementsByClassName('checkbox')[0].click()");

        jse.executeScript("document.getElementsByClassName('form__button-submit')[0].click()");

        System.out.println(mailInput.getAttribute("class").contains("error-field"));

        driver.quit();

    }


}
