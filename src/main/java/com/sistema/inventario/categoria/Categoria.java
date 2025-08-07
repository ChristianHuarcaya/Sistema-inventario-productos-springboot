package com.sistema.inventario.categoria;

import com.sistema.inventario.marca.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, nullable = false, unique = true)
	private String nombre;
	
	
   @ManyToOne
   @JoinColumn(name ="marca_id")
   private Marca marca;
	public Categoria() {
		// Constructor vacío requerido por Spring y JPA
	}

	public Categoria(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria(Long id) {
		this.id = id;
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria(Long id, String nombre, Marca marca) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Categoria(String nombre, Marca marca) {
		super();
		this.nombre = nombre;
		this.marca = marca;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	
}
