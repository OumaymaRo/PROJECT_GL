package com.example.appgl.models;

import jakarta.persistence.*;
import com.example.appgl.models.*;
import java.util.List;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "ressource")
@Inheritance(strategy = InheritanceType.JOINED)
public class ressource {

    @Id
    @Column(name = "id")
    private int id;  // Pas de @GeneratedValue, l'ID doit être fourni manuellement

    @Column(name = "nom")
    private String nom;

    @Column(name = "etataffectation")
    private boolean etataffectation;

    @Column(name = "etatpanne")
    private boolean etatpanne;

    @Column(name = "marque")
    private String marque;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "responsableid")
    private responsable mresponsable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departementid")
    private departement departement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enseignantid") // clé étrangère vers la table "ressource"
    private enseignant enseignant;

    @OneToMany(mappedBy = "ressource")
    private List<panne> pannes;

    public ressource() {
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isEtataffectation() {
        return etataffectation;
    }

    public void setEtataffectation(boolean etataffectation) {
        this.etataffectation = etataffectation;
    }

    public boolean isEtatpanne() {
        return etatpanne;
    }

    public void setEtatpanne(boolean etatpanne) {
        this.etatpanne = etatpanne;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public responsable getMresponsable() {
        return mresponsable;
    }

    public void setMresponsable(responsable mresponsable) {
        this.mresponsable = mresponsable;
    }

    public enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<panne> getPannes() {
        return pannes;
    }

    public void setPannes(List<panne> pannes) {
        this.pannes = pannes;
    }

    public departement getDepartement() {
        return departement;
    }

    public void setDepartement(departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "ressource [id=" + id + ", nom=" + nom + ", etataffectation=" + etataffectation + ", etatpanne="
                + etatpanne + ", marque=" + marque + ", mresponsable=" + mresponsable + ", enseignant=" + enseignant
                + "]";
    }
}
