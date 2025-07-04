package com.usuarios.users.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.users.Controller.SuscripcionController;
import com.usuarios.users.Model.Suscripcion;
import com.usuarios.users.Service.SuscripcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ActiveProfiles("test")
@WebMvcTest(SuscripcionController.class)
public class SuscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuscripcionService suscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Suscripcion suscripcion;

    @BeforeEach
    void setUp() {
        suscripcion = new Suscripcion();
        suscripcion.setId(1);
        suscripcion.setNombre("Mensual");
        suscripcion.setDuracion("30 días");
    }

    @Test
    public void testListarSuscripciones() throws Exception {
        when(suscripcionService.obtenerTodos()).thenReturn(List.of(suscripcion));

        mockMvc.perform(get("/api/suscripciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Mensual"));
    }

    @Test
    public void testCrearSuscripcion() throws Exception {
        when(suscripcionService.guardar(any(Suscripcion.class))).thenReturn(suscripcion);

        mockMvc.perform(post("/api/suscripciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mensual"));
    }

}