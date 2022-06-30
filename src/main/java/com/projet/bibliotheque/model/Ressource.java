package com.projet.bibliotheque.model;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table (name = "ressource")

public class Ressource
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    @Column(name = "code_barre")
    private String codeBarre;
    private String genre;
    @Column(name = "date_de_parution")
    private Date dateDeParution;
    private String langue;
    @Column(name = "type_ressource")
    private String typeRessource;
    private Long disponibilite;
    private String edition;
    @Column(name = "nombre_exemplaire")
    private Long nombreExemplaire;
    private String image;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "emplacement_id")
    private Emplacement emplacement;

    public Ressource() {
    }

    public Ressource(Long id, String titre, String auteur, String codeBarre, String genre, Date dateDeParution, String langue, String typeRessource, Long disponibilite, String edition, Long nombreExemplaire, String image)
    {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.codeBarre = codeBarre;
        this.genre = genre;
        this.dateDeParution = dateDeParution;
        this.langue = langue;
        this.typeRessource = typeRessource;
        this.disponibilite = disponibilite;
        this.edition = edition;
        this.nombreExemplaire = nombreExemplaire;
        this.image = image;
    }

    public Ressource(Long id, String titre, String auteur, String codeBarre, String genre, Date dateDeParution, String langue, String typeRessource, Long disponibilite, String edition, Long nombreExemplaire, String image, Emplacement emplacement)
    {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.codeBarre = codeBarre;
        this.genre = genre;
        this.dateDeParution = dateDeParution;
        this.langue = langue;
        this.typeRessource = typeRessource;
        this.disponibilite = disponibilite;
        this.edition = edition;
        this.nombreExemplaire = nombreExemplaire;
        this.image = image;
        this.emplacement = emplacement;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCodeBarre() {
        return codeBarre;
    }
    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateDeParution() {
        return dateDeParution;
    }
    public void setDateDeParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }

    public String getLangue() {
        return langue;
    }
    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getTypeRessource() {
        return typeRessource;
    }
    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }

    public Long getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(Long disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getNombreExemplaire() {
        return nombreExemplaire;
    }
    public void setNombreExemplaire(Long nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Emplacement getEmplacement() { return emplacement; }
    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
}
