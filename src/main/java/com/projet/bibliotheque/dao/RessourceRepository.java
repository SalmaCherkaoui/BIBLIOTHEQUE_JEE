package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository <Ressource, Long>
{
    Long countById(Long id);
}
