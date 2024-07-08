package com.ahmetakkoyun.testrecorder.service;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.model.Action;

import java.util.List;

public interface TestScenarioService {
    TestScenario saveTestScenario(String name, List<Action> actions);
    TestScenario getTestScenario(Long id);
    List<TestScenario> getAllTestScenarios();
    void deleteTestScenario(Long id);
}