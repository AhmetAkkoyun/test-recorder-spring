package com.ahmetakkoyun.testrecorder.service;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.repository.TestScenarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioRecorderService {

    private final TestScenarioRepository testScenarioRepository;

    public ScenarioRecorderService(TestScenarioRepository testScenarioRepository) {
        this.testScenarioRepository = testScenarioRepository;
    }

    public TestScenario saveTestScenario(String name, List<Action> actions, String recordId) {
        if (recordId == null || recordId.trim().isEmpty()) {
            throw new IllegalArgumentException("recordId cannot be null or empty");
        }
        TestScenario testScenario = new TestScenario();
        testScenario.setName(name);
        testScenario.setRecordId(recordId);
        for (Action action : actions) {
            action.setTestScenario(testScenario);
        }
        testScenario.setActions(actions);
        return testScenarioRepository.save(testScenario);
    }

    public TestScenario getTestScenario(String recordId) {
        return testScenarioRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Test Scenario not found"));
    }

    public List<TestScenario> getAllTestScenarios() {
        return testScenarioRepository.findAll();
    }

    public void deleteTestScenario(String recordId) {
        testScenarioRepository.deleteById(recordId);
    }
}
