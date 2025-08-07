package com.sistema.inventario.marca;

import com.sistema.inventario.categoria.Categoria;

import jakarta.persistence.*;


@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false, unique = true)
    
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
   
    private Categoria categoria;

    // Constructores
    public Marca() {}

    public Marca(Long id) {
        this.id = id;
    }

    public Marca(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // Getters y Setters
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

