package com.projet.bibliotheque.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "emprunt")

public class Emprunt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_emprunt")
    private Date dateEmprunt;
    @Column(name = "date_retour")
    private Date dateRetour;
    @Column(name = "date_effective")
    private Date dateEffective;
    private int duree;
    @Column(name = "nombre_rappel")
    private int nombreRappel;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;
    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    private Exemplaire exemplaire;

    public Emprunt() {
    }

    public Emprunt(Long id, Date dateEmprunt, Date dateRetour, Date dateEffective, int duree, int nombreRappel)
    {
        this.id = id;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.dateEffective = dateEffective;
        this.duree = duree;
        this.nombreRappel = nombreRappel;
    }

    public Emprunt(Long id, Date dateEmprunt, Date dateRetour, Date dateEffective, int duree, int nombreRappel, Adherent adherent, Exemplaire exemplaire)
    {
        this.id = id;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.dateEffective = dateEffective;
        this.duree = duree;
        this.nombreRappel = nombreRappel;
        this.adherent = adherent;
        this.exemplaire = exemplaire;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }

    public Date getDateEffective() { return dateEffective; }
    public void setDateEffective(Date dateEffective) { this.dateEffective = dateEffective; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public int getNombreRappel() { return nombreRappel; }
    public void setNombreRappel(int nombreRappel) { this.nombreRappel = nombreRappel; }

    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
}
