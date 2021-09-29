package com.example.springapi.controllers;

import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")

public class NewPassController {

    private final UserDAO userdao;

    public NewPassController(UserDAO userdao) {
        this.userdao = userdao;
    }

    //http://localhost:8080/resetpass
    @PostMapping("/newpass")

    Object newPass(@RequestBody UserBean user) {
        ErrorMessage error = new ErrorMessage();
        System.out.println("/newpass user = " + user.toString());
        try {
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                error.setError("Le password est vide ou manquant");
                error.setCode(412);
                throw new Exception(error.getError());
            }
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                error.setError("Le pseudo est vide ou manquant");
                error.setCode(411);
                throw new Exception(error.getError());
            }
            // SELECT * FROM userbean WHERE login = "user.getLogin()";
            List<UserBean> users = userdao.findAllByLogin(user.getLogin());
            String passHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            System.out.println(users.get(0).getPassword());
            users.get(0).setPassword(passHashed);
            users.get(0).setTestPass(0);
            System.out.println(users.get(0).getPassword());
            //UPDATE userbean SET password = "passHashed" WHERE id_user = "users.get(0).getIdUser()";
            userdao.save(users.get(0));
            return users.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return error;
        }
    }
}
