package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import com.lol.champs_info.validators.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChampionServiceTest {

    @Mock
    private ChampionRepository championRepository;

    @Mock
    private AddChampionValidator championValidator;

    @Mock
    private TierValidator tierValidator;

    @Mock
    private RoleValidator roleValidator;

    @Mock
    private RegionValidator regionValidator;

    @Mock
    private ClassTypeValidator classTypeValidator;

    @InjectMocks
    private ChampionService championService;

    private ChampionEntity createChampion(String name, String classType, String role, String tier, String region) {
        ChampionEntity champion = new ChampionEntity();
        champion.setName(name);
        champion.setClassType(classType);
        champion.setRole(role);
        champion.setTier(tier);
        champion.setRegion(region);
        return champion;
    }

    @Test
    void whenGetChampionsName_thenReturnListOfNames() {
        List<String> expectedNames = Arrays.asList("Aatrox", "Ahri", "Zed");
        when(championRepository.findNames()).thenReturn(expectedNames);

        List<String> result = championService.getChampionsName();

        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder("Aatrox", "Ahri", "Zed");
        verify(championRepository).findNames();
    }

    @Test
    void whenGetChampionsFromIonia_thenReturnIonianChampions() {
        // Arrange
        String region = "Ionia";
        ChampionEntity ahri = createChampion("Ahri", "Mage", "MID", "A", region);
        ChampionEntity zed = createChampion("Zed", "Assassin", "MID", "S+", region);

        doNothing().when(regionValidator).validateRegion(region);
        when(championRepository.findDistinctByRegion(region)).thenReturn(Arrays.asList(ahri, zed));

        List<ChampionEntity> result = championService.getChampionsFromRegion(region);

        assertThat(result)
                .hasSize(2)
                .extracting(ChampionEntity::getRegion)
                .containsOnly(region);
        verify(regionValidator).validateRegion(region);
        verify(championRepository).findDistinctByRegion(region);
    }

    @Test
    void whenGetMageClassChampions_thenReturnMages() {
        String classType = "Mage";
        ChampionEntity ahri = createChampion("Ahri", classType, "MID", "A", "Ionia");
        ChampionEntity lux = createChampion("Lux", classType, "MID", "S", "Demacia");

        doNothing().when(classTypeValidator).validateClass(classType);
        when(championRepository.findByClassType(classType)).thenReturn(Arrays.asList(ahri, lux));

        List<ChampionEntity> result = championService.getChampionsByClass(classType);

        assertThat(result)
                .hasSize(2)
                .extracting(ChampionEntity::getClassType)
                .containsOnly(classType);
        verify(classTypeValidator).validateClass(classType);
        verify(championRepository).findByClassType(classType);
    }

    @Test
    void whenGetMidLaneChampions_thenReturnMidLaners() {
        String role = "MID";
        ChampionEntity ahri = createChampion("Ahri", "Mage", role, "A", "Ionia");
        ChampionEntity zed = createChampion("Zed", "Assassin", role, "S+", "Ionia");

        doNothing().when(roleValidator).validation(role);
        when(championRepository.findAll()).thenReturn(Arrays.asList(ahri, zed));

        List<ChampionEntity> result = championService.getChampionsByRole(role);

        assertThat(result)
                .hasSize(2)
                .extracting(ChampionEntity::getRole)
                .containsOnly(role);
        verify(roleValidator).validation(role);
        verify(championRepository).findAll();
    }

    @Test
    void whenGetSTierChampions_thenReturnTopTierChampions() {
        String tier = "S";
        ChampionEntity lux = createChampion("Lux", "Mage", "MID", tier, "Demacia");
        ChampionEntity diana = createChampion("Diana", "Fighter", "JUNGLE", tier, "Targon");

        doNothing().when(tierValidator).validate(tier);
        when(championRepository.findAll()).thenReturn(Arrays.asList(lux, diana));

        List<ChampionEntity> result = championService.getChampionsByTier(tier);

        assertThat(result)
                .hasSize(2)
                .extracting(ChampionEntity::getTier)
                .containsOnly(tier);
        verify(tierValidator).validate(tier);
        verify(championRepository).findAll();
    }

    @Test
    void whenAddNewChampion_thenReturnSavedChampion() {

        ChampionEntity newChamp = createChampion("NewChamp", "Fighter", "TOP", "B", "Noxus");
        doNothing().when(championValidator).validate(newChamp);
        when(championRepository.save(newChamp)).thenReturn(newChamp);

        ChampionEntity result = championService.addChampion(newChamp);

        assertThat(result).isEqualTo(newChamp);
        verify(championValidator).validate(newChamp);
        verify(championRepository).save(newChamp);
    }

    @Test
    void whenUpdateExistingChampion_thenReturnUpdatedChampion() {
        ChampionEntity existing = createChampion("Ahri", "Mage", "MID", "A", "Ionia");
        ChampionEntity updated = createChampion("Ahri", "Mage", "MID", "S", "Ionia");

        when(championRepository.findByName("Ahri")).thenReturn(Optional.of(existing));
        when(championRepository.save(any(ChampionEntity.class))).thenReturn(updated);

        ChampionEntity result = championService.updateChampion(updated);

        assertThat(result.getTier()).isEqualTo("S");
        verify(championRepository).findByName("Ahri");
        verify(championRepository).save(any(ChampionEntity.class));
    }

    @Test
    void whenUpdateNonExistingChampion_thenReturnNull() {
        ChampionEntity nonExisting = createChampion("NonExisting", "Mage", "MID", "S", "Ionia");
        when(championRepository.findByName("NonExisting")).thenReturn(Optional.empty());

        ChampionEntity result = championService.updateChampion(nonExisting);

        assertThat(result).isNull();
        verify(championRepository).findByName("NonExisting");
    }

    @Test
    void whenDeleteExistingChampionByNameAndRole_thenReturnTrue() {
        ChampionEntity ahriMid = createChampion("Ahri", "Mage", "MID", "A", "Ionia");
        ChampionEntity ahriTop = createChampion("Ahri", "Mage", "TOP", "B", "Ionia");

        when(championRepository.findAll()).thenReturn(Arrays.asList(ahriMid, ahriTop));

        boolean result = championService.deleteByNameAndRole("Ahri", "MID");

        assertThat(result).isTrue();
        verify(championRepository).deleteAll(List.of(ahriMid));
    }

    @Test
    void whenDeleteNonExistingChampionByNameAndRole_thenReturnFalse() {
        when(championRepository.findAll()).thenReturn(List.of());

        boolean result = championService.deleteByNameAndRole("NonExisting", "MID");

        assertThat(result).isFalse();
        verify(championRepository).findAll();
    }
}
