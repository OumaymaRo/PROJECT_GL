package com.example.appgl.models;

import jakarta.persistence.*;
import java.util.List;
import com.example.appgl.models.*;
/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "departement")
public class departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "budget")
	private float budget;

	@Column(name = "nom")
	private String nom;

	@OneToOne
	@JoinColumn(name = "chefdepartementid") // Clé étrangère vers la table enseignant
	private enseignant chefdepartement;

	@OneToMany(mappedBy = "departement")
    private List<enseignant> enseignants; 

	@OneToMany(mappedBy = "departement")
    private List<ressource> ressources; 



	
	public departement() {
	}



	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public enseignant getChefdepartement() {
		return this.chefdepartement;
	}

	public void setChefdepartement(enseignant chefdepartement) {
		this.chefdepartement = chefdepartement;
	}

	public List<enseignant> getEnseignants() {
		return enseignants;
	}
	public List<ressource> getRessources() {
		return ressources;
	}

	public void setEnseignants(List<enseignant> enseignants) {
		this.enseignants = enseignants;
	}
}