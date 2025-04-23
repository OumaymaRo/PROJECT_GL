package com.example.appgl.models;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Représente une proposition d’un fournisseur en réponse à un appel d’offre.
 * 
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "proposition")
public class proposition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "datelivraisonfuture")
	private Date datelivraisonfuture;

	@Column(name = "dureegarantie")
	private long dureegarantie;

	@Column(name = "etat")
	private String etat;

	@Column(name = "marque")
	private char marque;

	@Column(name = "motif")
	private char motif;

	@Column(name = "prixtotal")
	private float prixtotal;

	@ManyToOne
	@JoinColumn(name = "appeldoffreid")
	private appeldoffre mappeldoffre;

	@ManyToOne
	@JoinColumn(name = "fournisseurid")
	private fournisseur mfournisseur;

	public proposition() {
	}

	

	// Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatelivraisonfuture() {
		return datelivraisonfuture;
	}

	public void setDatelivraisonfuture(Date datelivraisonfuture) {
		this.datelivraisonfuture = datelivraisonfuture;
	}

	public long getDureegarantie() {
		return dureegarantie;
	}

	public void setDureegarantie(long dureegarantie) {
		this.dureegarantie = dureegarantie;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public char getMarque() {
		return marque;
	}

	public void setMarque(char marque) {
		this.marque = marque;
	}

	public char getMotif() {
		return motif;
	}

	public void setMotif(char motif) {
		this.motif = motif;
	}

	public float getPrixtotal() {
		return prixtotal;
	}

	public void setPrixtotal(float prixtotal) {
		this.prixtotal = prixtotal;
	}

	public appeldoffre getMappeldoffre() {
		return mappeldoffre;
	}

	public void setMappeldoffre(appeldoffre mappeldoffre) {
		this.mappeldoffre = mappeldoffre;
	}

	public fournisseur getMfournisseur() {
		return mfournisseur;
	}

	public void setMfournisseur(fournisseur mfournisseur) {
		this.mfournisseur = mfournisseur;
	}
}
