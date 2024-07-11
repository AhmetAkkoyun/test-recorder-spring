package com.ahmetakkoyun.testrecorder.service;

import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.repository.TestScenarioRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class ScenarioExecutorService {

    private final TestScenarioRepository testScenarioRepository;

    public ScenarioExecutorService(TestScenarioRepository testScenarioRepository) {
        this.testScenarioRepository = testScenarioRepository;
    }


    public List<Action> findActionsByScenarioId (String recordId) {
        Optional<TestScenario> scenario = testScenarioRepository.findById(recordId);
        return scenario.get().getActions();
    }


    String url = "https://demo.automationtesting.in/Register.html";




    public String scenarioExecute (String recordId, String url) {
        List<Action> actions = findActionsByScenarioId(recordId);

        Map<String, String> actionsMap = new HashMap<>();
        List<Map<String, String>> testActions = new ArrayList<>();

        for (Action action : actions) {
            if (action.getSelector().equals("#startRecording")){
                continue;
            }
            actionsMap.put("selector", action.getSelector());
            actionsMap.put("type", action.getType());
            actionsMap.put("value", action.getValue());
            testActions.add(actionsMap);
            System.out.println(actions);
        }

        if (actions.isEmpty()) {
            System.out.println("SCENARIO NOT FOUND");
        } else {

            System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");

            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get(url);

            try {
                for (Map<String, String> action : testActions) {
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(action.get("selector"))));

                    switch (action.get("type")) {
                        case "click":
                            element.click();
                            break;
                        case "input":
                            element.clear();
                            element.sendKeys(action.get("value"));
                            break;
                    }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.err.println("Error occurred during test execution: " + e.getMessage());
                e.printStackTrace();
            } finally {
                driver.quit();
            }

        }
    return null;
    }


}



