package com.example.springapi.controllers;

import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.GenreBean;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.GenreDao;
import com.example.springapi.models.daos.UserDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class SearchController {

    private final UserDAO userDao;
    private final GenreDao genreDao;

    public SearchController(UserDAO userDao, GenreDao genreDao){
        this.userDao = userDao;
        this.genreDao=genreDao;
    }

    @PostMapping("/search")
    Object search(@RequestBody GenreBean genre){
        System.out.println(genre.getGenre());
        ErrorMessage error = new ErrorMessage();
        try{
            if(genre.getGenre().isEmpty()){
                error.setError("Veuillez renseigner un genre");
                error.setCode(401);
                throw new Exception(error.getError());
            }
            GenreBean genreBDD = genreDao.findByGenre(genre.getGenre());

            if(genreBDD == null){
                error.setError("Genre pas trouve");
                error.setCode(402);
                throw new Exception(error.getError());
            }

            System.out.println(genreBDD.getIdGenre());

            List<UserBean> userList = userDao.findByIdGenre(genreBDD);

            if(userList.isEmpty()){
                error.setError("Personne aime ca");
                error.setCode(402);
                throw new Exception(error.getError());
            }

            return userList;
        }catch(Exception e){
            e.printStackTrace();
            return error;
        }
    }
}
