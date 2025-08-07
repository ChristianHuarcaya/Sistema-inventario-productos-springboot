package com.sistema.inventario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;
import com.sistema.inventario.usuario.Usuario;
import com.sistema.inventario.usuario.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repositorio;

    @Test
    public void testCrearCategoria() {
        Categoria categoria = new Categoria("Electrónicos");
        Categoria categoriaGuardada = repositorio.save(categoria);

        assertThat(categoriaGuardada.getId()).isGreaterThan(0);
    }
    

    
}
