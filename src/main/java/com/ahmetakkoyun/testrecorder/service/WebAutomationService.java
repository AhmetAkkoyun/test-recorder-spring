package com.ahmetakkoyun.testrecorder.service;

import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.repository.ActionRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class WebAutomationService {

    @Autowired
    private ActionRepository actionRepository;

    public void performActions(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            driver.get(url);

            List<Action> actions = actionRepository.findAll();

            for (Action action : actions) {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(action.getSelector())));

                switch (action.getType().toLowerCase()) {
                    case "click":
                        Thread.sleep(5000);
                        element.click();
                        break;
                    case "input":
                        Thread.sleep(5000);
                        element.sendKeys(action.getValue());
                        break;
                    // Diğer action tipleri için case'ler eklenebilir
                }
            }

            // İşlemler tamamlandıktan sonra bekletme (isteğe bağlı)
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}