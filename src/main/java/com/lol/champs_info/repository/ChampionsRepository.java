package com.lol.champs_info.repository;

import com.lol.champs_info.model.ChampionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChampionsRepository extends JpaRepository<ChampionsEntity, UUID> {
}
