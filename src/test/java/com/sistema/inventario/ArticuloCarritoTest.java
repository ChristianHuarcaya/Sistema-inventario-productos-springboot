package com.sistema.inventario;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.carrito.compras.ArticuloCarrito;
import com.sistema.inventario.carrito.compras.ArticuloCarritoRepository;
import com.sistema.inventario.producto.Producto;
import com.sistema.inventario.usuario.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTest {
	@Autowired
	private ArticuloCarritoRepository repositorio;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testAñadirArticulo() {
		Producto producto = entityManager.find(Producto.class, 2L);
		Usuario usuario = entityManager.find(Usuario.class, 1L);

		// Asegúrate de que el constructor de ArticuloCarrito sea correcto.
		
		ArticuloCarrito articulo = new ArticuloCarrito();
		articulo.setProducto(producto);
		articulo.setUsuario(usuario);
		articulo.setCantidad(1); // Puedes ajustar la cantidad según sea necesario

		repositorio.save(articulo);
	}
	
	@Test
	@Rollback(false)
	public void testAñadirMultiplesArticulo() {
	    // Recuperar entidades existentes desde la base de datos
	    Usuario usuario = entityManager.find(Usuario.class, 1L);
	    Producto producto1 = entityManager.find(Producto.class, 2L);
	    Producto producto2 = entityManager.find(Producto.class, 3L);

	    
	    // Crear artículos con los objetos gestionados por JPA
	    ArticuloCarrito articulo1 = new ArticuloCarrito(3, producto1, usuario);
	    ArticuloCarrito articulo2 = new ArticuloCarrito(5, producto2, usuario);

	    // Guardar en lote
	    repositorio.saveAll(List.of(articulo1, articulo2));

	    System.out.println("Artículos añadidos correctamente.");
	}
	
	@Test
	public void testListarArticulos() {
		
		List<ArticuloCarrito> articulos = repositorio.findAll();
		articulos.forEach(System.out::println);
	}
	@Test
	public void testActualizarArticulos() {
		ArticuloCarrito articulo = repositorio.findById(1L).get();
		articulo.setCantidad(11);
		articulo.setProducto(new Producto(3L));
	}
	@Test
	public void testElimarArticulo() {
		repositorio.deleteById(1L);
		
	}
	



}
