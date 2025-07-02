package com.usuarios.users.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "juegos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Juegos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String plataforma;

}