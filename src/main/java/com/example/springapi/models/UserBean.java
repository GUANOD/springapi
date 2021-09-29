package com.example.springapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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
    @Column(name="mail")
    private String mail;
    @Column(name="testpass" )
    private int testPass;
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
    public UserBean(int idUser, String login, String password, String name, RoleBean idRole,String mail, int testPass, GenreBean genre) {
        super();
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.idRole = idRole;
        this.idGenre = genre;
        this.mail = mail;
        this.testPass=testPass;
    }

    public UserBean(String login, String name, RoleBean idRole, GenreBean genre, String mail, int testPass ) {
        this.login = login;
        this.name = name;
        this.idRole = idRole;
        this.idGenre = genre;
        this.testPass=testPass;
        this.mail=mail;
    }

    public UserBean(String login, String password, String name, RoleBean idRole, String mail, GenreBean genre) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.idRole = idRole;
        this.idGenre= genre;
        this.mail=mail;
    }

    public UserBean(int idUser) {
        this.idUser= idUser;
    }

    public UserBean(String login, String name, RoleBean roleBean) {
        this.login=login;
        this.name=name;
        this.idRole=roleBean;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public int getTestPass() {
        return testPass;
    }

    public void setTestPass(int testPass) {
        this.testPass = testPass;
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

