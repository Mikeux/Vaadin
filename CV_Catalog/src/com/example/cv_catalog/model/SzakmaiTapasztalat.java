package com.example.cv_catalog.model;

import java.io.Serializable;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the szakmai_tapasztalat database table.
 * 
 */
@Entity
@Table(name="szakmai_tapasztalat")
@NamedQuery(name="SzakmaiTapasztalat.findAll", query="SELECT s FROM SzakmaiTapasztalat s")
public class SzakmaiTapasztalat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date kezdete;

	@Lob
	private String leiras;

	@Lob
	@Column(name="munkaado_neve")
	private String munkaadoNeve;

	@Lob
	@Column(name="pozicio_neve")
	private String pozicioNeve;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vege;

	public SzakmaiTapasztalat() {
	}
	
	public SzakmaiTapasztalat(Oneletrajz cv) {
		try {
			this.kezdete = new SimpleDateFormat("yyyy.MM.dd").parse("1990.01.01");
			this.vege = kezdete;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.leiras = "";
		this.munkaadoNeve = "";
		this.pozicioNeve = "";
		this.oneletrajz = cv;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getKezdete() {
		return this.kezdete;
	}

	public void setKezdete(Date kezdete) {
		this.kezdete = kezdete;
	}

	public String getLeiras() {
		return this.leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	public String getMunkaadoNeve() {
		return this.munkaadoNeve;
	}

	public void setMunkaadoNeve(String munkaadoNeve) {
		this.munkaadoNeve = munkaadoNeve;
	}

	public String getPozicioNeve() {
		return this.pozicioNeve;
	}

	public void setPozicioNeve(String pozicioNeve) {
		this.pozicioNeve = pozicioNeve;
	}

	public Date getVege() {
		return this.vege;
	}

	public void setVege(Date vege) {
		this.vege = vege;
	}
	
	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}	

}