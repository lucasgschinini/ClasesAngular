package com.accenture.farm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Farm")
public class Farm {
	
	//			ATRIBUTOS
	
	@Id				
    @GeneratedValue	
	private Long id;
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="farm")
	public List<Chicken> hens;
	
	//    Huevos huerfanos
	@OneToMany(cascade=CascadeType.ALL,mappedBy="farm")
	public List<Egg> eggs;
	
    //    Huevos novisibles
	@OneToMany(cascade=CascadeType.ALL,mappedBy="farm")
	public List<Egg> novisibles;
	
    //    Huevos nacidos
	@OneToMany(cascade=CascadeType.ALL,mappedBy="chick")
	public List<Egg> tortilla;
	
	//			GETTERS
	
	public List<Chicken> getHens()
	{
		return hens;
	}
	
	public List<Egg> getEggs() {
		return eggs;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Egg> getNovisibles() {
		return novisibles;
	}

	public List<Egg> getTortilla() {
		return tortilla;
	}

	//			SETTERS
	
	public void setHens(List<Chicken> hens)
	{
		this.hens = hens;
	}

	public void setPollitos(List<Egg> pollitos) {
		this.novisibles = pollitos;
	}

	public void setTortilla(List<Egg> tortilla) {
		this.tortilla = tortilla;
	}

	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
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
