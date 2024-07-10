package com.ahmetakkoyun.testrecorder.controller;
import com.ahmetakkoyun.testrecorder.service.WebAutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomationController {

    @Autowired
    private WebAutomationService webAutomationService;

    @GetMapping("/run-automation")
    public String runAutomation(@RequestParam String url) {
        webAutomationService.performActions(url);
        return "Automation completed!";
    }
}