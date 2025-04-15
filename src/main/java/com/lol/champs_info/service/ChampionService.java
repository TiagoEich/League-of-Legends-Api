package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import com.lol.champs_info.validators.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;
    private final AddChampionValidator championValidator;
    private final TierValidator tierValidator;
    private final RoleValidator roleValidator;
    private final RegionValidator regionValidator;
    private final ClassTypeValidator classTypeValidator;

    public ChampionService(ChampionRepository championRepository, AddChampionValidator
                                   championValidator, TierValidator tierValidator, RoleValidator roleValidator,
                           RegionValidator regionValidator, ClassTypeValidator classTypeValidator) {
        this.championRepository = championRepository;
        this.championValidator = championValidator;
        this.tierValidator = tierValidator;
        this.roleValidator = roleValidator;
        this.regionValidator = regionValidator;
        this.classTypeValidator = classTypeValidator;
    }

    public List<String> getChampionsName()
    {
        return championRepository.findNames();
    }

    public List<ChampionEntity> getChampionsFromRegion(String region){
        regionValidator.validateRegion(region);
        return championRepository.findDistinctByRegion(region);
    }

    public List <ChampionEntity> getChampionsByClass(String classType) {
        classTypeValidator.validateClass(classType);
        return championRepository.findByClassType(classType);
    }

    public List <ChampionEntity> getChampionsByRole(String role) {
        roleValidator.validation(role);
        return championRepository.findAll().stream()
                .filter(champion -> role.equalsIgnoreCase(champion.getRole()))
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

    @Transactional
    public boolean deleteByNameAndRole (String name, String role) {
        List<ChampionEntity> champions = championRepository.findAll().stream()
                .filter(champion -> champion.getName().equalsIgnoreCase(name)
                        && champion.getRole().equalsIgnoreCase(role)).
                toList();
        if (champions.isEmpty()) {
            return false;
        }
        championRepository.deleteAll(champions);
        return true;
    }

}
//    public boolean deleteById(UUID id) {
//        if (championRepository.existsById(id)) {
//            championRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }

