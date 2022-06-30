package com.projet.bibliotheque.model;

import javax.persistence.*;

@Entity
@Table (name = "bibliothecaire")

public class Bibliothecaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    private String adresse;
    private String type;
    private String email;
    @Column(name = "mot_de_passe")
    private String motDePasse;

    public Bibliothecaire() {
    }

    public Bibliothecaire(Long id, String nom, String prenom, String cin, String numeroTelephone, String adresse, String type, String email, String motDePasse)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.type = type;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getCin() { return cin;}
    public void setCin(String cin) {
        this.cin = cin;
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

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
