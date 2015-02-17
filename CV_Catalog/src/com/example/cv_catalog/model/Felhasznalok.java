package com.example.cv_catalog.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.cv_catalog.model.Oneletrajz;


/**
 * The persistent class for the felhasznalok database table.
 * 
 */
@Entity
@Table(name="felhasznalok")
@NamedQuery(name="Felhasznalok.findAll", query="SELECT f FROM Felhasznalok f")
public class Felhasznalok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private String jelszo;

	private String nev;

	@OneToMany(mappedBy="felhasznalok")
	private List<Oneletrajz> oneletrajzok;
	
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
	
	public List<Oneletrajz> getOneletrajzs() {
		return this.oneletrajzok;
	}

	public void setOneletrajzs(List<Oneletrajz> oneletrajzok) {
		this.oneletrajzok = oneletrajzok;
	}

	public Oneletrajz addOneletrajz(Oneletrajz oneletrajzok) {
		getOneletrajzs().add(oneletrajzok);
		oneletrajzok.setFelhasznalok(this);

		return oneletrajzok;
	}

	public Oneletrajz removeOneletrajz(Oneletrajz oneletrajzok) {
		getOneletrajzs().remove(oneletrajzok);
		oneletrajzok.setFelhasznalok(null);

		return oneletrajzok;
	}	

}