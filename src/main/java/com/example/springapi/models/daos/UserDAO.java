package com.example.springapi.models.daos;

import com.example.springapi.models.GenreBean;
import com.example.springapi.models.UserBean;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<UserBean, Long> {

    List<UserBean> findAllByLogin(String login);
    List<UserBean> findByIdGenre(GenreBean idGenre);
}
