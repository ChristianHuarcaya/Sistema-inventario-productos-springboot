package com.sistema.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.usuario.Rol;
import com.sistema.inventario.usuario.Usuario;
import com.sistema.inventario.usuario.UsuarioRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCrearRoles() {
		Rol rolAdmin = new Rol("Administrador");
		Rol rolEditor = new Rol("Editor");
		Rol rolVisitante = new Rol("Visitante");

		entityManager.persist(rolAdmin);
		entityManager.persist(rolEditor);
		entityManager.persist(rolVisitante);
	}

	@Test
	public void testCrearNuevoUsuarioConRol() {
		Rol rolAdmin = entityManager.find(Rol.class, 1L); // asegúrate que el ID 1 exista
		Usuario usuario = new Usuario("Dior19@gmail.com", "12345");

		usuario.añadirRol(rolAdmin);
		usuarioRepository.save(usuario);
	}

	@Test
	public void testCrearNuevoUsuarioConDosRoles() {
		Rol rolEditor = entityManager.find(Rol.class, 2L);
		Rol rolVisitante = entityManager.find(Rol.class, 3L);

		Usuario usuario = new Usuario("Liz.com", "122345");

		usuario.añadirRol(rolEditor);
		usuario.añadirRol(rolVisitante);

		usuarioRepository.save(usuario);
	}

	@Test
	public void testAsignarRolUsuarioExistente() {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(1L);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Rol rolEditor = entityManager.find(Rol.class, 2L);
			usuario.añadirRol(rolEditor);
			usuarioRepository.save(usuario);
		}
	}

	@Test
	public void testEliminarRolUsuarioExistente() {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(1L);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Rol rol = entityManager.find(Rol.class, 2L);
			usuario.eliminarRol(rol);
			usuarioRepository.save(usuario);
		}
	}

	@Test
	public void testCrearNuevoUsuarioConNuevoRol() {
		Rol rolVendedor = new Rol("Vendedor");
		entityManager.persist(rolVendedor); // Asegurarse de guardar primero el rol

		Usuario usuario = new Usuario("loschips@gmail.com", "125555345");
		usuario.añadirRol(rolVendedor);
		usuarioRepository.save(usuario);
	}

	@Test
	public void testObtenerUsuario() {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(1L);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			entityManager.detach(usuario); // Detach para simular lectura limpia
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getRoles());
		}
	}

	@Test
	public void testEliminarUsuario() {
		Long id = 2L;
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		}
	}
}
