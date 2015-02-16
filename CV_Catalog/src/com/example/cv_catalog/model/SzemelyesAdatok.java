package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the szemelyes_adatok database table.
 * 
 */
@Entity
@Table(name="szemelyes_adatok")
@NamedQuery(name="SzemelyesAdatok.findAll", query="SELECT s FROM SzemelyesAdatok s")
public class SzemelyesAdatok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="kereszt_nev")
	private String keresztNev;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="szul_ido")
	private Date szulIdo;

	private String telefonszam;

	@Column(name="vezetek_nev")
	private String vezetekNev;

	public SzemelyesAdatok() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeresztNev() {
		return this.keresztNev;
	}

	public void setKeresztNev(String keresztNev) {
		this.keresztNev = keresztNev;
	}

	public Date getSzulIdo() {
		return this.szulIdo;
	}

	public void setSzulIdo(Date szulIdo) {
		this.szulIdo = szulIdo;
	}

	public String getTelefonszam() {
		return this.telefonszam;
	}

	public void setTelefonszam(String telefonszam) {
		this.telefonszam = telefonszam;
	}

	public String getVezetekNev() {
		return this.vezetekNev;
	}

	public void setVezetekNev(String vezetekNev) {
		this.vezetekNev = vezetekNev;
	}

}