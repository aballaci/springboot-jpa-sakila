package de.ballaci;

import de.ballaci.jpa.domain.Film;
import de.ballaci.services.FilmRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author Armand.Ballaci
 */

@SpringBootApplication
@EnableScheduling
public class SakilaApplication {

    @Autowired
    private FilmRentalService rentalService;

    public static void main(String[] args) {
        SpringApplication.run(SakilaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void bookFilms() {

        Flux.interval(Duration.ofSeconds(2))
                .map(SakilaApplication::toFilm)
                .doOnEach(f -> rentalService.bookFilm(f.get()))
                .subscribe();
    }

    private static Film toFilm(Long l) {
        return new Film();
    }
}
