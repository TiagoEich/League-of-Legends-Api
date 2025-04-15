package com.lol.champs_info.validators;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddChampionValidator {

    @Autowired
    private ChampionRepository championRepository;

    public void validate(ChampionEntity champion) {
    if (championRepository.existsByNameAndRole(champion.getName(), champion.getRole())) {

        throw new IllegalArgumentException("Champion: '"+champion.getName()+"' with role: '"+champion.getRole()+"' already exists");

    }

    if (champion.getName() == null || champion.getName().trim().isEmpty()) {
        throw new IllegalArgumentException("Champion name is required");

    }

    if (champion.getRole() == null) {
        throw new IllegalArgumentException("Champion role is required");
    }
    }

}
