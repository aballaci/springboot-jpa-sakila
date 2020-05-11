package de.ballaci.jpa.domain;

import java.util.stream.Stream;

public enum SpecialFeature {

    Trailers("Trailers"),
    Commentaries("Commentaries"),
    Deleted_Scenes("Deleted Scenes"),
    Behind_the_Scenes("Behind the Scenes");

    private String specialFeature;

    public String getSpecialFeature() {
        return specialFeature;
    }

    private SpecialFeature(String specialFeature){
        this.specialFeature = specialFeature;
    }

    public static SpecialFeature of(String specialFeature) {
        return Stream.of(SpecialFeature.values())
                .filter(p -> p.getSpecialFeature() == specialFeature)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
