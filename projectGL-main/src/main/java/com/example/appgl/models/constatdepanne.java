package com.example.appgl.models;
import jakarta.persistence.*;
import java.util.Date;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "constatdepanne")
public class constatdepanne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateapparition")
	private Date dateapparition;

	@Column(name = "explication")
	private String explication;

	@Column(name = "frequence")
	private String frequence;

	@Column(name = "ordre")
	private String ordre;

	public constatdepanne() {
	}



	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateapparition() {
		return dateapparition;
	}

	public void setDateapparition(Date dateapparition) {
		this.dateapparition = dateapparition;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
}
