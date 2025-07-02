package com.usuarios.users.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suscripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Suscripcion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String duracion;

    @ManyToOne
    @JoinColumn(name = "id_maquina",nullable= false)
    private Infraestructura infra ;

}