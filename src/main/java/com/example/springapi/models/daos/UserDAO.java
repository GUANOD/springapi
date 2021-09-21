package com.example.springapi.models.daos;

import com.example.springapi.models.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<UserBean, Long> {

    List<UserBean> findAllByLogin(String login);
}
