package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tanulmanyok database table.
 * 
 */
@Entity
@Table(name="tanulmanyok")
@NamedQuery(name="Tanulmanyok.findAll", query="SELECT t FROM Tanulmanyok t")
public class Tanulmanyok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	@ManyToOne
	@JoinColumn(name="fk_kepzes_szint")
	private KepzesSzint kepzesSzint;
		
	@Temporal(TemporalType.DATE)
	private Date kezdete;

	@Lob
	private String neve;

	@Temporal(TemporalType.DATE)
	private Date vege;

	public Tanulmanyok() {
	
	}
	
	public Tanulmanyok(Oneletrajz cv) {
		try {
			this.kezdete = new SimpleDateFormat("yyyy.MM.dd").parse("1990.01.01");
			this.vege = kezdete;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.neve = "";
		this.oneletrajz = cv;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}
	
	public KepzesSzint getKepzesSzint() {
		return this.kepzesSzint;
	}

	public void setKepzesSzint(KepzesSzint kepzesSzint) {
		this.kepzesSzint = kepzesSzint;
	}	
	
	public Date getKezdete() {
		return this.kezdete;
	}

	public void setKezdete(Date kezdete) {
		this.kezdete = kezdete;
	}

	public String getNeve() {
		return this.neve;
	}

	public void setNeve(String neve) {
		this.neve = neve;
	}

	public Date getVege() {
		return this.vege;
	}

	public void setVege(Date vege) {
		this.vege = vege;
	}

}