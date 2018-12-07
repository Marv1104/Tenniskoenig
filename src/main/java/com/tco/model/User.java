package com.tco.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private int id;
    private String vorname;
    private String nachname;
    private boolean geschlechtw;
    private boolean admin;

    public User() {
    }

    public User(String vorName, String nachname, boolean geschlechtw, boolean admin) {
        super();
        this.vorname = vorName;
        this.nachname = nachname;
        this.geschlechtw = geschlechtw;
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public boolean isGeschlechtw() {
        return geschlechtw;
    }

    public void setGeschlechtw(boolean geschlechtW) {
        this.geschlechtw = geschlechtW;
    }
}
