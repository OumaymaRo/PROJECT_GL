package com.example.appgl.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entité représentant une panne dans le système.
 * 
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "panne")
public class panne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "estreparee")
	private boolean estreparee;

	@Column(name = "rapport", length = 4000)
	private String rapport;

	@Column(name = "type")
	private String type;

	@Column(name = "date_reclamation")
	private LocalDateTime dateReclamation;

	@Column(name = "date_reparation")
	private LocalDateTime dateReparation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "constatdepanneid", referencedColumnName = "id")
	private constatdepanne mconstatdepanne;

	@ManyToOne
	@JoinColumn(name = "responsableid") // plusieurs ressources peuvent être liées à un même responsable
	private responsable mresponsable;

	@ManyToOne
	@JoinColumn(name = "ressourceid") 
	private ressource ressource;

	@ManyToOne
	@JoinColumn(name = "technicienid") 
	private technicien technicien;

	@ManyToOne
	@JoinColumn(name = "enseignantid")
	private enseignant enseignant;

	public panne() {
	}

	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEstreparee() {
		return estreparee;
	}

	public void setEstreparee(boolean estreparee) {
		this.estreparee = estreparee;
	}

	public String getRapport() {
		return rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public constatdepanne getMconstatdepanne() {
		return mconstatdepanne;
	}

	public void setMconstatdepanne(constatdepanne mconstatdepanne) {
		this.mconstatdepanne = mconstatdepanne;
	}

	
	public responsable getMresponsable() {
		return mresponsable;
	}

	public void setMresponsable(responsable mresponsable) {
		this.mresponsable = mresponsable;
	}

	public ressource getRessource() {
		return ressource;
	}

	public void setRessource(ressource ressource) {
		this.ressource = ressource;
	}

	public technicien getTechnicien() {
		return technicien;
	}

	public void setTechnicien(technicien technicien) {
		this.technicien = technicien;
	}

	public LocalDateTime getDateReclamation() {
		return dateReclamation;
	}

	public void setDateReclamation(LocalDateTime dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

	public LocalDateTime getDateReparation() {
		return dateReparation;
	}

	public void setDateReparation(LocalDateTime dateReparation) {
		this.dateReparation = dateReparation;
	}

	public enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(enseignant enseignant) {
		this.enseignant = enseignant;
	}
}
