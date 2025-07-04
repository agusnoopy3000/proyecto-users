package com.usuarios.users.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.users.Controller.UsuarioController;
import com.usuarios.users.Model.Usuarios;
import com.usuarios.users.Service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@ActiveProfiles("test")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioServicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuarios usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuarios();
        usuario.setId(1);
        usuario.setNombre_usuario("agustin123");
        usuario.setCorreo("agustin@test.com");
        usuario.setPnombre("Agustín");
        usuario.setPapellido("Garrido");
    }

    @Test
    void testListarUsuarios() throws Exception {
        when(usuarioServicio.obtenerTodos()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/usuarios"))  // ✅ ruta correcta
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre_usuario").value("agustin123"));
    }

    @Test
    void testObtenerUsuarioPorId() throws Exception {
        when(usuarioServicio.obtenerPorId(1)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/1"))  // ✅ ruta correcta
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("agustin@test.com"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        when(usuarioServicio.guardar(any(Usuarios.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")  // ✅ ruta correcta
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_usuario").value("agustin123"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        doNothing().when(usuarioServicio).eliminar(1);

        mockMvc.perform(delete("/api/usuarios/1"))  // ✅ ruta correcta
                .andExpect(status().isOk());

        verify(usuarioServicio, times(1)).eliminar(1);
    }
}
