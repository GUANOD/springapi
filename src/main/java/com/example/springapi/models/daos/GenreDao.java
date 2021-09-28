package com.example.springapi.models.daos;

import com.example.springapi.models.GenreBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreDao extends JpaRepository<GenreBean, Long> {

    GenreBean findByGenre(String genre);
}
