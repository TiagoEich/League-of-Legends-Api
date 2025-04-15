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
    public ResponseEntity<?> getByRegion(@RequestParam String region) {
        try {
            return ResponseEntity.ok(championService.getChampionsFromRegion(region));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/class/{classType}")
    public ResponseEntity<?>getChampionsByClass(@PathVariable String classType) {
        try{
            return ResponseEntity.ok(championService.getChampionsByClass(classType));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @PostMapping("/addChampion")
    public ResponseEntity<?> createChampion(@RequestBody ChampionEntity champion) {
        try {
            ChampionEntity createdChampion = championService.addChampion(champion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChampion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/champions")
    public ResponseEntity<ChampionEntity> updateChampion(@RequestBody ChampionEntity updatedChampion) {
        ChampionEntity result = championService.updateChampion(updatedChampion);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteChampion")
    public ResponseEntity<String> deleteByNameAndRole (@RequestParam String name, @RequestParam String role) {
        boolean deleted = championService.deleteByNameAndRole(name, role);

        if (deleted) {
            return ResponseEntity.ok("Champion with name '"+name+"' and role '"+role+"' was deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
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