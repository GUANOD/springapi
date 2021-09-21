package com.example.springapi.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="avoir")
public class AvoirBean {

    @Id
    private Long id;

    @ManyToOne() @JoinColumn(name="id_user") private UserBean idUser;
    @ManyToOne() @JoinColumn(name="id_genre")private GenreBean idGenre;

    public AvoirBean(UserBean user, GenreBean genre){
        this.idUser = user;
        this.idGenre = genre;
    }

    public AvoirBean() {

    }


    @JsonProperty("id_user")
    public UserBean getIdUser() {
        return idUser;
    }

    @JsonProperty("id_user")
    public void setIdUser(UserBean idUser) {
        this.idUser = idUser;
    }

    @JsonProperty("id_genre")
    public GenreBean getIdGenre() {
        return idGenre;
    }

    @JsonProperty("id_genre")
    public void setId_genre(GenreBean idGenre) {
        this.idGenre = idGenre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
