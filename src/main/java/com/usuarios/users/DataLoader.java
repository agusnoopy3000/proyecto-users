package com.usuarios.users;

import com.usuarios.users.Model.*;
import com.usuarios.users.Repository.*;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final InfraestructuraRepository infraestructuraRepository;
    private final SuscripcionRepository suscripcionRepository;
    private final JuegoRepository juegoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear Juegos
        for (int i = 0; i < 5; i++) {
            Juegos juego = new Juegos();
            juego.setNombre(faker.esports().game());
            juego.setPlataforma(faker.options().option("PC", "Xbox", "PlayStation", "Nintendo"));
            juegoRepository.save(juego);
        }

        List<Juegos> juegos = juegoRepository.findAll();

        // Crear Infraestructura
        for (int i = 0; i < 3; i++) {
            Infraestructura infra = new Infraestructura();
            infra.setNombre("Maquina " + (i + 1));
            infra.setDetalle(faker.lorem().sentence());
            infra.setJueguito(juegos.get(random.nextInt(juegos.size())));
            infraestructuraRepository.save(infra);
        }

        List<Infraestructura> infraestructuras = infraestructuraRepository.findAll();

        // Crear Suscripciones
        for (int i = 0; i < 3; i++) {
            Suscripcion sub = new Suscripcion();
            sub.setNombre("Plan " + faker.color().name());
            sub.setDuracion(faker.number().numberBetween(1, 12) + " meses");
            sub.setInfra(infraestructuras.get(random.nextInt(infraestructuras.size())));
            suscripcionRepository.save(sub);
        }

        List<Suscripcion> suscripciones = suscripcionRepository.findAll();

        // Crear Usuarios
        for (int i = 0; i < 20; i++) {
            Usuarios usuario = new Usuarios();
            usuario.setNombre_usuario(faker.name().username());
            usuario.setPnombre(faker.name().firstName());
            usuario.setSnombre(faker.name().firstName());
            usuario.setPapellido(faker.name().lastName());
            usuario.setSapellido(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContraseña("123456");
            usuario.setSub(suscripciones.get(random.nextInt(suscripciones.size())));
            usuario.setInfra(infraestructuras.get(random.nextInt(infraestructuras.size())));
            usuarioRepository.save(usuario);
        }
    }
}
