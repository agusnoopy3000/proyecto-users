package com.usuarios.users.Repository;

import com.usuarios.users.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByCorreo(String correo);
}
