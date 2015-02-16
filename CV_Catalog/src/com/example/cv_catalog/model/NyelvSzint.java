package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the nyelv_szint database table.
 * 
 */
@Entity
@Table(name="nyelv_szint")
@NamedQuery(name="NyelvSzint.findAll", query="SELECT n FROM NyelvSzint n")
public class NyelvSzint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String megnevezes;

	public NyelvSzint() {
		this.megnevezes = "";
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