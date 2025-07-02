package com.usuarios.users.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.users.Controller.JuegoController;
import com.usuarios.users.Model.Juegos;
import com.usuarios.users.Service.JuegosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JuegoController.class)
public class JuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JuegosService juegoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Juegos juego;

    @BeforeEach
    void setUp() {
        juego = new Juegos();
        juego.setId(1);
        juego.setNombre("Minecraft");
        juego.setPlataforma("PC");
    }

    @Test
    public void testListarJuegos() throws Exception {
        when(juegoService.obtenerTodos()).thenReturn(List.of(juego));

        mockMvc.perform(get("/api/juegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Minecraft"));
    }

    @Test
    public void testCrearJuego() throws Exception {
        when(juegoService.guardar(any(Juegos.class))).thenReturn(juego);

        mockMvc.perform(post("/api/juegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(juego)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plataforma").value("PC"));
    }

}