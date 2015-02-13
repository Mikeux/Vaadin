package model;

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
	private int id;

	@Column(name="kereszt_nev")
	private String keresztNev;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="szul_ido")
	private Date szulIdo;

	private String telefonszam;

	@Column(name="vezetek_nev")
	private String vezetekNev;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	//bi-directional many-to-one association to Orszagok
	@ManyToOne
	@JoinColumn(name="orszag", referencedColumnName="NYELVKOD")
	private Orszagok orszagok;

	//bi-directional many-to-one association to Nyelvek
	@ManyToOne
	@JoinColumn(name="anyanyelv")
	private Nyelvek nyelvek;

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

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}

	public Orszagok getOrszagok() {
		return this.orszagok;
	}

	public void setOrszagok(Orszagok orszagok) {
		this.orszagok = orszagok;
	}

	public Nyelvek getNyelvek() {
		return this.nyelvek;
	}

	public void setNyelvek(Nyelvek nyelvek) {
		this.nyelvek = nyelvek;
	}

}