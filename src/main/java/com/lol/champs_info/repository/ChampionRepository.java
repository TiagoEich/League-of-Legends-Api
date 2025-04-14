package com.lol.champs_info.repository;

import com.lol.champs_info.model.ChampionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChampionRepository extends JpaRepository<ChampionEntity, UUID> {
    @Query("SELECT DISTINCT c.name FROM ChampionEntity c ORDER BY c.name ASC") /*This query is pretty important. When
    Using it, the next method called FindNames(); will guarantee that the name of the champions won't be duplicated, since there are some
    champions who appear more than once but with different roles. This shouldn't be visible on this query because I just want the names of them. */
    List<String> findNames(); // Used on service, method: getChampion

    @Query(value = "SELECT c.* FROM champions c WHERE LOWER(c.region) = LOWER(:region)",   //it can be necessary to use 'value' parameter when having a more complex query
            nativeQuery = true) //Distinct = consider all the columns.... Distinct on = only consider the specified columns
    //nativeQuery specified that this query is totally from postgres not JPQL

    /*This query is pretty similar to the first one. It does the same thing but with a different purpose. In this query,
    when I search by region, a champion mentioned more than once on the CSV file won't appear twice or more. Example:
     Imagine there is a champion with 5 different roles, instead of showing up 5 times this champ, it will appear once. */
    List<ChampionEntity> findDistinctByRegion(@Param("region") String region);

    @Query(value = "SELECT c.* FROM champions c WHERE LOWER(c.class_type) = LOWER(:classType)",
            nativeQuery = true)
    List<ChampionEntity> findByClassType(@Param("classType") String classType);

    Optional<ChampionEntity> findByName(String name); // used on service, method: updateChampion


    boolean existsByNameAndRole(String name, String role);
}
