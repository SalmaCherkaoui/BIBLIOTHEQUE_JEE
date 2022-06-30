package com.projet.bibliotheque.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "exemplaire")

public class Exemplaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    public Exemplaire() {
    }

    public Exemplaire(Long id)
    {
        this.id = id;
    }

    public Exemplaire(Long id, Ressource ressource)
    {
        this.id = id;
        this.ressource = ressource;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Ressource getRessource() { return ressource; }
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString()
    {
        return " " + this.ressource.getTitre();
    }
}
