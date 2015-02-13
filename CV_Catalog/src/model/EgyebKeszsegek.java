package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the egyeb_keszsegek database table.
 * 
 */
@Entity
@Table(name="egyeb_keszsegek")
@NamedQuery(name="EgyebKeszsegek.findAll", query="SELECT e FROM EgyebKeszsegek e")
public class EgyebKeszsegek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String leiras;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	public EgyebKeszsegek() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeiras() {
		return this.leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}

}