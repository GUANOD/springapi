package com.example.springapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="genrebean")
public class GenreBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private int id;
    @Column(name = "genre")
    private String genre;


    //CONSTRUCT

    public GenreBean() {
    }


    public GenreBean(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }


    @JsonProperty("id_genre")
    public int getId() {
        return id;
    }

    @JsonProperty("id_genre")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("genre")
    public String getGenre() {
        return genre;
    }

    @JsonProperty("genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }
}

