package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository <Adherent, Long>
{
    Long countById(Long id);

    Long countByEmail(String email);

    Long countByCin(String cin);
}
