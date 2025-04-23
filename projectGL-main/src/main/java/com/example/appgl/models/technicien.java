package com.example.appgl.models;

import jakarta.persistence.*;
import com.example.appgl.models.*;
import java.util.List;
/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:12
 */
@Entity
@Table(name = "technicien")
@PrimaryKeyJoinColumn(name = "userid") // Liaison avec la table "user"
public class technicien extends User {

	@OneToMany(mappedBy = "technicien")
    private List<panne> pannes; 


	public technicien() {
	}

	
	public List<panne> getPannes() {
		return pannes;
	}

	public void setPannes(List<panne> pannes) {
		this.pannes = pannes;
	}
	

}