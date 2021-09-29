package com.example.springapi.models.daos;

import com.example.springapi.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilDao extends JpaRepository <Profil, Long> {
    Profil findByIdProfil(int idUser);
}
