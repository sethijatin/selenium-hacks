package com.github.sethijatin.selenium.hacks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class EnableWaitsOnDriver implements WebDriverListener {

    int timeoutInSeconds;
    WebDriver driver;

    public EnableWaitsOnDriver(WebDriver driver) {
        this.driver = driver;
        String seconds = System.getProperty("timeoutInSeconds", "10");
        timeoutInSeconds = Integer.parseInt(seconds);
    }

    public WebDriver apply() {
        return new EventFiringDecorator<>(this).decorate(driver);
    }

    private void isIntractable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
        if(!element.isDisplayed())
            throw  new AutoWaitException("The element you are trying to interact with is not displayed. Element -> " + element);
        if (!element.isEnabled()) {
            throw new AutoWaitException("The element you are trying to interact with is not enabled.  Element -> " + element);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element));
        AtomicInteger counter = new AtomicInteger();
        String originalFrame = element.getAttribute("outerHTML");
        wait.until((webDriver) -> {
             counter.getAndIncrement();
             String nextFrame = element.getAttribute("outerHTML");
             return originalFrame.equals(nextFrame) && counter.get() > 2;
        });
    }

    public void beforeGet(WebDriver driver, String url) {
        driver.manage().window().maximize();
    }

    public void afterGet(WebDriver driver, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

     public void beforeTo(WebDriver.Navigation navigation, String url) {
         driver.manage().window().maximize();
    }

    public void afterTo(WebDriver.Navigation navigation, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        driver.manage().window().maximize();
    }

    public void afterTo(WebDriver.Navigation navigation, URL url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void beforeFindElement(WebElement element, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void beforeFindElements(WebElement element, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void beforeClick(WebElement element) {
        isIntractable(element);
    }

    public void beforeClear(WebElement element) {
        isIntractable(element);
    }

    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        isIntractable(element);
    }

    public void beforeSubmit(WebElement element) {
        isIntractable(element);
    }

    private static class AutoWaitException extends RuntimeException {
        AutoWaitException(String errorMessage) {
            super(errorMessage);
        }
    }

}
