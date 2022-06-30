package com.projet.bibliotheque.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "emplacement")

public class Emplacement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private Long etage;
    private Long ranger;
    private String couleur;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Emplacement() {
    }

    public Emplacement(Long id, String theme, Long etage, Long ranger, String couleur)
    {
        this.id = id;
        this.theme = theme;
        this.etage = etage;
        this.ranger = ranger;
        this.couleur = couleur;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public Long getEtage() { return etage; }
    public void setEtage(Long etage) { this.etage = etage; }

    public Long getRanger() { return ranger; }
    public void setRanger(Long ranger) { this.ranger = ranger; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

    @Override
    public String toString()
    {
        return " Thème : " + this.theme + ", Etage : " + this.etage + ", Ranger : " + this.ranger + ", Couleur : " + this.couleur;
    }
    public String toStringIndex()
    {
        return " " + this.theme;
    }
}
