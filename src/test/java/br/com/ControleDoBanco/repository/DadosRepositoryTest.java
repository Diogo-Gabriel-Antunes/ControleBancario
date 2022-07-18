package br.com.ControleDoBanco.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.ControleDoBanco.model.DadosBancarios;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DadosRepositoryTest {
	
	
	@Autowired
	private DadosRepository repository;

	@Test
	void deveriaCarregarAsTransacoesSuspeitas() {
		int ano = 2022;
		int mes = 01;
		List<DadosBancarios> transacoesSuspeitas = repository.transacoesSuspeitas(ano, mes);
		assertNotNull(transacoesSuspeitas);
		assertEquals(3, transacoesSuspeitas.size());
	}
	
	@Test
	void naoDeveriaCarregarAsTransacoesSuspeitas() {
		int ano = 2022;
		int mes = 05;
		List<DadosBancarios> transacoesSuspeitas = repository.transacoesSuspeitas(ano, mes);
		assertNotNull(transacoesSuspeitas);
		assertEquals(0, transacoesSuspeitas.size());
	}
}
