package com.sistema.inventario.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Mostrar formulario para agregar un nuevo producto
    @GetMapping("/productos/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "producto_formulario";
    }

    // Guardar un nuevo producto
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, HttpServletRequest request) {
        String[] detallesIDs = request.getParameterValues("detallesIDs");
        String[] detallesNombres = request.getParameterValues("detallesNombres");
        String[] detallesValores = request.getParameterValues("detallesValores");

        if (detallesNombres != null && detallesValores != null) {
            for (int i = 0; i < detallesNombres.length; i++) {
                String nombre = detallesNombres[i];
                String valor = detallesValores[i];

                if (nombre == null || nombre.isBlank() || valor == null || valor.isBlank()) {
                    continue; // omitir campos vacíos
                }

                if (detallesIDs != null && i < detallesIDs.length && detallesIDs[i] != null && !detallesIDs[i].isEmpty()) {
                    producto.setDetalle(Long.valueOf(detallesIDs[i]), nombre, valor);
                } else {
                    producto.añadirDetalles(nombre, valor);
                }
            }
        }

        productoRepository.save(producto);
        return "redirect:/productos";
    }

    // Listar todos los productos
    @GetMapping("/productos")
    public String listarProductos(Model modelo) {
        List<Producto> listaProductos = productoRepository.findAll();
        modelo.addAttribute("listaProductos", listaProductos);
        return "productos";
    }

    // Mostrar formulario de edición
    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioDeModificarProducto(@PathVariable("id") Long id, Model modelo) {
        Producto producto = productoRepository.findById(id).orElse(null);
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "producto_formulario";
    }

    // Eliminar producto
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

    // Página dashboard relacionada a productos
    @GetMapping("/producto/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }
}
