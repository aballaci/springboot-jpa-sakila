package de.ballaci.jpa.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACTOR")
public class Actor {
    @Id
    @Column(name = "ACTOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long actoriId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
