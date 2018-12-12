package com.tco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    public int id;
    public String vorname;
    public String nachname;
    public boolean geschlechtw;
    public boolean admin;

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
