package br.com.ControleDoBanco.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ControleDoBanco.Vo.RelatorioDeContasVo;

@SpringBootTest
class ContaDaoTest {
	
	@Autowired
	private EntityManager em;

	@Test
	void retornarContas() {
		ContaDao dao = new ContaDao(em);
		List<RelatorioDeContasVo> valorTotalDaConta = dao.valorTotalDaConta(2022, 01);
		
		assertNotNull(valorTotalDaConta);
		assertEquals(2, valorTotalDaConta.size());
	}

}
