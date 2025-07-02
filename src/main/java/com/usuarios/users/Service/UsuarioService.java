package com.usuarios.users.Service;

import com.usuarios.users.Model.Usuarios;
import com.usuarios.users.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuarios> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuarios> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuarios guardar(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
