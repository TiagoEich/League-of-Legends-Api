package com.lol.champs_info.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "champions")
public class ChampionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String classType;
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
