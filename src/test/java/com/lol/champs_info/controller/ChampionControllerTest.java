package com.lol.champs_info.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.service.ChampionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChampionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ChampionService championService;

    @InjectMocks
    private ChampionController championController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(championController).build();
    }

    @Test
    void getAllChampsName_ShouldReturnListOfNames() throws Exception {
        // Given
        List<String> championNames = Arrays.asList("Aatrox", "Ahri", "Akali");
        when(championService.getChampionsName()).thenReturn(championNames);

        // When & Then
        mockMvc.perform(get("/names"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(championNames)));
    }

    @Test
    void getByRegion_ShouldReturnChampions_WhenValidRegion() throws Exception {
        // Given
        List<ChampionEntity> demaciansChampions = Arrays.asList(
                createTestChampion("Garen", "Demacia"),
                createTestChampion("Lux", "Demacia")
        );
        when(championService.getChampionsFromRegion("Demacia")).thenReturn(demaciansChampions);

        // When & Then
        mockMvc.perform(get("/region/Demacia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(demaciansChampions)));
    }

    @Test
    void getByRegion_ShouldReturnBadRequest_WhenInvalidRegion() throws Exception {
        // Given
        String errorMessage = "Invalid region: Unknown";
        when(championService.getChampionsFromRegion("Unknown")).thenThrow(new IllegalArgumentException(errorMessage));

        // When & Then
        mockMvc.perform(get("/region/Unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void getChampionsByClass_ShouldReturnChampions_WhenValidClass() throws Exception {
        // Given
        List<ChampionEntity> fighterChampions = Arrays.asList(
                createTestChampion("Darius", "Noxus", "Fighter"),
                createTestChampion("Garen", "Demacia", "Fighter")
        );
        when(championService.getChampionsByClass("Fighter")).thenReturn(fighterChampions);

        // When & Then
        mockMvc.perform(get("/class/Fighter"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(fighterChampions)));
    }

    @Test
    void getChampionsByClass_ShouldReturnBadRequest_WhenInvalidClass() throws Exception {
        // Given
        String errorMessage = "Invalid class type: Unknown";
        when(championService.getChampionsByClass("Unknown")).thenThrow(new IllegalArgumentException(errorMessage));

        // When & Then
        mockMvc.perform(get("/class/Unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void getChampionsByRole_ShouldReturnChampions_WhenValidRole() throws Exception {
        // Given
        List<ChampionEntity> topChampions = Arrays.asList(
                createTestChampion("Darius", "Noxus", "Fighter", "TOP"),
                createTestChampion("Garen", "Demacia", "Fighter", "TOP")
        );
        when(championService.getChampionsByRole("TOP")).thenReturn(topChampions);

        // When & Then
        mockMvc.perform(get("/role/TOP"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(topChampions)));
    }

    @Test
    void getChampionsByRole_ShouldReturnBadRequest_WhenInvalidRole() throws Exception {
        // Given
        String errorMessage = "Invalid role: Unknown";
        when(championService.getChampionsByRole("Unknown")).thenThrow(new IllegalArgumentException(errorMessage));

        // When & Then
        mockMvc.perform(get("/role/Unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void getChampionsByTier_ShouldReturnChampions_WhenValidTier() throws Exception {
        // Given
        List<ChampionEntity> tierSChampions = Arrays.asList(
                createTestChampion("Darius", "Noxus", "Fighter", "TOP", "S"),
                createTestChampion("Ahri", "Ionia", "Mage", "MID", "S")
        );
        when(championService.getChampionsByTier("S")).thenReturn(tierSChampions);

        // When & Then
        mockMvc.perform(get("/tier/S"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(tierSChampions)));
    }

    @Test
    void getChampionsByTier_ShouldReturnBadRequest_WhenInvalidTier() throws Exception {
        // Given
        String errorMessage = "Invalid tier: Unknown";
        when(championService.getChampionsByTier("Unknown")).thenThrow(new IllegalArgumentException(errorMessage));

        // When & Then
        mockMvc.perform(get("/tier/Unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void createChampion_ShouldReturnCreatedChampion_WhenValidInput() throws Exception {
        // Given
        ChampionEntity newChampion = createTestChampion("Yone", "Ionia", "Fighter", "MID", "A");
        when(championService.addChampion(any(ChampionEntity.class))).thenReturn(newChampion);

        // When & Then
        mockMvc.perform(post("/addChampion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newChampion)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(newChampion)));
    }

    @Test
    void createChampion_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        // Given
        ChampionEntity invalidChampion = new ChampionEntity();
        String errorMessage = "Champion name cannot be null or empty";
        when(championService.addChampion(any(ChampionEntity.class))).thenThrow(new IllegalArgumentException(errorMessage));

        // When & Then
        mockMvc.perform(post("/addChampion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidChampion)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void updateChampion_ShouldReturnUpdatedChampion_WhenChampionExists() throws Exception {
        // Given
        ChampionEntity updatedChampion = createTestChampion("Ahri", "Ionia", "Mage", "MID", "A");
        updatedChampion.setWinRate(53.5);
        when(championService.updateChampion(any(ChampionEntity.class))).thenReturn(updatedChampion);

        // When & Then
        mockMvc.perform(put("/champions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedChampion)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedChampion)));
    }

    @Test
    void updateChampion_ShouldReturnNotFound_WhenChampionDoesNotExist() throws Exception {
        // Given
        ChampionEntity nonExistentChampion = createTestChampion("NonExistent", "Unknown");
        when(championService.updateChampion(any(ChampionEntity.class))).thenReturn(null);

        // When & Then
        mockMvc.perform(put("/champions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nonExistentChampion)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteByNameAndRole_ShouldReturnOk_WhenChampionExists() throws Exception {
        // Given
        when(championService.deleteByNameAndRole("Ahri", "MID")).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/deleteChampion")
                        .param("name", "Ahri")
                        .param("role", "MID"))
                .andExpect(status().isOk())
                .andExpect(content().string("Champion with name 'Ahri' and role 'MID' was deleted successfully"));
    }

    @Test
    void deleteByNameAndRole_ShouldReturnNotFound_WhenChampionDoesNotExist() throws Exception {
        // Given
        when(championService.deleteByNameAndRole("NonExistent", "TOP")).thenReturn(false);

        // When & Then
        mockMvc.perform(delete("/deleteChampion")
                        .param("name", "NonExistent")
                        .param("role", "TOP"))
                .andExpect(status().isNotFound());
    }

    // Método auxiliar para criar instâncias de teste de ChampionEntity
    private ChampionEntity createTestChampion(String name, String region) {
        ChampionEntity champion = new ChampionEntity();
        champion.setId(UUID.randomUUID());
        champion.setName(name);
        champion.setRegion(region);
        champion.setClassType("Fighter");
        champion.setRole("TOP");
        champion.setTier("S");
        champion.setScore(8.5);
        champion.setTrend(0.3);
        champion.setWinRate(52.5);
        champion.setRoleRate(92.1);
        champion.setPickRate(10.2);
        champion.setBanRate(5.5);
        champion.setKda(2.1);
        return champion;
    }

    private ChampionEntity createTestChampion(String name, String region, String classType) {
        ChampionEntity champion = createTestChampion(name, region);
        champion.setClassType(classType);
        return champion;
    }

    private ChampionEntity createTestChampion(String name, String region, String classType, String role) {
        ChampionEntity champion = createTestChampion(name, region, classType);
        champion.setRole(role);
        return champion;
    }

    private ChampionEntity createTestChampion(String name, String region, String classType, String role, String tier) {
        ChampionEntity champion = createTestChampion(name, region, classType, role);
        champion.setTier(tier);
        return champion;
    }
}
