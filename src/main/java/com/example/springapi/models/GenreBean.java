package com.example.springapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="genrebean")
public class GenreBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private int idGenre;
    @Column(name = "genre")
    private String genre;
//    @ManyToMany(mappedBy = "genres")  private List<UserBean> users = new ArrayList<>();


    //CONSTRUCT

    public GenreBean() {
    }

    /**
     *
     * @param idGenre
     * @param genre
     */

    public GenreBean(int idGenre, String genre) {
        this.idGenre = idGenre;
        this.genre = genre;
    }

    public GenreBean(String genre){
        this.genre=genre;
    }

    public GenreBean(int idGenre){
        this.idGenre=idGenre;
    }


    @JsonProperty("idGenre")
    public int getIdGenre() {
        return idGenre;
    }

    @JsonProperty("idGenre")
    public void setIdGenre(int id) {
        this.idGenre = id;
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

