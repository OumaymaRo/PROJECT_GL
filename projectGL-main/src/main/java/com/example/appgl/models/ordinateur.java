package com.example.appgl.models;

import jakarta.persistence.*;

/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "ordinateur")
@PrimaryKeyJoinColumn(name = "ressourceid") // Clé étrangère vers la table "ressource"
public class ordinateur extends ressource {

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "disquedur")
	private String disquedur;

	@Column(name = "ecran")
	private String ecran;

	@Column(name = "ram")
	private String ram;

	public ordinateur() {
	}


	// Getters et Setters
	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getDisquedur() {
		return disquedur;
	}

	public void setDisquedur(String disquedur) {
		this.disquedur = disquedur;
	}

	public String getEcran() {
		return ecran;
	}

	public void setEcran(String ecran) {
		this.ecran = ecran;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}
}