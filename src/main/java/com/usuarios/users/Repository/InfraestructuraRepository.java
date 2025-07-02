package com.usuarios.users.Repository;

import com.usuarios.users.Model.Infraestructura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfraestructuraRepository extends JpaRepository<Infraestructura, Integer> {
}
