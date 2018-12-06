package com.tco.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private int id;
    private String vorName;
    private String nachName;
    private boolean geschlechtW;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public boolean isGeschlechtW() {
        return geschlechtW;
    }

    public void setGeschlechtW(boolean geschlechtW) {
        this.geschlechtW = geschlechtW;
    }
}
