package com.usuarios.users.Service;

import com.usuarios.users.Model.Juegos;
import com.usuarios.users.Repository.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JuegosService {

    private final JuegoRepository gameRepository;

    public List<Juegos> obtenerTodos() {
        return gameRepository.findAll();
    }

    public Optional<Juegos> obtenerPorId(Integer id) {
        return gameRepository.findById(id);
    }

    public Juegos guardar(Juegos game) {
        return gameRepository.save(game);
    }

    public void eliminar(Integer id) {
        gameRepository.deleteById(id);
    }
}
