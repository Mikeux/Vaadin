package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the orszagok database table.
 * 
 */
@Entity
@Table(name="orszagok")
@NamedQuery(name="Orszagok.findAll", query="SELECT o FROM Orszagok o")
public class Orszagok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String orszag;

	private String megnevezes;

	private String nyelvkod;

	private String penznem;

	private String tipus;

	public Orszagok() {
	}

	public String getOrszag() {
		return this.orszag;
	}

	public void setOrszag(String orszag) {
		this.orszag = orszag;
	}

	public String getMegnevezes() {
		return this.megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public String getNyelvkod() {
		return this.nyelvkod;
	}

	public void setNyelvkod(String nyelvkod) {
		this.nyelvkod = nyelvkod;
	}

	public String getPenznem() {
		return this.penznem;
	}

	public void setPenznem(String penznem) {
		this.penznem = penznem;
	}

	public String getTipus() {
		return this.tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

}