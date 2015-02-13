package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nyelvek database table.
 * 
 */
@Entity
@NamedQuery(name="Nyelvek.findAll", query="SELECT n FROM Nyelvek n")
public class Nyelvek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nyelvkod;

	@Column(name="KARAKTER_KESZLET")
	private String karakterKeszlet;

	private String nyelv;

	//bi-directional many-to-one association to Nyelvismeret
	@OneToMany(mappedBy="nyelvek")
	private List<Nyelvismeret> nyelvismerets;

	//bi-directional many-to-one association to SzemelyesAdatok
	@OneToMany(mappedBy="nyelvek")
	private List<SzemelyesAdatok> szemelyesAdatoks;

	public Nyelvek() {
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

	public List<Nyelvismeret> getNyelvismerets() {
		return this.nyelvismerets;
	}

	public void setNyelvismerets(List<Nyelvismeret> nyelvismerets) {
		this.nyelvismerets = nyelvismerets;
	}

	public Nyelvismeret addNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().add(nyelvismeret);
		nyelvismeret.setNyelvek(this);

		return nyelvismeret;
	}

	public Nyelvismeret removeNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().remove(nyelvismeret);
		nyelvismeret.setNyelvek(null);

		return nyelvismeret;
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