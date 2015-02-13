package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orszagok database table.
 * 
 */
@Entity
@NamedQuery(name="Orszagok.findAll", query="SELECT o FROM Orszagok o")
public class Orszagok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String orszag;

	private String megnevezes;

	private String penznem;

	private String tipus;

	//bi-directional many-to-one association to SzemelyesAdatok
	@OneToMany(mappedBy="orszagok")
	private List<SzemelyesAdatok> szemelyesAdatoks;

	public Orszagok() {
	}

	public String getOrszag() {
		return this.orszag;
	}

	public void setOrszag(String orszag) {
		this.orszag = orszag;
	}

	public String getMegnevezes() {
		return this.megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public String getPenznem() {
		return this.penznem;
	}

	public void setPenznem(String penznem) {
		this.penznem = penznem;
	}

	public String getTipus() {
		return this.tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public List<SzemelyesAdatok> getSzemelyesAdatoks() {
		return this.szemelyesAdatoks;
	}

	public void setSzemelyesAdatoks(List<SzemelyesAdatok> szemelyesAdatoks) {
		this.szemelyesAdatoks = szemelyesAdatoks;
	}

	public SzemelyesAdatok addSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().add(szemelyesAdatok);
		szemelyesAdatok.setOrszagok(this);

		return szemelyesAdatok;
	}

	public SzemelyesAdatok removeSzemelyesAdatok(SzemelyesAdatok szemelyesAdatok) {
		getSzemelyesAdatoks().remove(szemelyesAdatok);
		szemelyesAdatok.setOrszagok(null);

		return szemelyesAdatok;
	}

}