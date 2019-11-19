package com.accenture.farm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Chick")
public class Chicken {
	
	//			ATRIBUTOS

	@Id				
    @GeneratedValue	
	private Long id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="farm_id")
	private Farm farm;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="chick")
	public List<Egg> huevos;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="chick")
	public List<Egg> pollitos;
	
	//			GETTERS
	
	public Farm getFarm()
	{
		return farm;
	}
	
	public List<Egg> getHuevos()
	{
		return huevos;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	//			SETTERS
	
	public void setFarm(Farm farm)
	{
		this.farm = farm;
	}

	public void setHuevos(List<Egg> huevos)
	{
		this.huevos = huevos;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
}		// PUBLIC CLASS
