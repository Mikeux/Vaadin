package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tanulmanyok database table.
 * 
 */
@Entity
@NamedQuery(name="Tanulmanyok.findAll", query="SELECT t FROM Tanulmanyok t")
public class Tanulmanyok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date kezdete;

	@Lob
	private String neve;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vege;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	//bi-directional many-to-one association to KepzesSzint
	@ManyToOne
	@JoinColumn(name="fk_kepzes_szint")
	private KepzesSzint kepzesSzint;

	public Tanulmanyok() {
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

}