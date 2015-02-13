package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dokumentum_tipus database table.
 * 
 */
@Entity
@Table(name="dokumentum_tipus")
@NamedQuery(name="DokumentumTipus.findAll", query="SELECT d FROM DokumentumTipus d")
public class DokumentumTipus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String megnevezes;

	//bi-directional many-to-one association to Dokumentumok
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="fk_dokumentum_tipus")
	private Dokumentumok dokumentumok;

	public DokumentumTipus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMegnevezes() {
		return this.megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public Dokumentumok getDokumentumok() {
		return this.dokumentumok;
	}

	public void setDokumentumok(Dokumentumok dokumentumok) {
		this.dokumentumok = dokumentumok;
	}

}