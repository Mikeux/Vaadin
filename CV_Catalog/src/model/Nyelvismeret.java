package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nyelvismeret database table.
 * 
 */
@Entity
@NamedQuery(name="Nyelvismeret.findAll", query="SELECT n FROM Nyelvismeret n")
public class Nyelvismeret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	//bi-directional many-to-one association to Nyelvek
	@ManyToOne
	@JoinColumn(name="fk_nyelvek")
	private Nyelvek nyelvek;

	//bi-directional many-to-one association to NyelvSzint
	@ManyToOne
	@JoinColumn(name="fk_nyelv_szint")
	private NyelvSzint nyelvSzint;

	public Nyelvismeret() {
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

	public NyelvSzint getNyelvSzint() {
		return this.nyelvSzint;
	}

	public void setNyelvSzint(NyelvSzint nyelvSzint) {
		this.nyelvSzint = nyelvSzint;
	}

}