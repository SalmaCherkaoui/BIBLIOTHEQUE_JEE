package com.projet.bibliotheque.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "adherent")

public class Adherent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private String prenom;
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    private String adresse;
    private String email;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Adherent() {
    }

    public Adherent(Long id, String cin, String nom, String prenom, String numeroTelephone, String adresse, String email)
    {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

    @Override
    public String toString()
    {
        return "" + this.nom+ "  " + this.prenom;
    }
}
