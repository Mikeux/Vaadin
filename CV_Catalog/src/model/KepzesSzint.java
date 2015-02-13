package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kepzes_szint database table.
 * 
 */
@Entity
@Table(name="kepzes_szint")
@NamedQuery(name="KepzesSzint.findAll", query="SELECT k FROM KepzesSzint k")
public class KepzesSzint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String megnvezes;

	//bi-directional many-to-one association to Tanulmanyok
	@OneToMany(mappedBy="kepzesSzint")
	private List<Tanulmanyok> tanulmanyoks;

	public KepzesSzint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMegnvezes() {
		return this.megnvezes;
	}

	public void setMegnvezes(String megnvezes) {
		this.megnvezes = megnvezes;
	}

	public List<Tanulmanyok> getTanulmanyoks() {
		return this.tanulmanyoks;
	}

	public void setTanulmanyoks(List<Tanulmanyok> tanulmanyoks) {
		this.tanulmanyoks = tanulmanyoks;
	}

	public Tanulmanyok addTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyoks().add(tanulmanyok);
		tanulmanyok.setKepzesSzint(this);

		return tanulmanyok;
	}

	public Tanulmanyok removeTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyoks().remove(tanulmanyok);
		tanulmanyok.setKepzesSzint(null);

		return tanulmanyok;
	}

}