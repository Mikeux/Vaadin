package com.example.cv_catalog.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nyelvismeret database table.
 * 
 */
@Entity
@NamedQuery(name="Nyelvismeret.findAll", query="SELECT n FROM Nyelvismeret n")
public class Nyelvismeret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	public Nyelvismeret() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}