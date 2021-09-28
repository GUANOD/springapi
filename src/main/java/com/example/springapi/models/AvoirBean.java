//package com.example.springapi.models;


//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="avoir")
//public class AvoirBean {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne() @JoinColumn(name="id_user") private UserBean idUser;
//    @ManyToOne() @JoinColumn(name="id_genre")private GenreBean idGenre;
//
//    public AvoirBean(UserBean user, GenreBean genre){
//        this.idUser = user;
//        this.idGenre = genre;
//    }
//
//    public AvoirBean() {
//
//    }
//
//
//    @JsonProperty("idUser")
//    public UserBean getIdUser() {
//        return idUser;
//    }
//
//    @JsonProperty("idUser")
//    public void setIdUser(UserBean idUser) {
//        this.idUser = idUser;
//    }
//
//    @JsonProperty("idGenre")
//    public GenreBean getIdGenre() {
//        return idGenre;
//    }
//
//    @JsonProperty("idGenre")
//    public void setId_genre(GenreBean idGenre) {
//        this.idGenre = idGenre;
//    }
//
//
//}
