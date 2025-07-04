package com.usuarios.users.ServiceTest;
import com.usuarios.users.Model.Suscripcion;
import com.usuarios.users.Repository.SuscripcionRepository;
import com.usuarios.users.Service.SuscripcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class SuscripcionServiceTest {

    private SuscripcionRepository subRepository;
    private SuscripcionService subService;

    @BeforeEach
    void setUp() {
        subRepository = mock(SuscripcionRepository.class);
        subService = new SuscripcionService(subRepository);
    }

    @Test
    void testObtenerTodos() {
        Suscripcion s1 = new Suscripcion();
        Suscripcion s2 = new Suscripcion();

        when(subRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Suscripcion> result = subService.obtenerTodos();

        assertEquals(2, result.size());
        verify(subRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Suscripcion s = new Suscripcion();
        s.setId(1);

        when(subRepository.findById(1)).thenReturn(Optional.of(s));

        Optional<Suscripcion> result = subService.obtenerPorId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        verify(subRepository, times(1)).findById(1);
    }

    @Test
    void testGuardar() {
        Suscripcion s = new Suscripcion();
        s.setNombre("Premium");

        when(subRepository.save(s)).thenReturn(s);

        Suscripcion result = subService.guardar(s);

        assertNotNull(result);
        assertEquals("Premium", result.getNombre());
        verify(subRepository, times(1)).save(s);
    }

    @Test
    void testEliminar() {
        int id = 1;

        subService.eliminar(id);

        verify(subRepository, times(1)).deleteById(id);
    }
}
