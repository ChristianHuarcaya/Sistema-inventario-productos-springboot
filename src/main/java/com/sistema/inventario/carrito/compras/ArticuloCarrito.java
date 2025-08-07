package com.sistema.inventario.carrito.compras;

import com.sistema.inventario.producto.Producto;
import com.sistema.inventario.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "articulo_carrito")
public class ArticuloCarrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArticuloCarrito(Long id, int cantidad, Producto producto, Usuario usuario) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
		this.usuario = usuario;
	}

	public ArticuloCarrito() {
		super();
	}

	public ArticuloCarrito(int cantidad, Producto producto, Usuario usuario) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.usuario = usuario;
	}

	public ArticuloCarrito(Long id) {
		super();
		this.id = id;
	}

}
