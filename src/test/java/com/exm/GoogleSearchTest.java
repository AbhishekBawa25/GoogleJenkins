package com.exm; // Adjusted to com.exm per your stack trace; use com.example if required 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test
    public void testGoogleSearch() {
        System.out.println("Navigating to Google...");
        driver.get("https://www.google.com");

        // Handle consent screen if present
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("L2AGLb"))).click();
            System.out.println("Clicked consent button");
        } catch (Exception e) {
            System.out.println("No consent screen found");
        }

        // Enter search query
        System.out.println("Entering search query...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")))
            .sendKeys("Selenium WebDriver");

        // Click search button
        System.out.println("Clicking search button...");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")))
            .click();

        // Wait for results page
        System.out.println("Waiting for results page...");
        wait.until(ExpectedConditions.titleContains("Selenium"));

        // Check title
        String title = driver.getTitle();
        System.out.println("Actual title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("selenium"), 
            "Title does not contain 'selenium'. Actual title: " + title);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
