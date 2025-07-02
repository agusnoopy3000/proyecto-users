package com.usuarios.users.Controller;

import com.usuarios.users.Model.Juegos;
import com.usuarios.users.Service.JuegosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/juegos")
@RequiredArgsConstructor
public class JuegoController {

    private final JuegosService juegoService;

    @GetMapping
    public List<Juegos> obtenerTodos() {
        return juegoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Juegos obtenerPorId(@PathVariable Integer id) {
        return juegoService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Juegos crear(@RequestBody Juegos juego) {
        return juegoService.guardar(juego);
    }

    @PutMapping("/{id}")
    public Juegos actualizar(@PathVariable Integer id, @RequestBody Juegos juego) {
        juego.setId(id);
        return juegoService.guardar(juego);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        juegoService.eliminar(id);
    }
}
