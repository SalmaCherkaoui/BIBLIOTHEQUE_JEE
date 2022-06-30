package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository <Emprunt, Long>
{
    Long countById(Long id);
}
