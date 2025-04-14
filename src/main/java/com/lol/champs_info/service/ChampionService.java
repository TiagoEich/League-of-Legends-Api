package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import com.lol.champs_info.validators.ChampionValidator;
import com.lol.champs_info.validators.TierValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;
    private final ChampionValidator championValidator;
    private final TierValidator tierValidator;

    public ChampionService(ChampionRepository championRepository, ChampionValidator championValidator, TierValidator tierValidator) {
        this.championRepository = championRepository;
        this.championValidator = championValidator;
        this.tierValidator = tierValidator;
    }

    public List<String> getChampionsName() {
        return championRepository.findNames();
    }

    public List<ChampionEntity> getChampionsFromRegion(String region){
        return championRepository.findDistinctByRegion(region);
    }

    public List <ChampionEntity> getChampionsByClass(String classType) {
        return championRepository.findByClassType(classType);
    }

    public List <ChampionEntity> getChampionsByRole(String searchText) {
        return championRepository.findAll().stream()
                .filter(champions ->
                        champions.getRole().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<ChampionEntity> getChampionsByTier(String tier) {
        tierValidator.validate(tier);

        return championRepository.findAll().stream()
                .filter(champion -> tier.equalsIgnoreCase(champion.getTier()))
                .collect(Collectors.toList());
    }

    public ChampionEntity addChampion(ChampionEntity champion) {
        championValidator.validate(champion);
        return championRepository.save(champion);
    }

    public ChampionEntity updateChampion(ChampionEntity updatedChampion) {
        Optional<ChampionEntity> existingChamp = championRepository.findByName(updatedChampion.getName());
        if (existingChamp.isPresent()) {
            ChampionEntity champToUdate = existingChamp.get();
            champToUdate.setName(updatedChampion.getName());
            champToUdate.setClassType(updatedChampion.getClassType());
            champToUdate.setRole(updatedChampion.getRole());
            champToUdate.setTier(updatedChampion.getTier());
            champToUdate.setRegion(updatedChampion.getRegion());
            champToUdate.setScore(updatedChampion.getScore());
            champToUdate.setTrend(updatedChampion.getTrend());
            champToUdate.setWinRate(updatedChampion.getWinRate());
            champToUdate.setRoleRate(updatedChampion.getRoleRate());
            champToUdate.setPickRate(updatedChampion.getPickRate());
            champToUdate.setBanRate(updatedChampion.getBanRate());
            champToUdate.setKda(champToUdate.getKda());
//            BeanUtils.copyProperties(updatedChampion, champToUdate);

            championRepository.save(champToUdate);
            return  champToUdate;
        }
        return  null;
    }

//    public boolean deleteById(UUID id) {
//        if (championsRepository.existsById(id)) {
//            championsRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
