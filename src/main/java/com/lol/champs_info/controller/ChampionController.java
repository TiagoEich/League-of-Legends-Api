package com.lol.champs_info.controller;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.service.ChampionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/region/{region}")
    public ResponseEntity<List<ChampionEntity>> getChampionsByRegion(@PathVariable String region) {
        return ResponseEntity.ok(championService.getChampionsFromRegion(region));
    }

    @PostMapping
    public ResponseEntity<?> createChampion(@RequestBody ChampionEntity champion) {
        try {
            ChampionEntity createdChampion = championService.addChampion(champion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChampion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



//    @DeleteMapping("/{id}") THIS METHOD HAS TO COMMENTED BECAUSE THE USER CAN'T AND WON'T SEE THE IDS OF THE CHAMPIONS
        //SO I MUST ONLY USE IT WHEN I HAVE A DUPLICATE CHAMPION NAME WITH THE SAME ROLE.
//    public ResponseEntity<ChampionEntity> deleteById(@PathVariable UUID id) {
//        boolean isDeleted = championService.deleteById(id);
//
//        if (isDeleted) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}