package com.ahmetakkoyun.testrecorder.service;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.repository.TestScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestScenarioServiceImpl implements TestScenarioService {

    private final TestScenarioRepository testScenarioRepository;

    @Autowired
    public TestScenarioServiceImpl(TestScenarioRepository testScenarioRepository) {
        this.testScenarioRepository = testScenarioRepository;
    }

    @Override
    public TestScenario saveTestScenario(String name, List<Action> actions) {
        TestScenario testScenario = new TestScenario();
        testScenario.setName(name);
        testScenario.setActions(actions);
        return testScenarioRepository.save(testScenario);
    }

    @Override
    public TestScenario getTestScenario(Long id) {
        return testScenarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test Scenario not found"));
    }

    @Override
    public List<TestScenario> getAllTestScenarios() {
        return testScenarioRepository.findAll();
    }

    @Override
    public void deleteTestScenario(Long id) {
        testScenarioRepository.deleteById(id);
    }
}