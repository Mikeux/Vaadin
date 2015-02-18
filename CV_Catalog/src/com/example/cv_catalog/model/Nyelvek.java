package com.example.cv_catalog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the nyelvek database table.
 * 
 */
@Entity
@Table(name="nyelvek")
@NamedQuery(name="Nyelvek.findAll", query="SELECT n FROM Nyelvek n")
public class Nyelvek implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="nyelvek")
	private List<SzemelyesAdatok> szemelyesAdatoks;
	
	@OneToMany(mappedBy="nyelvek")
	private List<Nyelvismeret> nyelvismeret;
	
	@Column(name="nyelvkod")
	private String nyelvkod;

	@Column(name="KARAKTER_KESZLET")
	private String karakterKeszlet;

	@Column(name="nyelv")
	private String nyelv;

	public Nyelvek() {
		this.nyelvkod = "";
		this.nyelv ="";
		this.karakterKeszlet ="";
	}	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNyelvkod() {
		return this.nyelvkod;
	}

	public void setNyelvkod(String nyelvkod) {
		this.nyelvkod = nyelvkod;
	}

	public String getKarakterKeszlet() {
		return this.karakterKeszlet;
	}

	public void setKarakterKeszlet(String karakterKeszlet) {
		this.karakterKeszlet = karakterKeszlet;
	}

	public String getNyelv() {
		return this.nyelv;
	}

	public void setNyelv(String nyelv) {
		this.nyelv = nyelv;
	}
	

	public List<SzemelyesAdatok> getSzemelyesAdatoks() {
		return this.szemelyesAdatoks;
	}

	public void setSzemelyesAdatoks(List<SzemelyesAdatok> szemelyesAdatoks) {
		this.szemelyesAdatoks = szemelyesAdatoks;
	}

	public SzemelyesAdatok addSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().add(szemelyesAdatok);
		szemelyesAdatok.setNyelvek(this);

		return szemelyesAdatok;
	}

	public SzemelyesAdatok removeSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().remove(szemelyesAdatok);
		szemelyesAdatok.setNyelvek(null);

		return szemelyesAdatok;
	}

}