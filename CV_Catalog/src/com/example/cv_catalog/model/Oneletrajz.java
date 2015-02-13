package com.example.cv_catalog.model;

import java.io.Serializable;
import javax.persistence.*;
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
	private int id;

	private int fk_felhasznalok;
	
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
	
	public int getfk_felhasznalok() {
		return this.fk_felhasznalok;
	}

	public void setfk_felhasznalok(int fk) {
		this.fk_felhasznalok = fk;
	}

	public Date getHozzaadva() {
		return this.hozzaadva;
	}

	public void setHozzaadva(Date hozzaadva) {
		this.hozzaadva = hozzaadva;
	}

}