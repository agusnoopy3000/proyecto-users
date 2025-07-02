package com.usuarios.users.ServiceTest;
import com.usuarios.users.Model.Usuarios;
import com.usuarios.users.Repository.UsuarioRepository;
import com.usuarios.users.Service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void testObtenerTodos() {
        Usuarios u1 = new Usuarios();
        Usuarios u2 = new Usuarios();

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuarios> result = usuarioService.obtenerTodos();

        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        usuario.setCorreo("test@example.com");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuarios> result = usuarioService.obtenerPorId(1);

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getCorreo());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testGuardar() {
        Usuarios usuario = new Usuarios();
        usuario.setCorreo("nuevo@correo.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuarios result = usuarioService.guardar(usuario);

        assertNotNull(result);
        assertEquals("nuevo@correo.com", result.getCorreo());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testEliminar() {
        int id = 1;

        usuarioService.eliminar(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
