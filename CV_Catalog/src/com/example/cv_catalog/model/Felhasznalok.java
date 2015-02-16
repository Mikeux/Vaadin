package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the felhasznalok database table.
 * 
 */
@Entity
@NamedQuery(name="Felhasznalok.findAll", query="SELECT f FROM Felhasznalok f")
public class Felhasznalok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private String jelszo;

	private String nev;

	public Felhasznalok() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJelszo() {
		return this.jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	public String getNev() {
		return this.nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

}