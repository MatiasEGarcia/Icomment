package com.icomment.icomment.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles", schema="icomment")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
	
	@Enumerated(EnumType.STRING)
	@Column(name="name")
	private ERol name;
	
	public Rol() {
		
	}
	
	public Rol(Long idRol, ERol name) {
		super();
		this.idRol = idRol;
		this.name = name;
	}

	public Rol(ERol name) {
		super();
		this.name = name;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public ERol getName() {
		return name;
	}

	public void setName(ERol name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(idRol, other.idRol) && name == other.name;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", name=" + name + "]";
	}
	
	
	
	
}
