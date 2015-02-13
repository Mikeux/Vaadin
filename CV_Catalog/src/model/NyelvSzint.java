package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nyelv_szint database table.
 * 
 */
@Entity
@Table(name="nyelv_szint")
@NamedQuery(name="NyelvSzint.findAll", query="SELECT n FROM NyelvSzint n")
public class NyelvSzint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String megnevezes;

	//bi-directional many-to-one association to Nyelvismeret
	@OneToMany(mappedBy="nyelvSzint")
	private List<Nyelvismeret> nyelvismerets;

	public NyelvSzint() {
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

	public List<Nyelvismeret> getNyelvismerets() {
		return this.nyelvismerets;
	}

	public void setNyelvismerets(List<Nyelvismeret> nyelvismerets) {
		this.nyelvismerets = nyelvismerets;
	}

	public Nyelvismeret addNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().add(nyelvismeret);
		nyelvismeret.setNyelvSzint(this);

		return nyelvismeret;
	}

	public Nyelvismeret removeNyelvismeret(Nyelvismeret nyelvismeret) {
		getNyelvismerets().remove(nyelvismeret);
		nyelvismeret.setNyelvSzint(null);

		return nyelvismeret;
	}

}