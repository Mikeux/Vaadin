package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the dokumentumok database table.
 * 
 */
@Entity
@Table(name="dokumentumok")
@NamedQuery(name="Dokumentumok.findAll", query="SELECT d FROM Dokumentumok d")
public class Dokumentumok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;	
	
	@ManyToOne
	@JoinColumn(name="fk_dokumentum_tipus")
	private DokumentumTipus dokumentum_tipus;		
	
	@Column(name="fajl_neve")
	private String fajlNeve;

	public Dokumentumok() {
	}
	
	public Dokumentumok(Oneletrajz cv, DokumentumTipus tipus, String fnev) {
		this.oneletrajz = cv;
		this.dokumentum_tipus = tipus;
		this.fajlNeve = fnev;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFajlNeve() {
		return this.fajlNeve;
	}

	public void setFajlNeve(String fajlNeve) {
		this.fajlNeve = fajlNeve;
	}

}