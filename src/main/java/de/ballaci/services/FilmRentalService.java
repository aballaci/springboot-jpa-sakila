package de.ballaci.services;

import de.ballaci.jpa.domain.Film;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
@PropertySource("classpath:application.properties")
public class FilmRentalService {

    private MeterRegistry meterRegistry;
    private Counter requestedFilmCounter;

    private List<Film> filmList;

    private String instanceName;

    private Environment env;

    Random rand = new Random();

    public FilmRentalService(MeterRegistry meterRegistry, Environment env) {
        this.meterRegistry = meterRegistry;
        this.env = env;
        this.filmList = new ArrayList<>();
        this.instanceName = env.getProperty("INSTANCE_NAME");
        initOrderCounters();
        Gauge.builder("film.bookings.in.queue", filmList, Collection::size)
                .tag("isntance", instanceName)
                .description("Number of unserved Films")
                .register(meterRegistry);
    }


    private void initOrderCounters() {
        requestedFilmCounter =  Counter.builder("film.bookings")
                .tag("isntance", instanceName)
                .register(meterRegistry);
    }

    public void bookFilm(Film film) {
        filmList.add(film);
        requestedFilmCounter.increment();
    }

    @Scheduled(fixedRate = 3000)
    @Timed(description = "Do some work", longTask = true)
    public void serveFilm() throws InterruptedException {
        if(!filmList.isEmpty()) {
            filmList.remove(0);
            Thread.sleep((rand.nextInt(3 - 1) + 1) * 1000);
        }
    }
}