package com.sistema.inventario.categoria;


import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas las categorías
    @GetMapping("/categorias")
    public String listarCategorias(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }

    // Mostrar formulario para nueva categoría
    @GetMapping("/categorias/nuevo")
    public String mostrarFormularioNuevaCategoria(Model modelo) {
        modelo.addAttribute("categoria", new Categoria());  // ✅ Usa constructor vacío
        return "categoria_formulario";
    }

    // Guardar una categoría
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    // Eliminar una categoría
    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {  // ✅ ID tipo Integer
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
 // Si quieres una ruta para el dashboard, usa una ruta diferente
 	@GetMapping("/categoria/dashboard")
 	public String mostrarDashboard() {
 		return "dashboard"; // Página del dashboard relacionada con productos
 	}
 	@GetMapping("/categorias/editar/{id}")
 	public String mostrarFormularioDeModificarCategoria(@PathVariable("id") Long id, Model modelo) {
 	    Categoria categoria = categoriaRepository.findById(id).orElse(null);
 	    modelo.addAttribute("categoria", categoria);
 	    return "categoria_formulario";
 	}


}



