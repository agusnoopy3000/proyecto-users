package com.usuarios.users.Controller;

import com.usuarios.users.Model.Suscripcion;
import com.usuarios.users.Service.SuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/suscripciones")
@RequiredArgsConstructor
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    @GetMapping
    public List<Suscripcion> obtenerTodos() {
        return suscripcionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Suscripcion obtenerPorId(@PathVariable Integer id) {
        return suscripcionService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Suscripcion crear(@RequestBody Suscripcion suscripcion) {
        return suscripcionService.guardar(suscripcion);
    }

    @PutMapping("/{id}")
    public Suscripcion actualizar(@PathVariable Integer id, @RequestBody Suscripcion suscripcion) {
        suscripcion.setId(id);
        return suscripcionService.guardar(suscripcion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        suscripcionService.eliminar(id);
    }
}
