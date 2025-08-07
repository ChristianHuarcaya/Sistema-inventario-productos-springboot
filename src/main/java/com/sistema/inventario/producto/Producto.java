package com.sistema.inventario.producto;

import com.sistema.inventario.categoria.Categoria;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 128, nullable = false, unique = true)
	private String nombre;

	private BigDecimal precio;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	// ======= Getters y Setters =======
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoDetalles> detalles = new ArrayList<>();

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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void añadirDetalles(String nombre, String valor) {
		this.detalles.add(new ProductoDetalles(nombre, valor, this));
	}

	public List<ProductoDetalles> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ProductoDetalles> detalles) {
		this.detalles = detalles;
	}

	public void setDetalle(Long id, String nombre, String valor) {
		this.detalles.add(new ProductoDetalles(id, nombre, valor, this));

	}

	// ======= Constructores =======

	public Producto() {
	}

	public Producto(Long id, String nombre, BigDecimal precio, Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Producto(String nombre, BigDecimal precio, Categoria categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Producto(String nombre) {
		this.nombre = nombre;
	}

	public Producto(Long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + "]";
	}

}
