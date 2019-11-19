package com.accenture.farm.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Egg")
public class Egg {
	
	//			ATRIBUTOS
	
	@Id				
    @GeneratedValue	
	private Long id;
	
	public enum estadoHuevo{ VIVO , TORTILLA , NACIDO, NOVISIBLE };
	public enum colorHuevo { BLANCO , NEGRO , MARRONCITO };
	
	@Enumerated(EnumType.STRING)
	private estadoHuevo estado;
	@Enumerated(EnumType.STRING)
	private colorHuevo color;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="chick_id")
	private Chicken chick;
	
	//		Huevos huerfanos
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="farm_id")
	private Farm farm;
		
	//			GETTERS
	
	public Chicken getChick()
	{
		return chick;
	}
	
	public Farm getFarm() {
		return farm;
	}

	public Long getId()
	{
		return id;
	}
	
	public estadoHuevo getEstado()
	{
		return estado;
	}
	
	public colorHuevo getColor()
	{
		return color;
	}
	
	public void setChick(Chicken chick)
	{
		this.chick = chick;
	}
	
	//			SETTERS
	
	public void setId(Long id)
	{
		this.id = id;
	}	
	
	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public void setEstado(estadoHuevo estado)
	{
		this.estado = estado;
	}
	
	public void setEstadoVivo()
	{
		this.estado = estado.VIVO;
	}
	
	public void setEstadoNacido()
	{
		this.estado = estado.NACIDO;
	}
	
	public void setEstadoTortilla()
	{
		this.estado = estado.TORTILLA;
	}
	
	public void setColor(colorHuevo color) {
		this.color = color;
	}
	
	public void setBlanco()
	{
		this.color = color.BLANCO;
	}

	public void setNegro()
	{
		this.color = color.NEGRO;
	}
	
	public void setMarr()
	{
		this.color = color.MARRONCITO;
	}
	
}		// PUBLIC CLASS

