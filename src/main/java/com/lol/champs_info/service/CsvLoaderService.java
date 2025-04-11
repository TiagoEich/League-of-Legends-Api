package com.lol.champs_info.service;

import com.lol.champs_info.model.ChampionsEntity;
import com.lol.champs_info.repository.ChampionsRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;

@Service
public class CsvLoaderService implements CommandLineRunner {

    //This is required, without the repository, JPA will not be able to connect this Csv class, to Postgres
    private final ChampionsRepository repository;

    public CsvLoaderService(ChampionsRepository repository) {
        this.repository = repository;
    }

    @Override // method required from CommandLineRunner. It comes from default.
    public void run(String... args) throws Exception {
        String file = "src/main/resources/lolchamps.csv";
        importChampionsFromCsv(file);
    }

    public void importChampionsFromCsv(String file) {
        try (FileReader reader = new FileReader(file)) {

            //It's a mapping strategy that makes the matching between the columns from the CSV file and atributes from the class
            HeaderColumnNameMappingStrategy<ChampionsEntity> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ChampionsEntity.class); //tells the mapper which is the class will be used to do the
            //mapping


            CsvToBean<ChampionsEntity> csvToBean = new CsvToBeanBuilder<ChampionsEntity>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true) //This will ignore white Spaces, such as: " Aatrox" = "Aatrox"
                    .build();

            List<ChampionsEntity> champions = csvToBean.parse(); //convert the rows of the CSV file in objects for Java
            repository.saveAll(champions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
