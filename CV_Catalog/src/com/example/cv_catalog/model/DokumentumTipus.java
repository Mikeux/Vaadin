package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the dokumentum_tipus database table.
 * 
 */
@Entity
@Table(name="dokumentum_tipus")
@NamedQuery(name="DokumentumTipus.findAll", query="SELECT d FROM DokumentumTipus d")
public class DokumentumTipus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String megnevezes;

	public DokumentumTipus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMegnevezes() {
		return this.megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

}