package com.sistema.inventario.usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.producto.Producto;

@Controller
public class UsuarioController {

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Listar todos los usuarios
	@GetMapping("/usuarios")
	public String listarUsuarios(Model modelo) {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "usuarios"; // nueva vista para listar
	}

	// Mostrar formulario para crear un nuevo usuario
	@GetMapping("/usuarios/nuevo")
	public String mostrarFormularioDeRegistroDeUsuario(Model modelo) {
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		modelo.addAttribute("usuario", new Usuario());
		return "usuario_formulario";
	}

	
	@PostMapping("/usuarios/guardar")
	public String guardarUsuario(@ModelAttribute Usuario usuario) {
	    Set<Rol> rolesCompletos = new HashSet<>();

	    for (Rol rolParcial : usuario.getRoles()) {
	        rolRepository.findById(rolParcial.getId()).ifPresent(rolesCompletos::add);
	    }

	    usuario.setRoles(rolesCompletos);
	    usuarioRepository.save(usuario);
	    return "redirect:/usuarios";
	}

	// Editar usuario
	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormularioDeEditarUsuario(@PathVariable("id") Long id, Model modelo) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("listaRoles", listaRoles);
		return "usuario_formulario";
	}

	// Eliminar usuario
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuarios(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios";
	}

	// Dashboard
	@GetMapping("/usuario/")
	public String mostrarDashboard() {
		return "dashboard";
	}
}
