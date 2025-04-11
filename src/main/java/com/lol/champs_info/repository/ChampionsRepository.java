package com.lol.champs_info.repository;

import com.lol.champs_info.model.ChampionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChampionsRepository extends JpaRepository<ChampionsEntity, UUID> {
    @Query("SELECT DISTINCT c.name FROM ChampionsEntity c ORDER BY c.name ASC")
    List<String> findNames();
}
