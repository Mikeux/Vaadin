package com.example.cv_catalog.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kepzes_szint database table.
 * 
 */
@Entity
@Table(name="kepzes_szint")
@NamedQuery(name="KepzesSzint.findAll", query="SELECT k FROM KepzesSzint k")
public class KepzesSzint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String megnvezes;

	public KepzesSzint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMegnvezes() {
		return this.megnvezes;
	}

	public void setMegnvezes(String megnvezes) {
		this.megnvezes = megnvezes;
	}

}