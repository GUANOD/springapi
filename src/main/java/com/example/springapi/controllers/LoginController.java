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
public class LoginController {

    private final UserDAO userDao;

    public LoginController(UserDAO userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/login")
    Object login(@RequestBody UserBean user) {

        ErrorMessage error = new ErrorMessage();

        System.out.println("/login user" + user.toString());

        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
//                return new ErrorMessage("Le pseudo est vide ou manquant", 1);
                error.setError("Le pseudo est vide ou manquant");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                error.setError("La password est vide ou manquant");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            List<UserBean> users = userDao.findAllByLogin(user.getLogin());
            if (users.isEmpty()) {
                error.setError("Utilisateur pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            String enteredPass = user.getPassword();
            String hashedPass = users.get(0).getPassword();
            if (!BCrypt.checkpw(enteredPass, hashedPass)) {
                error.setError("Password errone");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            return users.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return error;
        }
    }
}


