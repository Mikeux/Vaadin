package com.example.cv_catalog.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;


/**
 * The persistent class for the nyelvismeret database table.
 * 
 */
@Entity
@Table(name="nyelvismeret")
@NamedQuery(name="Nyelvismeret.findAll", query="SELECT n FROM Nyelvismeret n")
public class Nyelvismeret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;
		
	@ManyToOne
	@JoinColumn(name="fk_nyelvek")
	private Nyelvek nyelvek;
	
	@ManyToOne
	@JoinColumn(name="fk_nyelv_szint")
	private NyelvSzint nyelvszint;
	
	public Nyelvismeret() {
	}
	
	public Nyelvismeret(Oneletrajz cv) {
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
	
	public Nyelvek getNyelvek() {
		return this.nyelvek;
	}

	public void setNyelvek(Nyelvek nyelvek) {
		this.nyelvek = nyelvek;
	}
	
	public NyelvSzint getnyelvszint() {
		return this.nyelvszint;
	}

	public void setnyelvszint(NyelvSzint nyelvszint) {
		this.nyelvszint = nyelvszint;
	}	

}