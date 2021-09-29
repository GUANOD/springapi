package com.example.springapi.models;

import javax.persistence.*;

@Entity
@Table(name = "profilbean")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profil") private int idProfil;
    @Column(name = "nom") private String nom;
    @Column(name = "prenom") private String prenom;
    @Column(name = "age") private int age;
    @OneToOne() @JoinColumn(name = "id_user") private UserBean user;


    public Profil() {}

    public Profil(int idProfil, String nom, String prenom, int age) {
        super();
        this.idProfil = idProfil;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Profil(String nom, String prenom, int age) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Profil(String nom, String prenom, int age, UserBean userbean) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.user = userbean;
    }

    public Profil(UserBean user) {
        super();
        this.user = user;
    }

    public Profil(int idProfil, String nom, String prenom, int age, UserBean user) {
        this.idProfil=idProfil;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.user=user;
    }


    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
