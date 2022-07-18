package br.com.ControleDoBanco.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.ControleDoBanco.model.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;

	@Test
	public void carregarUsuarioPeloEmail() {
		String email = "admin@email.com.br";
		Optional<Usuario> usuario = repository.findByEmail(email);
		assertNotNull(usuario.get());
		assertEquals(email, usuario.get().getEmail());
	}
	

}
