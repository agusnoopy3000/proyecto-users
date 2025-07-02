package com.usuarios.users.Controller;

import com.usuarios.users.Assamblers.UsuarioModelAssembler;
import com.usuarios.users.Model.Usuarios;
import com.usuarios.users.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/api/v2/usuarios", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Usuarios>> obtenerTodos() {
        List<EntityModel<Usuarios>> usuarios = usuarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Usuarios> obtenerPorId(@PathVariable Integer id) {
        Usuarios usuario = usuarioService.obtenerPorId(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return assembler.toModel(usuario);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Usuarios>> crear(@RequestBody Usuarios usuario) {
        Usuarios nuevo = usuarioService.guardar(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).obtenerPorId(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuarios>> actualizar(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        usuario.setId(id);
        Usuarios actualizado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
