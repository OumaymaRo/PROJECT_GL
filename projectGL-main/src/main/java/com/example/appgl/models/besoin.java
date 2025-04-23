package com.example.appgl.models;

import jakarta.persistence.*;
/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "besoin")
public class besoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "besoinaenvoyer")
	private boolean besoinaenvoyer;

	@Column(name = "quantite")
	private String quantite;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "responsableid") // Clé étrangère vers la table `responsable`
	private responsable mresponsable;

	
	@ManyToOne
	@JoinColumn(name = "enseignantid") // Clé étrangère vers la table `responsable`
	private enseignant enseignant;

	public besoin() {
	}


	// Getters et Setters recommandés
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBesoinaenvoyer() {
		return besoinaenvoyer;
	}

	public void setBesoinaenvoyer(boolean besoinaenvoyer) {
		this.besoinaenvoyer = besoinaenvoyer;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
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
}
