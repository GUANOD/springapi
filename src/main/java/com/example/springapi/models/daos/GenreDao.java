package com.example.springapi.models.daos;

import com.example.springapi.models.GenreBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreDao extends JpaRepository<GenreBean, Long> {

    //SELECT * FROM GenreBean
    //WHERE genre = "${genre}"
    GenreBean findByGenre(String genre);

    //SELECT * FROM GenreBean
    //WHERE id_genre = ${idGenre}
    GenreBean findByIdGenre(int idGenre);
}
