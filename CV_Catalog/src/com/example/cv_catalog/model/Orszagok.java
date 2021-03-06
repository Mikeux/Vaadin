package com.example.cv_catalog.model;

import java.io.Serializable;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to SzemelyesAdatok
	@OneToMany(mappedBy="orszagok")
	private List<SzemelyesAdatok> szemelyesAdatoks;
	
	private String orszag;

	private String megnevezes;

	private String nyelvkod;

	private String penznem;

	private String tipus;

	public Orszagok() {
		this.orszag = "";
		this.megnevezes = "";
		this.nyelvkod = "";
		this.penznem = "";
		this.tipus = "";
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<SzemelyesAdatok> getSzemelyesAdatoks() {
		return this.szemelyesAdatoks;
	}

	public void setSzemelyesAdatoks(List<SzemelyesAdatok> szemelyesAdatoks) {
		this.szemelyesAdatoks = szemelyesAdatoks;
	}

	public SzemelyesAdatok addSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().add(szemelyesAdatok);
		szemelyesAdatok.setOrszagok(this);

		return szemelyesAdatok;
	}

	public SzemelyesAdatok removeSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().remove(szemelyesAdatok);
		szemelyesAdatok.setOrszagok(null);

		return szemelyesAdatok;
	}
}