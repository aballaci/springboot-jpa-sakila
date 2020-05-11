package de.ballaci.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "FILM")
public class Film {

    @Id
    @Column(name="FILM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long filmId;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="RELEASE_YEAR")
    private int releaseYear;

    @Column(name="LANGUAGE_ID")
    private int languageId;

    @Column(name="RENTAL_RATE")
    private float rentalRate;

    @Column(name="LENGTH")
    private int length;

    @Column(name="REPLACEMENT_COST")
    private float replacementCost = 19.99f;

    @Column(name="RATING")
    @Basic
    @JsonIgnore
    private String ratingValue;

    @Transient
    private Rating rating;

//    @Column(name="SPECIAL_FEATURES")
//    private Set<SpecialFeature> specialFeatures;

    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE")
    private Date lastUpdate;

    /**
     * Using @PostLoad and @PrePersist annotations
     * https://www.baeldung.com/jpa-persisting-enums-in-jpa
     *
     * TODO: 4. Using JPA 2.1 @Converter Annotation
     */
    @PostLoad
    void fillTransient() {
        if (ratingValue != null) {
            this.rating = Rating.of(ratingValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (rating != null) {
            this.ratingValue = rating.getRating();
        }
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(float replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }
}
