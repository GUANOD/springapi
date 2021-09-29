package com.example.springapi.models.daos;
import com.example.springapi.models.GenreBean;
import com.example.springapi.models.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDAO extends JpaRepository<UserBean, Long> {

    //SELECT * FROM UserBean
    //WHERE login = "${login}"
    List<UserBean> findAllByLogin(String login);

    //SELECT * FROM UserBean
    //WHERE id_genre = "${idGenre}"
    List<UserBean> findByIdGenre(GenreBean idGenre);

    UserBean findByLogin(String Login);

}
