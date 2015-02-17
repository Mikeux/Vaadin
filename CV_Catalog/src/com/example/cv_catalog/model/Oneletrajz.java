package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;

import com.example.cv_catalog.model.Felhasznalok;

import java.util.Date;


/**
 * The persistent class for the oneletrajz database table.
 * 
 */
@Entity
@Table(name="oneletrajz")
@NamedQuery(name="Oneletrajz.findAll", query="SELECT o FROM Oneletrajz o")
public class Oneletrajz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="fk_felhasznalok")
	private Felhasznalok felhasznalok;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date hozzaadva;

	public Oneletrajz() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getHozzaadva() {
		return this.hozzaadva;
	}

	public void setHozzaadva(Date hozzaadva) {
		this.hozzaadva = hozzaadva;
	}
	
	public Felhasznalok getFelhasznalok() {
		return this.felhasznalok;
	}

	public void setFelhasznalok(Felhasznalok felhasznalok) {
		this.felhasznalok = felhasznalok;
	}

}