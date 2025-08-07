package com.sistema.inventario.usuario;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, nullable = false, unique = true)
	private String email;

	@Column(length = 10, nullable = false, unique = true)
	private String password;

	// Relación ManyToMany con cascada para persistir los roles automáticamente
	// cuando se guarda el usuario
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	// Constructor sin parámetros
	public Usuario() {
		super();
	}

	// Constructor con parámetros
	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// Constructor con roles
	public Usuario(String email, String password, Set<Rol> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	// Constructor completo
	public Usuario(Long id, String email, String password, Set<Rol> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	// Métodos de acceso (getters y setters)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Usuario(Long id) {
		super();
		this.id = id;
	}

	// Métodos para añadir y eliminar roles
	public void añadirRol(Rol rol) {
		this.roles.add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
	}

	// Override de equals y hashCode para comparación en colecciones basadas en hash
	// (opcional)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Usuario usuario = (Usuario) obj;
		return id != null && id.equals(usuario.id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + "]";
	}

}
