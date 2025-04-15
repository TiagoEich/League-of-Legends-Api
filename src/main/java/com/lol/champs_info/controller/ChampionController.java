package com.lol.champs_info.controller;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.service.ChampionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChampionController {

    private final ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/names")
        public ResponseEntity<List<String>> getAllChampsName() {
    return ResponseEntity.ok(championService.getChampionsName());

    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<ChampionEntity>> getChampionsByRegion(@PathVariable String region) {
        return ResponseEntity.ok(championService.getChampionsFromRegion(region));
    }


    @GetMapping("/class/{classType}")
    public ResponseEntity<List<ChampionEntity>>getChampionsByClass(@PathVariable String classType) {
        return ResponseEntity.ok(championService.getChampionsByClass(classType));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getChampionsByRole (@PathVariable String role) {
        try{
            List <ChampionEntity> champion = championService.getChampionsByRole(role);
            return ResponseEntity.ok(champion);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/tier/{tier}")
    public ResponseEntity<?> getChampionsByTier (@PathVariable String tier) {
        try{
            List<ChampionEntity> champion = championService.getChampionsByTier(tier);
            return ResponseEntity.ok(champion);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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