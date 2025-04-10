package com.lol.champs_info.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChampionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String clazz;
    private String role;
    private String tier;
    private Double score;
    private Double trend;
    private Double winRate;
    private Double roleRate;
    private Double pickRate;
    private Double banRate;
    private Double kda;
}
