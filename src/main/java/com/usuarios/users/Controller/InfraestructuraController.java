package com.usuarios.users.Controller;

import com.usuarios.users.Model.Infraestructura;
import com.usuarios.users.Service.InfraestructuraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/infraestructura")
@RequiredArgsConstructor
public class InfraestructuraController {

    private final InfraestructuraService infraestructuraService;

    @GetMapping
    public List<Infraestructura> obtenerTodos() {
        return infraestructuraService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Infraestructura obtenerPorId(@PathVariable Integer id) {
        return infraestructuraService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Infraestructura crear(@RequestBody Infraestructura infraestructura) {
        return infraestructuraService.guardar(infraestructura);
    }

    @PutMapping("/{id}")
    public Infraestructura actualizar(@PathVariable Integer id, @RequestBody Infraestructura infraestructura) {
        infraestructura.setId(id);
        return infraestructuraService.guardar(infraestructura);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        infraestructuraService.eliminar(id);
    }
}
