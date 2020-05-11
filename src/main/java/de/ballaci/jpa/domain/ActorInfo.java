package de.ballaci.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACTOR_INFO")
public class ActorInfo implements Serializable {
    @Id
    @Column(name = "ACTOR_ID", updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long actoriId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FILM_INFO")
    private String filminfo;

    public long getActoriId() {
        return actoriId;
    }

    public void setActoriId(long actoriId) {
        this.actoriId = actoriId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFilminfo() {
        return filminfo;
    }

    public void setFilminfo(String filminfo) {
        this.filminfo = filminfo;
    }
}
