package com.lol.champs_info.validators;

import com.lol.champs_info.model.ChampionEntity;
import com.lol.champs_info.repository.ChampionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class AddChampionValidatorTest {

    private AddChampionValidator validator;
    private TestChampionRepository championRepository;

    @BeforeEach
    void setUp() {
        championRepository = new TestChampionRepository();
        validator = new AddChampionValidator();

        try {
            java.lang.reflect.Field field = AddChampionValidator.class.getDeclaredField("championRepository");
            field.setAccessible(true);
            field.set(validator, championRepository);
        } catch (Exception e) {
            fail("Could not set repository field: " + e.getMessage());
        }
    }

    @Test
    public void testValidateWithValidChampion() {
        ChampionEntity champion = new ChampionEntity();
        champion.setName("Ahri");
        champion.setRole("MID");

        assertDoesNotThrow(() -> validator.validate(champion));
    }

    @Test
    public void testValidateWithExistingChampion() {
        championRepository.setExistsByNameAndRoleResult(true);

        ChampionEntity champion = new ChampionEntity();
        champion.setName("Ahri");
        champion.setRole("MID");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(champion)
        );

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    public void testValidateWithNullName() {
        ChampionEntity champion = new ChampionEntity();
        champion.setName(null);
        champion.setRole("MID");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(champion)
        );

        assertEquals("Champion name is required", exception.getMessage());
    }

    @Test
    public void testValidateWithEmptyName() {
        ChampionEntity champion = new ChampionEntity();
        champion.setName("  ");
        champion.setRole("MID");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(champion)
        );

        assertEquals("Champion name is required", exception.getMessage());
    }

    @Test
    public void testValidateWithNullRole() {
        ChampionEntity champion = new ChampionEntity();
        champion.setName("Ahri");
        champion.setRole(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(champion)
        );

        assertEquals("Champion role is required", exception.getMessage());
    }

    private static class TestChampionRepository implements ChampionRepository {
        private boolean existsByNameAndRoleResult = false;

        public void setExistsByNameAndRoleResult(boolean result) {
            this.existsByNameAndRoleResult = result;
        }

        @Override
        public List<String> findNames() {
            return List.of();
        }

        @Override
        public List<ChampionEntity> findDistinctByRegion(String region) {
            return List.of();
        }

        @Override
        public List<ChampionEntity> findByClassType(String classType) {
            return List.of();
        }

        @Override
        public Optional<ChampionEntity> findByName(String name) {
            return Optional.empty();
        }

        @Override
        public boolean existsByNameAndRole(String name, String role) {
            return existsByNameAndRoleResult;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends ChampionEntity> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends ChampionEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<ChampionEntity> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public ChampionEntity getOne(UUID uuid) {
            return null;
        }

        @Override
        public ChampionEntity getById(UUID uuid) {
            return null;
        }

        @Override
        public ChampionEntity getReferenceById(UUID uuid) {
            return null;
        }

        @Override
        public <S extends ChampionEntity> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends ChampionEntity> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends ChampionEntity> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends ChampionEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends ChampionEntity> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends ChampionEntity> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends ChampionEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public <S extends ChampionEntity> S save(S entity) {
            return null;
        }

        @Override
        public <S extends ChampionEntity> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public Optional<ChampionEntity> findById(UUID uuid) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(UUID uuid) {
            return false;
        }

        @Override
        public List<ChampionEntity> findAll() {
            return List.of();
        }

        @Override
        public List<ChampionEntity> findAllById(Iterable<UUID> uuids) {
            return List.of();
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(UUID uuid) {

        }

        @Override
        public void delete(ChampionEntity entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends UUID> uuids) {

        }

        @Override
        public void deleteAll(Iterable<? extends ChampionEntity> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<ChampionEntity> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<ChampionEntity> findAll(Pageable pageable) {
            return null;
        }
    }
}