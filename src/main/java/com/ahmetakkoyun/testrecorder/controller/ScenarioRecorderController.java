package com.ahmetakkoyun.testrecorder.controller;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.service.ScenarioRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test-scenarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScenarioRecorderController {

    private final ScenarioRecorderService scenarioRecorderService;

    @Autowired
    public ScenarioRecorderController(ScenarioRecorderService scenarioRecorderService) {
        this.scenarioRecorderService = scenarioRecorderService;
    }

    @PostMapping("/record")
    public ResponseEntity<TestScenario> recordScenario(@RequestBody List<Action> actions, @RequestParam String recordId) {
        if (recordId == null || recordId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        String name = "Recorded Scenario " + System.currentTimeMillis();
        TestScenario savedScenario = scenarioRecorderService.saveTestScenario(name, actions, recordId);
        return ResponseEntity.ok(savedScenario);
    }

    @PostMapping
    public ResponseEntity<TestScenario> createTestScenario(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        List<Action> actions = (List<Action>) payload.get("actions");
        String recordId = (String) payload.get("recordId");
        TestScenario savedScenario = scenarioRecorderService.saveTestScenario(name, actions, recordId);
        return ResponseEntity.ok(savedScenario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestScenario> getTestScenario(@PathVariable String recordId) {
        TestScenario testScenario = scenarioRecorderService.getTestScenario(recordId);
        return ResponseEntity.ok(testScenario);
    }

    @GetMapping
    public ResponseEntity<List<TestScenario>> getAllTestScenarios() {
        List<TestScenario> scenarios = scenarioRecorderService.getAllTestScenarios();
        return ResponseEntity.ok(scenarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestScenario(@PathVariable String recordId) {
        scenarioRecorderService.deleteTestScenario(recordId);
        return ResponseEntity.noContent().build();
    }
}
