package com.example.springapi.controllers;
import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.RoleBean;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RegisterController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/register")
       Object register(@RequestBody UserBean user){

        ErrorMessage error= new ErrorMessage();

            try{
                if(user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                    error.setError("Le pseudo est vide ou manquant");
                    error.setCode(401);
                    throw new Exception(error.getError());
                }
                if(user.getPassword().isEmpty() || user.getPassword().isBlank()){
                    error.setError("La password est vide ou manquant");
                    error.setCode(401);
                    throw new Exception(error.getError());
                }

                List<UserBean> users = userDAO.findAllByLogin(user.getLogin());

                if(!users.isEmpty()){
                    error.setError("Le utilisateur existe deja");
                    error.setCode(401);
                    throw new Exception(error.getError());
                }else {
                    String hashPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
                    RoleBean role = new RoleBean();
                    role.setIdRole(2);


                    UserBean newUser = new UserBean(user.getLogin(), hashPass, user.getName(), role );
                    userDAO.save(newUser);
                    return newUser;
                }

            }catch(Exception e){
                e.printStackTrace();
                return error;
            }
        }
}
