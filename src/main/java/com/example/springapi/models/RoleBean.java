package com.example.springapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "rolebean")
public class RoleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")private int idRole;
    @Column(name="name")private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public RoleBean() {
    }

    /**
     *
     * @param idRole
     * @param name
     */
    public RoleBean(int idRole, String name) {
        super();
        this.idRole = idRole;
        this.name = name;
    }

    @JsonProperty("idRole")
    public int getIdRole() {
        return idRole;
    }

    @JsonProperty("idRole")
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
