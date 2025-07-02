package com.usuarios.users.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre_usuario;

    private String pnombre;

    private String snombre;

    private String papellido;

    private String sapellido;

    private String correo;

    private String contraseña;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_suscripcion",nullable= false)
    private Suscripcion sub;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maquina",nullable= false)
    private Infraestructura infra ;



    


    



}