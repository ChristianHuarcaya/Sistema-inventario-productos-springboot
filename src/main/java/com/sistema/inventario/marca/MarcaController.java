package com.sistema.inventario.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;

@Controller
public class MarcaController {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	// Mostrar formulario para crear nueva marca
	@GetMapping("/marcas/nuevo")
	public String mostrarFormularioDeCrearNuevaMarca(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		modelo.addAttribute("listaCategorias", listaCategorias);
		modelo.addAttribute("marca", new Marca());
		return "marca_formulario";
	}

	// Guardar marca
	@PostMapping("/marcas/guardar")
	public String guardarMarca(Marca marca) {
		marcaRepository.save(marca);
		return "redirect:/marcas";  // 
	}

	// Listar todas las marcas
	@GetMapping("/marcas")
	public String listarMarcas(Model modelo) {
		List<Marca> listaMarcas = marcaRepository.findAll();
		modelo.addAttribute("listaMarcas", listaMarcas);
		return "marcas";
	}

	// Mostrar formulario para editar una marca existente
	@GetMapping("/marcas/editar/{id}")
	public String mostrarFormularioDeModificarMarca(@PathVariable("id") Long id, Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		Marca marca = marcaRepository.findById(id).orElse(null);

		if (marca == null) {
			return "redirect:/marcas";  // Redirige si no se encuentra la marca
		}

		modelo.addAttribute("listaCategorias", listaCategorias);
		modelo.addAttribute("marca", marca);
		return "marca_formulario";
	}
	@GetMapping("/marcas/eliminar/{id}")
    public String eliminarMarca(@PathVariable("id") Long id) {
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }

	
}

