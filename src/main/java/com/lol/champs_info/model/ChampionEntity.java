package com.lol.champs_info.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "champions")
public class ChampionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore // to ignore the ID on the Requisition since he has the getter and setters annotations
    //@Setter(AccessLevel.NONE) That's another way, it makes the swagger ignore the id attribute, can be used for getter as well
    private UUID id;
    @JsonFormat()
    private String name;
    private String region;
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