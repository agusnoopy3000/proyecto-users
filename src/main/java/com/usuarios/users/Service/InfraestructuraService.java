package com.usuarios.users.Service;

import com.usuarios.users.Model.Infraestructura;
import com.usuarios.users.Repository.InfraestructuraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfraestructuraService {

    private final InfraestructuraRepository infraRepository;

    public List<Infraestructura> obtenerTodos() {
        return infraRepository.findAll();
    }

    public Optional<Infraestructura> obtenerPorId(Integer id) {
        return infraRepository.findById(id);
    }

    public Infraestructura guardar(Infraestructura maquina) {
        return infraRepository.save(maquina);
    }

    public void eliminar(Integer id) {
        infraRepository.deleteById(id);
    }
}
