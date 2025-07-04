package com.usuarios.users.ServiceTest;
import com.usuarios.users.Model.Juegos;
import com.usuarios.users.Repository.JuegoRepository;
import com.usuarios.users.Service.JuegosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class JuegosServiceTest {

    private JuegoRepository juegoRepository;
    private JuegosService juegoService;

    @BeforeEach
    void setUp() {
        juegoRepository = mock(JuegoRepository.class);
        juegoService = new JuegosService(juegoRepository);
    }

    @Test
    void testObtenerTodos() {
        Juegos j1 = new Juegos();
        Juegos j2 = new Juegos();
        when(juegoRepository.findAll()).thenReturn(Arrays.asList(j1, j2));

        List<Juegos> result = juegoService.obtenerTodos();

        assertEquals(2, result.size());
        verify(juegoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Juegos juego = new Juegos();
        juego.setId(1);
        when(juegoRepository.findById(1)).thenReturn(Optional.of(juego));

        Optional<Juegos> result = juegoService.obtenerPorId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        verify(juegoRepository, times(1)).findById(1);
    }

    @Test
    void testGuardar() {
        Juegos juego = new Juegos();
        juego.setNombre("Minecraft");

        when(juegoRepository.save(juego)).thenReturn(juego);

        Juegos result = juegoService.guardar(juego);

        assertNotNull(result);
        assertEquals("Minecraft", result.getNombre());
        verify(juegoRepository, times(1)).save(juego);
    }

    @Test
    void testEliminar() {
        int id = 1;

        juegoService.eliminar(id);

        verify(juegoRepository, times(1)).deleteById(id);
    }
}
