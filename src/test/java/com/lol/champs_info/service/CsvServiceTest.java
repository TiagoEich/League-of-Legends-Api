package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CsvServiceTest {

    @Mock
    private ChampionRepository championRepository;

    @Captor
    private ArgumentCaptor<List<ChampionEntity>> championsCaptor;

    private CsvService csvService;

    @TempDir
    Path tempDir;

    private File testCsvFile;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        csvService = new CsvService(championRepository);

        testCsvFile = tempDir.resolve("test-champions.csv").toFile();
        try (FileWriter writer = new FileWriter(testCsvFile)) {
            writer.write("name,region,classType,role,tier,score,trend,winRate,roleRate,pickRate,banRate,kda\n");
            writer.write("Aatrox,Runeterra,Fighter,TOP,S,8.5,0.3,52.5,92.1,10.2,5.5,2.1\n");
            writer.write("Ahri,Ionia,Mage,MID,A,7.8,0.1,51.2,88.5,8.7,2.3,2.8\n");
        }
    }

    @Test
    void shouldImportChampionsFromCsv() throws Exception {
        when(championRepository.count()).thenReturn(0L);
        csvService.importChampionsFromCsv(testCsvFile.getAbsolutePath());
        verify(championRepository).saveAll(championsCaptor.capture());
        List<ChampionEntity> capturedChampions = championsCaptor.getValue();

        assertEquals(2, capturedChampions.size());

        ChampionEntity firstChampion = capturedChampions.get(0);
        assertEquals("Aatrox", firstChampion.getName());
        assertEquals("Runeterra", firstChampion.getRegion());
        assertEquals("Fighter", firstChampion.getClassType());
        assertEquals("TOP", firstChampion.getRole());
        assertEquals("S", firstChampion.getTier());
        assertEquals(8.5, firstChampion.getScore());
        assertEquals(0.3, firstChampion.getTrend());
        assertEquals(52.5, firstChampion.getWinRate());
        assertEquals(92.1, firstChampion.getRoleRate());
        assertEquals(10.2, firstChampion.getPickRate());
        assertEquals(5.5, firstChampion.getBanRate());
        assertEquals(2.1, firstChampion.getKda());

        ChampionEntity secondChampion = capturedChampions.get(1);
        assertEquals("Ahri", secondChampion.getName());
        assertEquals("Ionia", secondChampion.getRegion());
        assertEquals("Mage", secondChampion.getClassType());
    }

    @Test
    void shouldNotImportChampionsWhenRepositoryIsNotEmpty() throws Exception {
        when(championRepository.count()).thenReturn(10L);
        csvService.run();
        verify(championRepository, never()).saveAll(any());
    }

    @Test
    void shouldImportChampionsOnRunWhenRepositoryIsEmpty() throws Exception {
        when(championRepository.count()).thenReturn(0L);
        CsvService spyService = spy(csvService);
        doNothing().when(spyService).importChampionsFromCsv(anyString());
        spyService.run();
        verify(spyService).importChampionsFromCsv(contains("lolchamps.csv"));
    }

    @Test
    void shouldHandleExceptionWhenFileNotFound() {
        String nonExistentFile = "non-existent-file.csv";
        assertDoesNotThrow(() -> csvService.importChampionsFromCsv(nonExistentFile));
        verify(championRepository, never()).saveAll(any());
    }
}