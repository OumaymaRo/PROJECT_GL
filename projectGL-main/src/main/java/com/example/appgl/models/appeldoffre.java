package com.example.appgl.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
/**
 * @author DELL
 * @version 1.0
 * @created 22-avr.-2025 22:47:11
 */
@Entity
@Table(name = "appeldoffre")
public class appeldoffre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "datedebut")
    private Date datedebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "datefin")
    private Date datefin;

    @ManyToOne
	@JoinColumn(name = "responsableid") 
	private responsable mresponsable;

    @OneToMany(mappedBy = "mappeldoffre")
    private List<proposition> propositions; 


    public appeldoffre() {
    }

    // Getters et Setters (fortement recommandés pour JPA)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
    
	public responsable getMresponsable() {
		return mresponsable;
	}

	public void setMresponsable(responsable mresponsable) {
		this.mresponsable = mresponsable;
	}

    public List<proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<proposition> propositions) {
		this.propositions = propositions;
	}
	
}
