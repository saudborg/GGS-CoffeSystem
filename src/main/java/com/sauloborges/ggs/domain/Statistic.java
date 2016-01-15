package com.sauloborges.ggs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Class represents a statistic.
 * Save a programmer and your times and which machine he passed
 * @author sauloborges
 *
 */
@Entity
public class Statistic implements Serializable {

	private static final long serialVersionUID = -6418689418276488290L;

	@Id
	@GeneratedValue
	private Integer id;

	private String machine;

	@ManyToOne
	private Programmer programmer;

	public Statistic() {
	}

	public Statistic(String machine, Programmer programmer) {
		super();
		this.machine = machine;
		this.programmer = programmer;
	}

	public Programmer getProgrammer() {
		return programmer;
	}

	public void setProgrammer(Programmer programmer) {
		this.programmer = programmer;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Statistic [id=" + id + ", machine=" + machine + ", programmer=" + programmer + "]";
	}

}
