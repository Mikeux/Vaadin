package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dokumentumok database table.
 * 
 */
@Entity
@NamedQuery(name="Dokumentumok.findAll", query="SELECT d FROM Dokumentumok d")
public class Dokumentumok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="fajl_neve")
	private String fajlNeve;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	//bi-directional many-to-one association to DokumentumTipus
	@OneToMany(mappedBy="dokumentumok")
	private List<DokumentumTipus> dokumentumTipuses;

	public Dokumentumok() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFajlNeve() {
		return this.fajlNeve;
	}

	public void setFajlNeve(String fajlNeve) {
		this.fajlNeve = fajlNeve;
	}

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}

	public List<DokumentumTipus> getDokumentumTipuses() {
		return this.dokumentumTipuses;
	}

	public void setDokumentumTipuses(List<DokumentumTipus> dokumentumTipuses) {
		this.dokumentumTipuses = dokumentumTipuses;
	}

	public DokumentumTipus addDokumentumTipus(DokumentumTipus dokumentumTipus) {
		getDokumentumTipuses().add(dokumentumTipus);
		dokumentumTipus.setDokumentumok(this);

		return dokumentumTipus;
	}

	public DokumentumTipus removeDokumentumTipus(DokumentumTipus dokumentumTipus) {
		getDokumentumTipuses().remove(dokumentumTipus);
		dokumentumTipus.setDokumentumok(null);

		return dokumentumTipus;
	}

}