package com.ahmetakkoyun.testrecorder.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String selector;
    private String value;
}