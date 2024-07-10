package com.ahmetakkoyun.testrecorder.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "test_scenarios")
public class TestScenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Action> actions;
}