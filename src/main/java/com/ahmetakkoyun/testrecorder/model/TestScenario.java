package com.ahmetakkoyun.testrecorder.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "test_scenarios")
public class TestScenario {
    @Id
    @Column(nullable = false, unique = true)
    private String recordId;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "testScenario")
    private List<Action> actions;
}
