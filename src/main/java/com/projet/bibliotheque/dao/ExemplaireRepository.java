package com.projet.bibliotheque.dao;

import com.projet.bibliotheque.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository <Exemplaire, Long>
{

}
