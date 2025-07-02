package com.usuarios.users.Assamblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.usuarios.users.Model.Usuarios;
import com.usuarios.users.Controller.UsuarioControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuarios, EntityModel<Usuarios>> {

    @Override
    public EntityModel<Usuarios> toModel(Usuarios usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).obtenerPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withRel("usuarios"));
    }
}
