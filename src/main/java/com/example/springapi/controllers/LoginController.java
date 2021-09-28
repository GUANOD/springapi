package com.example.springapi.controllers;
import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.GenreBean;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.GenreDao;
import com.example.springapi.models.daos.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class LoginController{

    private final UserDAO userDao;
    private final GenreDao genreDao;

    public LoginController(UserDAO userDao, GenreDao genreDao){
        this.userDao = userDao;
        this.genreDao=genreDao;
    }

    @PostMapping("/login")
    Object login(@RequestBody UserBean user){

        ErrorMessage error = new ErrorMessage();

        System.out.println("/login user" + user.toString());

        try{
            if(user.getLogin().isEmpty() || user.getLogin().isBlank()) {
//                return new ErrorMessage("Le pseudo est vide ou manquant", 1);
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
            if(users.isEmpty()){
                error.setError("Utilisateur pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            String enteredPass = user.getPassword();
            String hashedPass = users.get(0).getPassword();
            if(!BCrypt.checkpw(enteredPass, hashedPass)){
                error.setError("Password errone");
                error.setCode(401);
                throw new Exception(error.getError());
            }

            return new UserBean(users.get(0).getLogin(), users.get(0).getName(), users.get(0).getIdRole(), new GenreBean());


        }catch(Exception e){
            e.printStackTrace();
            return error;
        }
    }


    @PostMapping("/")
    Object update(@RequestPart String genre, @RequestPart String user ){
        System.out.println(genre);
        System.out.println(user);
        ErrorMessage error = new ErrorMessage();
        try {
            GenreBean genreDBB = genreDao.findByGenre(genre);
            if(genreDBB == null){
                error.setError("Genre pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            UserBean userDBB = userDao.findAllByLogin(user);
            if(user == null){
                error.setError("Utilisateur pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            user.

        }catch(Exception e){
            e.printStackTrace();
            return error;
        }





    }

}
