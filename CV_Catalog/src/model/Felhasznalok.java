package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the felhasznalok database table.
 * 
 */
@Entity
@NamedQuery(name="Felhasznalok.findAll", query="SELECT f FROM Felhasznalok f")
public class Felhasznalok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String jelszo;

	private String nev;

	//bi-directional many-to-one association to Oneletrajz
	@OneToMany(mappedBy="felhasznalok")
	private List<Oneletrajz> oneletrajzs;

	public Felhasznalok() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJelszo() {
		return this.jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	public String getNev() {
		return this.nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public List<Oneletrajz> getOneletrajzs() {
		return this.oneletrajzs;
	}

	public void setOneletrajzs(List<Oneletrajz> oneletrajzs) {
		this.oneletrajzs = oneletrajzs;
	}

	public Oneletrajz addOneletrajz(Oneletrajz oneletrajz) {
		getOneletrajzs().add(oneletrajz);
		oneletrajz.setFelhasznalok(this);

		return oneletrajz;
	}

	public Oneletrajz removeOneletrajz(Oneletrajz oneletrajz) {
		getOneletrajzs().remove(oneletrajz);
		oneletrajz.setFelhasznalok(null);

		return oneletrajz;
	}

}