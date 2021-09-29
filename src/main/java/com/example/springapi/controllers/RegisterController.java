package com.example.springapi.controllers;
import com.example.springapi.models.*;
import com.example.springapi.models.daos.GenreDao;
import com.example.springapi.models.daos.ProfilDao;
import com.example.springapi.models.daos.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RegisterController {


    private final UserDAO userDAO;
    private final ProfilDao profilDAO;
    private final GenreDao genreDAO;

    public RegisterController(UserDAO userdao, ProfilDao profildao, GenreDao genreDAO){
        this.userDAO = userdao;
        this.profilDAO = profildao;
        this.genreDAO = genreDAO;
    }

    @PostMapping("/register")
       Object register(@RequestBody UserBean user){

        ErrorMessage error= new ErrorMessage();
        System.out.println(user.getIdGenre().getIdGenre());
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        System.out.println(user.getName());

            try{
                if(user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                    error.setError("Le pseudo est vide ou manquant");
                    error.setCode(401);
                    throw new Exception(error.getError());
                }
                if(user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                    error.setError("La password est vide ou manquant");
                    error.setCode(401);
                    throw new Exception(error.getError());
                }

                if (user.getMail().isEmpty() || user.getMail().isBlank()) {
                    error.setError("Le mail est vide ou manquant");
                    error.setCode(415);
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
                    String mail = user.getMail();
                    System.out.println("mail "+user.getMail());

                    GenreBean genrebean = genreDAO.findByGenre(user.getIdGenre().getGenre());

                    UserBean newUser = new UserBean(user.getLogin(),hashPass,  user.getName(), role, mail, genrebean);

                    //INSERT INTO UserBean(login, password, name, id_role, id_genre)
                    //VALUES("${user.getLogin()}", "${hashpass}", "${user.getName()}", "${role}", "${user.getIdGenre()}")
                    userDAO.save(newUser);

                    UserBean util = userDAO.findByLogin(user.getLogin());
                    Profil profil = new Profil(new UserBean(util.getIdUser()));


                    profilDAO.save(profil);
                    return newUser;
                }

            }catch(Exception e){
                e.printStackTrace();
                return error;
            }
        }
}

