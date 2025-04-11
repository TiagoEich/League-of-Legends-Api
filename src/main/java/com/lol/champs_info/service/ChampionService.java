package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionsEntity;
import com.lol.champs_info.repository.ChampionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionService {


    private final ChampionsRepository championsRepository;


    public ChampionService(ChampionsRepository championsRepository) {
        this.championsRepository = championsRepository;
    }

    public List<String> getChampions() {
        return championsRepository.findNames();
    }

    public List<ChampionsEntity> getChampionsFromRegion(String region){
        return championsRepository.findAll().stream()
                .filter(champions ->region.equals(champions.getRegions()))
                .collect(Collectors.toList());
    }

    public List <ChampionsEntity> getChampionsByClass(String searchText) {
        return championsRepository.findAll().stream()
                .filter(champions ->  champions.getClassType().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List <ChampionsEntity> getChampionsByRole(String searchText) {
        return championsRepository.findAll().stream()
                .filter(champions ->
                        champions.getRole().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List <ChampionsEntity> getChampionsByTier(String searchText) {
        return championsRepository.findAll().stream()
                .filter(champions -> champions.getTier().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ChampionsEntity addChampion(ChampionsEntity champion) {
        championsRepository.save(champion);
        return champion;
    }

    public ChampionsEntity updateChampion(ChampionsEntity updatedChampion) {
        Optional<ChampionsEntity> existingChamp = championsRepository.findByName(updatedChampion.getName());
        if (existingChamp.isPresent()) {
            ChampionsEntity champToUdate = existingChamp.get();
            champToUdate.setName(updatedChampion.getName());
            champToUdate.setClassType(updatedChampion.getClassType());
            champToUdate.setRole(updatedChampion.getRole());
            champToUdate.setTier(updatedChampion.getTier());
            champToUdate.setRegions(updatedChampion.getRegions());
            champToUdate.setScore(updatedChampion.getScore());
            champToUdate.setTrend(updatedChampion.getTrend());
            champToUdate.setWinRate(updatedChampion.getWinRate());
            champToUdate.setRoleRate(updatedChampion.getRoleRate());
            champToUdate.setPickRate(updatedChampion.getPickRate());
            champToUdate.setBanRate(updatedChampion.getBanRate());
            champToUdate.setKda(champToUdate.getKda());

            championsRepository.save(champToUdate);
            return  champToUdate;
        }
        return  null;
    }


}
