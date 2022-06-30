package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository <Emplacement, Long>
{

    Long countById(Long id);
}
