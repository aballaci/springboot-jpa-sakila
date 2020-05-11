package de.ballaci.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Table "actor"
 *
 * CREATE TABLE actor (
 *   actor_id    SMALLINT     UNSIGNED NOT NULL AUTO_INCREMENT,
 *                            -- 16-bit unsigned int in the range of [0, 65535]
 *   first_name  VARCHAR(45)  NOT NULL,
 *   last_name   VARCHAR(45)  NOT NULL,
 *   last_update TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   PRIMARY KEY (actor_id),
 *   KEY idx_actor_last_name (last_name)   -- To build index (non-unique) on last_name
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 *        -- Use InnoDB Engine, which supports foreign key and transaction
 *        -- Use Unicode 'utf8' character set for this table
 * There can be one TIMESTAMP column with DEFAULT CURRENT_TIMESTAMP. If you wish to have both create and last_update, you need to use a ON INSERT trigger to set the create TIMESTAMP. For strict auditing, you might have create_timestamp, create_by, last_update_timestamp and last_update_by.
 * InnoDB engine is used, which support foreign key and transaction.
 * The default character set for this table is UTF8, which supports all languages for internationalization.
 * Better to use INT UNSIGNED for AUTO_INCREMENT column actor_id to avoid overrun.
 * There are 200 records for this table.
 */

@Entity
@Table(name = "ACTOR")
public class Actor implements Serializable {
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
