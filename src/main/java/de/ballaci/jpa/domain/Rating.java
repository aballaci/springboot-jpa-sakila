package de.ballaci.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.stream.Stream;

/**
 * https://www.baeldung.com/jackson-serialize-enums
 * Enum as JSON Object
 * Starting with Jackson 2.1.2, there is now a configuration option that can handle this kind of representation.
 * This can be done via the @JsonFormat annotation at the class level
 * It would print "PG-13" instead of PG_13.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private String rating;

    public String getRating() {
        return rating;
    }

    private Rating(String rating) {
        this.rating = rating;
    }

    public static Rating of(String rating) {
        return Stream.of(Rating.values())
                .filter(p -> p.getRating().equals(rating))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return name();
    }
}
