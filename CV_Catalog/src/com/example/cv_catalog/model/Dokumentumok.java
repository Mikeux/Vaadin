package com.example.cv_catalog.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dokumentumok database table.
 * 
 */
@Entity
@NamedQuery(name="Dokumentumok.findAll", query="SELECT d FROM Dokumentumok d")
public class Dokumentumok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="fajl_neve")
	private String fajlNeve;

	public Dokumentumok() {
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