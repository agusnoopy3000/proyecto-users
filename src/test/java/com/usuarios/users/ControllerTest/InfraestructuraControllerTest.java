package com.usuarios.users.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.usuarios.users.Controller.InfraestructuraController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.users.Model.Infraestructura;
import com.usuarios.users.Service.InfraestructuraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(InfraestructuraController.class)
public class InfraestructuraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfraestructuraService infraestructuraService;

    @Autowired
    private ObjectMapper objectMapper;

    private Infraestructura infraestructura;

    @BeforeEach
    void setUp() {
        infraestructura = new Infraestructura();
        infraestructura.setId(1);
        infraestructura.setNombre("Máquina Gamer");
        infraestructura.setDetalle("RTX 4090 y 64GB RAM");
    }

    @Test
    void testListarInfraestructuras() throws Exception {
        when(infraestructuraService.obtenerTodos()).thenReturn(List.of(infraestructura));

        mockMvc.perform(get("/api/infraestructura"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Máquina Gamer"));
    }

    @Test
    void testCrearInfraestructura() throws Exception {
        when(infraestructuraService.guardar(any(Infraestructura.class))).thenReturn(infraestructura);

        mockMvc.perform(post("/api/infraestructura")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(infraestructura)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Máquina Gamer"));
    }

    @Test
    void testActualizarInfraestructura() throws Exception {
        when(infraestructuraService.guardar(any(Infraestructura.class))).thenReturn(infraestructura);

        mockMvc.perform(put("/api/infraestructura/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(infraestructura)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Máquina Gamer"));
    }
}