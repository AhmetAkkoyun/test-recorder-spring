package com.ahmetakkoyun.testrecorder.controller;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.service.TestScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test-scenarios")
public class TestScenarioController {

    private final TestScenarioService testScenarioService;

    @Autowired
    public TestScenarioController(TestScenarioService testScenarioService) {
        this.testScenarioService = testScenarioService;
    }

    @PostMapping("/record")
    public ResponseEntity<TestScenario> recordScenario(@RequestBody List<Action> actions) {
        String name = "Recorded Scenario " + System.currentTimeMillis();
        TestScenario savedScenario = testScenarioService.saveTestScenario(name, actions);
        return ResponseEntity.ok(savedScenario);
    }

    @PostMapping
    public ResponseEntity<TestScenario> createTestScenario(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        List<Action> actions = (List<Action>) payload.get("actions");
        TestScenario savedScenario = testScenarioService.saveTestScenario(name, actions);
        return ResponseEntity.ok(savedScenario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestScenario> getTestScenario(@PathVariable Long id) {
        TestScenario testScenario = testScenarioService.getTestScenario(id);
        return ResponseEntity.ok(testScenario);
    }

    @GetMapping
    public ResponseEntity<List<TestScenario>> getAllTestScenarios() {
        List<TestScenario> scenarios = testScenarioService.getAllTestScenarios();
        return ResponseEntity.ok(scenarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestScenario(@PathVariable Long id) {
        testScenarioService.deleteTestScenario(id);
        return ResponseEntity.noContent().build();
    }
}