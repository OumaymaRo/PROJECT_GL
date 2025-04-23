package com.example.appgl.models;
import jakarta.persistence.*;
import java.util.*;
import com.example.appgl.models.*;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "responsable")
@PrimaryKeyJoinColumn(name = "userid") // Clé étrangère vers la table "user"
public class responsable extends User {

	@OneToOne
	@JoinColumn(name = "appeldoffreid") // Clé étrangère vers la table "appeldoffre"
	private appeldoffre mappeldoffre;

	@OneToMany(mappedBy = "mresponsable")
    private List<appeldoffre> appels; 

	@OneToMany(mappedBy = "mresponsable")
    private List<besoin> besoins; 

	@OneToMany(mappedBy = "mresponsable")
    private List<panne> pannes; 

	@OneToMany(mappedBy = "mresponsable")
    private List<ressource> ressources; 

	public responsable() {
	}


	// Getters et Setters
	public appeldoffre getMappeldoffre() {
		return mappeldoffre;
	}

	public void setMappeldoffre(appeldoffre mappeldoffre) {
		this.mappeldoffre = mappeldoffre;
	}
	// Getters et Setters
	public List<appeldoffre> getAppels() {
		return appels;
	}

	public void setAppels(List<appeldoffre> appels) {
		this.appels = appels;
	}

	public List<besoin> getBesoins() {
		return besoins;
	}

	public void setBesoins(List<besoin> besoins) {
		this.besoins = besoins;
	}

	public List<panne> getPannes() {
		return pannes;
	}

	public void setPannes(List<panne> pannes) {
		this.pannes = pannes;
	}

	public List<ressource> getRessources() {
		return ressources;
	}

	public void setRessources(List<ressource> ressources) {
		this.ressources = ressources;
	}

}