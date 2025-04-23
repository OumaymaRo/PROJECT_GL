package com.example.appgl.models;

import jakarta.persistence.*;
import java.util.List;
/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "fournisseur")
@PrimaryKeyJoinColumn(name = "userid") // clé étrangère vers la table "user"
public class fournisseur extends User {

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "estdanslistenoir")
	private boolean estdanslistenoir;

	@Column(name = "gerant")
	private String gerant;

	@Column(name = "lieu")
	private String lieu;

	@Column(name = "site")
	private String site;

	@Column(name = "societe")
	private String societe;

	@OneToMany(mappedBy = "mfournisseur")
    private List<proposition> propositions; 

	public fournisseur() {
	}

	// Getters et Setters
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean isEstdanslistenoir() {
		return estdanslistenoir;
	}

	public void setEstdanslistenoir(boolean estdanslistenoir) {
		this.estdanslistenoir = estdanslistenoir;
	}

	public String getGerant() {
		return gerant;
	}

	public void setGerant(String gerant) {
		this.gerant = gerant;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}
	
    public List<proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<proposition> propositions) {
		this.propositions = propositions;
	}
}
