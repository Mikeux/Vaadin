package com.example.cv_catalog.model;
import java.io.Serializable;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	@Temporal(TemporalType.DATE)
	@Column(name="szul_ido")
	private Date szulIdo;

	private String telefonszam;

	@Column(name="vezetek_nev")
	private String vezetekNev;

	@OneToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;
	
	@ManyToOne
	@JoinColumn(name="fk_nyelvek")
	private Nyelvek nyelvek;

	@ManyToOne
	@JoinColumn(name="fk_orszagok")
	private Orszagok orszagok;

	public SzemelyesAdatok() {
	}

	public SzemelyesAdatok(Oneletrajz cv) {
		this.oneletrajz = cv;
		this.keresztNev="";
		this.vezetekNev="";
		this.telefonszam = "";
		try {
			this.szulIdo = new SimpleDateFormat("yyyy.MM.dd").parse("1990.01.01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}

	public Nyelvek getNyelvek() {
		return this.nyelvek;
	}

	public void setNyelvek(Nyelvek nyelvek) {
		this.nyelvek = nyelvek;
	}

	public Orszagok getOrszagok() {
		return this.orszagok;
	}

	public void setOrszagok(Orszagok orszagok) {
		this.orszagok = orszagok;
	}
}