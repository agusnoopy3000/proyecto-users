package com.usuarios.users.Repository;

import com.usuarios.users.Model.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepository extends JpaRepository<Juegos, Integer> {
}
