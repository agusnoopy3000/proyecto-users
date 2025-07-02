package com.usuarios.users.Service;

import com.usuarios.users.Model.Suscripcion;
import com.usuarios.users.Repository.SuscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuscripcionService {

    private final SuscripcionRepository subRepository;

    public List<Suscripcion> obtenerTodos() {
        return subRepository.findAll();
    }

    public Optional<Suscripcion> obtenerPorId(Integer id) {
        return subRepository.findById(id);
    }

    public Suscripcion guardar(Suscripcion sub) {
        return subRepository.save(sub);
    }

    public void eliminar(Integer id) {
        subRepository.deleteById(id);
    }
}
