package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibliothecaireRepository extends JpaRepository <Bibliothecaire, Long>
{
    Bibliothecaire findByEmailAndMotDePasse (String email, String motDePasse);

    Long countById(Long id);

    Bibliothecaire findByEmailAndCin(String email, String cin);

    Long countByEmail(String email);

    Long countByCin(String cin);
}
