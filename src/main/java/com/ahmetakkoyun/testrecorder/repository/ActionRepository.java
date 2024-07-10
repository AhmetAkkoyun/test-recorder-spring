package com.ahmetakkoyun.testrecorder.repository;

import com.ahmetakkoyun.testrecorder.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}