package de.ballaci.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * Table "film"
 * CREATE TABLE film (
 *   film_id              SMALLINT     UNSIGNED NOT NULL AUTO_INCREMENT,
 *   title                VARCHAR(255) NOT NULL,
 *   description          TEXT         DEFAULT NULL,       -- Up to 64KB
 *   release_year         YEAR         DEFAULT NULL,       -- 'yyyy'
 *   language_id          TINYINT      UNSIGNED NOT NULL,  -- 8-bit unsigned int [0, 255]
 *   original_language_id TINYINT      UNSIGNED DEFAULT NULL,
 *   rental_duration      TINYINT      UNSIGNED NOT NULL DEFAULT 3,
 *   rental_rate          DECIMAL(4,2) NOT NULL DEFAULT 4.99,
 *                                     -- DECIMAL is precise and ideal for currency [99.99]. UNSIGNED?
 *   length               SMALLINT     UNSIGNED DEFAULT NULL,  -- 16-bit unsigned int [0, 65535]
 *   replacement_cost     DECIMAL(5,2) NOT NULL DEFAULT 19.99, -- [999.99], UNSIGNED??
 *   rating               ENUM('G','PG','PG-13','R','NC-17') DEFAULT 'G',
 *   special_features     SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL,
 *                                     -- Can take zero or more values from a SET
 *                                     -- But only one value from ENUM
 *   last_update          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   PRIMARY KEY (film_id),
 *   KEY idx_title (title),
 *   KEY idx_fk_language_id (language_id),
 *   KEY idx_fk_original_language_id (original_language_id),
 *         -- To build index on title, language_id, original_language_id and film_id (primary key)
 *   CONSTRAINT fk_film_language FOREIGN KEY (language_id) REFERENCES language (language_id)
 *     ON DELETE RESTRICT ON UPDATE CASCADE,
 *         -- Cannot delete parent record if there is any matching child record
 *         -- Update the matching child records if parent record is updated
 *   CONSTRAINT fk_film_language_original FOREIGN KEY (original_language_id) REFERENCES language (language_id)
 *     ON DELETE RESTRICT ON UPDATE CASCADE
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 *
 * Instead of hard-coding the "language" and "original language", it uses language_id to look up the language table, in a one-to-one relationship. Could use an ENUM for language directly for simplicity.
 * KEYs (INDEXes) are defined on certain columns to facilitate fast search on these columns. We would use "SHOW INDEX FROM tableName \G" to display the details on indexes.
 * Should include UNSIGNED for for non-negative numeric columns like rental_rate.
 * There are 1000 records for this table.
 *
 *
 */

@Entity
@Table(name = "FILM")
public class Film implements Serializable {

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
