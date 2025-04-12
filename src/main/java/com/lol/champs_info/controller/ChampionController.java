package com.lol.champs_info.controller;

import com.lol.champs_info.service.ChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class ChampionController {

    private final ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/names")
        public ResponseEntity<List<String>> getAllChamps() {
    return ResponseEntity.ok(championService.getChampions());

    }
}