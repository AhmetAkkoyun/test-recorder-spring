package com.ahmetakkoyun.testrecorder.repository;

import com.ahmetakkoyun.testrecorder.model.TestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestScenarioRepository extends JpaRepository<TestScenario, String> {
//    Optional<TestScenario> findById(String recordId);
//
//    Optional<TestScenario> deleteById(String recordId);
}