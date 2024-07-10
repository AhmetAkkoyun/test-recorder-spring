package com.ahmetakkoyun.testrecorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestRecorderApplication {
    public static void main(String[] args) {
       //SpringApplication.run(TestRecorderApplication.class, args);
       ConfigurableApplicationContext context = SpringApplication.run(TestRecorderApplication.class, args);

        // RestTemplate oluştur
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        // Otomasyon URL'si
        String automationUrl = "http://localhost:8080/run-automation?url=https://demo.automationtesting.in/Register.html";

        try {
            // REST isteğini yap
            String result = restTemplate.getForObject(automationUrl, String.class);
            System.out.println("Automation result: " + result);
        } catch (Exception e) {
            System.err.println("Error occurred while running automation: " + e.getMessage());
        } finally {
            // Uygulamayı kapat
            context.close();
        }
    }
}