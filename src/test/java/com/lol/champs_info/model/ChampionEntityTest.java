package com.lol.champs_info.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ChampionEntityTest {

    @Test
    void shouldCreateChampionWithAllFields() {
        UUID id = UUID.randomUUID();
        String name = "Ashe";
        String region = "Freljord";
        String classType = "Marksman";
        String role = "ADC";
        String tier = "S";
        Double score = 9.5;
        Double trend = 1.2;
        Double winRate = 52.3;
        Double roleRate = 85.0;
        Double pickRate = 15.7;
        Double banRate = 5.2;
        Double kda = 3.8;

        ChampionEntity champion = new ChampionEntity(
                id, name, region, classType, role, tier,
                score, trend, winRate, roleRate, pickRate, banRate, kda
        );

        assertNotNull(champion);
        assertEquals(id, champion.getId());
        assertEquals(name, champion.getName());
        assertEquals(region, champion.getRegion());
        assertEquals(classType, champion.getClassType());
        assertEquals(role, champion.getRole());
        assertEquals(tier, champion.getTier());
        assertEquals(score, champion.getScore());
        assertEquals(trend, champion.getTrend());
        assertEquals(winRate, champion.getWinRate());
        assertEquals(roleRate, champion.getRoleRate());
        assertEquals(pickRate, champion.getPickRate());
        assertEquals(banRate, champion.getBanRate());
        assertEquals(kda, champion.getKda());
    }

    @Test
    void shouldCreateChampionWithNoArgsConstructor() {
        ChampionEntity champion = new ChampionEntity();
        assertNotNull(champion);
        assertNull(champion.getName());
        assertNull(champion.getRegion());
    }

    @Test
    void shouldSetAndGetFieldsProperly() {
        ChampionEntity champion = new ChampionEntity();
        String name = "Zed";
        champion.setName(name);
        assertEquals(name, champion.getName());
    }

    @Test
    void equalsAndHashCodeShouldWorkCorrectly() {
        // Arrange
        UUID id = UUID.randomUUID();
        ChampionEntity champion1 = new ChampionEntity(id, "Ahri", "Ionia", "Mage", "Mid", "A",
                8.5, 0.5, 51.2, 90.0, 12.3, 8.7, 3.2);
        ChampionEntity champion2 = new ChampionEntity(id, "Ahri", "Ionia", "Mage", "Mid", "A",
                8.5, 0.5, 51.2, 90.0, 12.3, 8.7, 3.2);
        assertEquals(champion1, champion2);
        assertEquals(champion1.hashCode(), champion2.hashCode());
    }

    @Test
    void toStringShouldContainRelevantInformation() {
        // Arrange
        ChampionEntity champion = new ChampionEntity(null, "Jinx", "Zaun", "Marksman", "ADC", "S+",
                9.8, 1.5, 53.1, 95.0, 18.2, 12.4, 4.0);

        String result = champion.toString();
        assertTrue(result.contains("Jinx"));
        assertTrue(result.contains("Zaun"));
        assertTrue(result.contains("ADC"));
    }
}
