package com.example.springapi.controllers;

import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.GenreBean;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.GenreDao;
import com.example.springapi.models.daos.UserDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class GenresController {

    private final GenreDao genreDao;
    private final UserDAO userDao;

    public GenresController(GenreDao genreDao, UserDAO userDao) {
        this.genreDao = genreDao;
        this.userDao = userDao;
    }

    @GetMapping("/listgenres")
    Object genres(){
        return genreDao.findAll();
    }

    @PostMapping("/change")
    Object update(@RequestBody UserBean user) {
        System.out.println(user.getIdGenre().getIdGenre());
        System.out.println(user.getLogin());
        ErrorMessage error = new ErrorMessage();
        try {
            GenreBean genreDBB = genreDao.findByGenre(user.getIdGenre().getGenre());
            if (genreDBB == null) {
                error.setError("Genre pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            List<UserBean> userDBB = userDao.findAllByLogin(user.getLogin());
            if (userDBB == null) {
                error.setError("Utilisateur pas trouve");
                error.setCode(401);
                throw new Exception(error.getError());
            }

            userDBB.get(0).setIdGenre(genreDBB);

            //UPDATE TABLE UserBean
            //SET id_genre = "${user.getIdGenre().getIdGenre()}"
            //WHERE login = "${user.getLogin}"
            userDao.save(userDBB.get(0));
            return userDBB;

        } catch (Exception e) {
            e.printStackTrace();
            return error;
        }
    }
}

