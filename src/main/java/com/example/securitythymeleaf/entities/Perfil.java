package com.example.securitythymeleaf.entities;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    private Integer id;

    private String perfil;

    public Integer getId() {
        return id;
    }

    public Perfil() {
    }

    public Perfil(Integer id, String perfil) {
        super();
        this.id = id;
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Perfil [id=" + id + ", perfil=" + perfil + "]";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}