package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the szakmai_tapasztalat database table.
 * 
 */
@Entity
@Table(name="szakmai_tapasztalat")
@NamedQuery(name="SzakmaiTapasztalat.findAll", query="SELECT s FROM SzakmaiTapasztalat s")
public class SzakmaiTapasztalat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date kezdete;

	@Lob
	private String leiras;

	@Lob
	@Column(name="munkaado_neve")
	private String munkaadoNeve;

	@Lob
	@Column(name="pozicio_neve")
	private String pozicioNeve;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vege;

	//bi-directional many-to-one association to Oneletrajz
	@ManyToOne
	@JoinColumn(name="fk_oneletrajz")
	private Oneletrajz oneletrajz;

	public SzakmaiTapasztalat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getKezdete() {
		return this.kezdete;
	}

	public void setKezdete(Date kezdete) {
		this.kezdete = kezdete;
	}

	public String getLeiras() {
		return this.leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	public String getMunkaadoNeve() {
		return this.munkaadoNeve;
	}

	public void setMunkaadoNeve(String munkaadoNeve) {
		this.munkaadoNeve = munkaadoNeve;
	}

	public String getPozicioNeve() {
		return this.pozicioNeve;
	}

	public void setPozicioNeve(String pozicioNeve) {
		this.pozicioNeve = pozicioNeve;
	}

	public Date getVege() {
		return this.vege;
	}

	public void setVege(Date vege) {
		this.vege = vege;
	}

	public Oneletrajz getOneletrajz() {
		return this.oneletrajz;
	}

	public void setOneletrajz(Oneletrajz oneletrajz) {
		this.oneletrajz = oneletrajz;
	}

}