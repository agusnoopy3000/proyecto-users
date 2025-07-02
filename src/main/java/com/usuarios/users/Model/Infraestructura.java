package com.usuarios.users.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "infraestructura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Infraestructura{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String detalle;

    @ManyToOne
    @JoinColumn(name = "id_juego", nullable= false)
    private Juegos jueguito;

}