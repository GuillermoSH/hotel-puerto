package com.docencia.hotel_puerto.persistence.repository.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractJpaRepositoryTest {

    // Necesitamos una implementación concreta para probar la clase abstracta
    private static class TestEntity {}

    private static class TestRepositoryImpl extends AbstractJpaRepository<TestEntity, Long> {
        protected TestRepositoryImpl(JpaRepository<TestEntity, Long> repository) {
            super(repository);
        }
    }

    @Mock
    private JpaRepository<TestEntity, Long> jpaRepository;

    private TestRepositoryImpl repository;
    private TestEntity entity;

    @BeforeEach
    void setUp() {
        repository = new TestRepositoryImpl(jpaRepository);
        entity = new TestEntity();
    }

    @Test
    @DisplayName("Debe verificar si existe por ID")
    void testExistsById() {
        when(jpaRepository.existsById(1L)).thenReturn(true);

        boolean exists = repository.existsById(1L);

        assertTrue(exists);
        verify(jpaRepository).existsById(1L);
    }

    @Test
    @DisplayName("Debe buscar por ID")
    void testFindById() {
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<TestEntity> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
        verify(jpaRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe listar todos")
    void testFindAll() {
        when(jpaRepository.findAll()).thenReturn(Collections.singletonList(entity));

        List<TestEntity> result = repository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(jpaRepository).findAll();
    }

    @Test
    @DisplayName("Debe guardar una entidad")
    void testSave() {
        when(jpaRepository.save(entity)).thenReturn(entity);

        TestEntity saved = repository.save(entity);

        assertNotNull(saved);
        verify(jpaRepository).save(entity);
    }

    @Test
    @DisplayName("Debe eliminar por ID")
    void testDeleteById() {
        doNothing().when(jpaRepository).deleteById(1L);

        repository.deleteById(1L);

        verify(jpaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe eliminar una entidad")
    void testDelete() {
        doNothing().when(jpaRepository).delete(entity);

        repository.delete(entity);

        verify(jpaRepository).delete(entity);
    }

    @Test
    @DisplayName("Debe eliminar todo")
    void testDeleteAll() {
        doNothing().when(jpaRepository).deleteAll();

        repository.deleteAll();

        verify(jpaRepository).deleteAll();
    }
}