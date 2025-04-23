package com.example.appgl.models;

import jakarta.persistence.*;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "imprimante")
@PrimaryKeyJoinColumn(name = "ressourceid") // clé étrangère vers la table "ressource"
public class imprimante extends ressource {

	@Column(name = "resolution")
	private String resolution;

	@Column(name = "vitesseimpression")
	private String vitesseimpression;

	public imprimante() {
	}

	

	// Getters et Setters
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getVitesseimpression() {
		return vitesseimpression;
	}

	public void setVitesseimpression(String vitesseimpression) {
		this.vitesseimpression = vitesseimpression;
	}
}
