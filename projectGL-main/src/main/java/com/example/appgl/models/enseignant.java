package com.example.appgl.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "enseignant")
@PrimaryKeyJoinColumn(name = "userid") // Clé étrangère vers la table "user"
public class enseignant extends User {

	@Column(name = "estchef")
	private boolean estchef;

	@Column(name = "salaire")
	private float Salaire;



	@ManyToOne
	@JoinColumn(name = "departementid") // clé étrangère vers la table "besoin"
	private departement departement;
	
	@OneToMany(mappedBy = "enseignant")
    private List<besoin> besoins; 

	@OneToMany(mappedBy = "enseignant")
    private List<ressource> ressources; 

	public enseignant() {
	}

	// Getters et Setters
	public boolean isEstchef() {
		return estchef;
	}

	public void setEstchef(boolean estchef) {
		this.estchef = estchef;
	}

	public float getSalaire() {
		return Salaire;
	}

	public void setSalaire(float salaire) {
		Salaire = salaire;
	}


	public departement getDepartement() {
		return this.departement;
	}

	public void setDepartement(departement departement) {
		this.departement = departement;
	}
	public List<besoin> getBesoins() {
		return besoins;
	}

	public void setBesoins(List<besoin> besoins) {
		this.besoins = besoins;
	}


	public List<ressource> getRessources() {
		return ressources;
	}

	public void setRessources(List<ressource> ressources) {
		this.ressources = ressources;
	}
}
