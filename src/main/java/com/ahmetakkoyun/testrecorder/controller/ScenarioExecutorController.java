package com.ahmetakkoyun.testrecorder.controller;

import com.ahmetakkoyun.testrecorder.model.Action;
import com.ahmetakkoyun.testrecorder.service.ScenarioExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scenario-execute")
@RequiredArgsConstructor
public class ScenarioExecutorController {

    private final ScenarioExecutorService scenarioExecutorService;

    @GetMapping("execute")
    public ResponseEntity<List<Action>> findActionsByScenarioId (String recordId) {
        return ResponseEntity.ok(scenarioExecutorService.findActionsByScenarioId(recordId));
    }

//    @PostMapping
//    public ResponseEntity<TestScenario> createTestScenario(@RequestBody Map<String, Object> payload) {
//        String name = (String) payload.get("name");
//        List<Action> actions = (List<Action>) payload.get("actions");
//        TestScenario savedScenario = testScenarioService.saveTestScenario(name, actions);
//        return ResponseEntity.ok(savedScenario);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TestScenario> getTestScenario(@PathVariable Long id) {
//        TestScenario testScenario = testScenarioService.getTestScenario(id);
//        return ResponseEntity.ok(testScenario);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TestScenario>> getAllTestScenarios() {
//        List<TestScenario> scenarios = testScenarioService.getAllTestScenarios();
//        return ResponseEntity.ok(scenarios);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTestScenario(@PathVariable Long id) {
//        testScenarioService.deleteTestScenario(id);
//        return ResponseEntity.noContent().build();
//    }
}