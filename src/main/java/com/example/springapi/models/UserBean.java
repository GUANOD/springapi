package com.example.springapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userbean")
public class UserBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;

    @ManyToOne()
    @JoinColumn(name = "id_role")
    private RoleBean idRole;
    @ManyToOne()
    @JoinColumn(name = "id_genre")
    private GenreBean idGenre;
//    @ManyToMany(cascade={CascadeType.ALL})
//    @JoinTable(name="avoir",
//            joinColumns = {@JoinColumn(name="id_user")},
//            inverseJoinColumns = {@JoinColumn(name="id_genre")})
//            @JsonIgnoreProperties("users") private List<GenreBean> genres = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public UserBean() {
    }


    /**
     * @param idUser
     * @param password
     * @param idRole
     * @param name
     * @param login
     * @param genre
     */
    public UserBean(int idUser, String login, String password, String name, RoleBean idRole, GenreBean genre) {
        super();
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.idRole = idRole;
        this.idGenre = genre;
    }

    public UserBean(String login, String name, RoleBean idRole, GenreBean genre ) {
        this.login = login;
        this.name = name;
        this.idRole = idRole;
        this.idGenre = genre;
    }

    public UserBean(String login, String password, String name, RoleBean idRole, GenreBean genre) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.idRole = idRole;
        this.idGenre= genre;
    }


    @JsonProperty("idUser")
    public int getIdUser() {
        return idUser;
    }

    @JsonProperty("idUser")
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("idRole")
    public RoleBean getIdRole() {
        return idRole;
    }

    @JsonProperty("idRole")
    public void setIdRole(RoleBean idRole) {
        this.idRole = idRole;
    }

    public GenreBean getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(GenreBean genre) {
        this.idGenre = genre;
    }
}

