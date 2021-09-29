package com.example.springapi.controllers;

import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.Profil;
import com.example.springapi.models.RoleBean;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.ProfilDao;
import com.example.springapi.models.daos.UserDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProfilController {

    private final ProfilDao profildao;
    private final UserDAO userdao;

    public ProfilController(UserDAO userdao, ProfilDao profildao) {
        this.userdao = userdao;
        this.profildao = profildao;
    }
    ErrorMessage message = new ErrorMessage();
    //http://localhost:8080/profil
    @PostMapping("/profil")
    Object register(@RequestBody UserBean user) {


        System.out.println("/profil user" + user.toString());

        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                message.setError("Le nom est vide ou manquant");
                message.setCode(421);
                throw new Exception(message.getError());
            }
            List<UserBean> users = userdao.findAllByLogin(user.getLogin());

            int id = users.get(0).getIdUser();
            Profil profil = profildao.findByIdProfil(id);
//            String login = users.get(0).getLogin();
//            String nom = profil.getNom();
//            String prenom = profil.getPrenom();
//            int age = profil.getAge();
//            String role = users.get(0).getRole().getName();
            return new Profil(profil.getNom(), profil.getPrenom(), profil.getAge(), new UserBean(profil.getUser().getLogin(), profil.getUser().getName(), new RoleBean(profil.getUser().getIdRole().getName())));
//            return profil;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    //    http://localhost:8080/rest/upProfil
    @PostMapping("/upProfil")
    Object upProfil(@RequestBody Profil profil) {


        try {
            if (profil.getIdProfil() == 0) {
                message.setError("Il n'y a pas d'utilisateur");
                message.setCode(421);
                throw new Exception(message.getError());
            }

            profildao.save(new Profil(profil.getIdProfil(), profil.getNom(), profil.getPrenom(), profil.getAge(), profil.getUser()));
            message.setError("Profil sauvegardÃ© !");
            message.setCode(200);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}