package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the egyeb_keszsegek database table.
 * 
 */
@Entity
@Table(name="egyeb_keszsegek")
@NamedQuery(name="EgyebKeszsegek.findAll", query="SELECT e FROM EgyebKeszsegek e")
public class EgyebKeszsegek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private String leiras;

	public EgyebKeszsegek() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeiras() {
		return this.leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

}