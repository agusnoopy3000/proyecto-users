package com.usuarios.users.ServiceTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.usuarios.users.Model.Infraestructura;
import com.usuarios.users.Repository.InfraestructuraRepository;
import org.junit.jupiter.api.Test;
import com.usuarios.users.Service.InfraestructuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class InfraestructuraServiceTest {

    @Autowired
    private InfraestructuraService infraestructuraService;

    @MockBean
    private InfraestructuraRepository infraestructuraRepository;

    @Test
    void testListarInfraestructuras() {
        when(infraestructuraRepository.findAll()).thenReturn(List.of(
            new Infraestructura(1, "Gamer", "Potente", null)
        ));

        List<Infraestructura> lista = infraestructuraService.obtenerTodos();
        assertEquals(1, lista.size());
    }

    @Test
    void testGuardarInfraestructura() {
        Infraestructura i = new Infraestructura(1, "Gamer", "Potente", null);
        when(infraestructuraRepository.save(i)).thenReturn(i);

        Infraestructura resultado = infraestructuraService.guardar(i);
        assertNotNull(resultado);
        assertEquals("Gamer", resultado.getNombre());
    }
}