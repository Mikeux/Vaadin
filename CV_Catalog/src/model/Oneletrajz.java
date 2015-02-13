package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the oneletrajz database table.
 * 
 */
@Entity
@NamedQuery(name="Oneletrajz.findAll", query="SELECT o FROM Oneletrajz o")
public class Oneletrajz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hozzaadva;

	//bi-directional many-to-one association to Dokumentumok
	@OneToMany(mappedBy="oneletrajz")
	private List<Dokumentumok> dokumentumoks;

	//bi-directional many-to-one association to EgyebKeszsegek
	@OneToMany(mappedBy="oneletrajz")
	private List<EgyebKeszsegek> egyebKeszsegeks;

	//bi-directional many-to-one association to Nyelvismeret
	@OneToMany(mappedBy="oneletrajz")
	private List<Nyelvismeret> nyelvismerets;

	//bi-directional many-to-one association to Felhasznalok
	@ManyToOne
	@JoinColumn(name="fk_felhasznalok")
	private Felhasznalok felhasznalok;

	//bi-directional many-to-one association to SzakmaiTapasztalat
	@OneToMany(mappedBy="oneletrajz")
	private List<SzakmaiTapasztalat> szakmaiTapasztalats;

	//bi-directional many-to-one association to SzemelyesAdatok
	@OneToMany(mappedBy="oneletrajz")
	private List<SzemelyesAdatok> szemelyesAdatoks;

	//bi-directional many-to-one association to Tanulmanyok
	@OneToMany(mappedBy="oneletrajz")
	private List<Tanulmanyok> tanulmanyoks;

	public Oneletrajz() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHozzaadva() {
		return this.hozzaadva;
	}

	public void setHozzaadva(Date hozzaadva) {
		this.hozzaadva = hozzaadva;
	}

	public List<Dokumentumok> getDokumentumoks() {
		return this.dokumentumoks;
	}

	public void setDokumentumoks(List<Dokumentumok> dokumentumoks) {
		this.dokumentumoks = dokumentumoks;
	}

	public Dokumentumok addDokumentumok(Dokumentumok dokumentumok) {
		getDokumentumoks().add(dokumentumok);
		dokumentumok.setOneletrajz(this);

		return dokumentumok;
	}

	public Dokumentumok removeDokumentumok(Dokumentumok dokumentumok) {
		getDokumentumoks().remove(dokumentumok);
		dokumentumok.setOneletrajz(null);

		return dokumentumok;
	}

	public List<EgyebKeszsegek> getEgyebKeszsegeks() {
		return this.egyebKeszsegeks;
	}

	public void setEgyebKeszsegeks(List<EgyebKeszsegek> egyebKeszsegeks) {
		this.egyebKeszsegeks = egyebKeszsegeks;
	}

	public EgyebKeszsegek addEgyebKeszsegek(EgyebKeszsegek egyebKeszsegek) {
		getEgyebKeszsegeks().add(egyebKeszsegek);
		egyebKeszsegek.setOneletrajz(this);

		return egyebKeszsegek;
	}

	public EgyebKeszsegek removeEgyebKeszsegek(EgyebKeszsegek egyebKeszsegek) {
		getEgyebKeszsegeks().remove(egyebKeszsegek);
		egyebKeszsegek.setOneletrajz(null);

		return egyebKeszsegek;
	}

	public List<Nyelvismeret> getNyelvismerets() {
		return this.nyelvismerets;
	}

	public void setNyelvismerets(List<Nyelvismeret> nyelvismerets) {
		this.nyelvismerets = nyelvismerets;
	}

	public Nyelvismeret addNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().add(nyelvismeret);
		nyelvismeret.setOneletrajz(this);

		return nyelvismeret;
	}

	public Nyelvismeret removeNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().remove(nyelvismeret);
		nyelvismeret.setOneletrajz(null);

		return nyelvismeret;
	}

	public Felhasznalok getFelhasznalok() {
		return this.felhasznalok;
	}

	public void setFelhasznalok(Felhasznalok felhasznalok) {
		this.felhasznalok = felhasznalok;
	}

	public List<SzakmaiTapasztalat> getSzakmaiTapasztalats() {
		return this.szakmaiTapasztalats;
	}

	public void setSzakmaiTapasztalats(List<SzakmaiTapasztalat> szakmaiTapasztalats) {
		this.szakmaiTapasztalats = szakmaiTapasztalats;
	}

	public SzakmaiTapasztalat addSzakmaiTapasztalat(SzakmaiTapasztalat szakmaiTapasztalat) {
		getSzakmaiTapasztalats().add(szakmaiTapasztalat);
		szakmaiTapasztalat.setOneletrajz(this);

		return szakmaiTapasztalat;
	}

	public SzakmaiTapasztalat removeSzakmaiTapasztalat(SzakmaiTapasztalat szakmaiTapasztalat) {
		getSzakmaiTapasztalats().remove(szakmaiTapasztalat);
		szakmaiTapasztalat.setOneletrajz(null);

		return szakmaiTapasztalat;
	}

	public List<SzemelyesAdatok> getSzemelyesAdatoks() {
		return this.szemelyesAdatoks;
	}

	public void setSzemelyesAdatoks(List<SzemelyesAdatok> szemelyesAdatoks) {
		this.szemelyesAdatoks = szemelyesAdatoks;
	}

	public SzemelyesAdatok addSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().add(szemelyesAdatok);
		szemelyesAdatok.setOneletrajz(this);

		return szemelyesAdatok;
	}

	public SzemelyesAdatok removeSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().remove(szemelyesAdatok);
		szemelyesAdatok.setOneletrajz(null);

		return szemelyesAdatok;
	}

	public List<Tanulmanyok> getTanulmanyoks() {
		return this.tanulmanyoks;
	}

	public void setTanulmanyoks(List<Tanulmanyok> tanulmanyoks) {
		this.tanulmanyoks = tanulmanyoks;
	}

	public Tanulmanyok addTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyoks().add(tanulmanyok);
		tanulmanyok.setOneletrajz(this);

		return tanulmanyok;
	}

	public Tanulmanyok removeTanulmanyok(Tanulmanyok tanulmanyok) {
		getTanulmanyoks().remove(tanulmanyok);
		tanulmanyok.setOneletrajz(null);

		return tanulmanyok;
	}

}