package com.ahmetakkoyun.testrecorder.repository;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestScenarioRepository extends JpaRepository<TestScenario, Long> {
}