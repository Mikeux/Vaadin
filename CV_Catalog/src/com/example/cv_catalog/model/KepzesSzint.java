package com.example.cv_catalog.model;

import java.io.Serializable;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String megnvezes;

	@OneToMany(mappedBy="kepzesSzint")
	private List<Tanulmanyok> tanulmanyok;	
	
	public KepzesSzint() {
	}
	
	public KepzesSzint(String megnevezes) {
		this.megnvezes = megnevezes;
	}

	
	public KepzesSzint(int id, String megnevezes) {
		this.id = id;
		this.megnvezes = megnevezes;
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
	

	public List<Tanulmanyok> getTanulmanyok() {
		return this.tanulmanyok;
	}

	public void setTanulmanyok(List<Tanulmanyok> tanulmanyok) {
		this.tanulmanyok = tanulmanyok;
	}

	public Tanulmanyok addTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyok().add(tanulmanyok);
		tanulmanyok.setKepzesSzint(this);

		return tanulmanyok;
	}

	public Tanulmanyok removeTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyok().remove(tanulmanyok);
		tanulmanyok.setKepzesSzint(null);

		return tanulmanyok;
	}	

}