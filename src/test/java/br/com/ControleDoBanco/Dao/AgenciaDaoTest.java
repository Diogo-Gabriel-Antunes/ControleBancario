package br.com.ControleDoBanco.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ControleDoBanco.Vo.RelatorioDeAgenciasVo;

@SpringBootTest
class AgenciaDaoTest {
	
	@Autowired
	private EntityManager em;
	
	@Test
	void retornarRelatorio() {
		AgenciaDao dao = new AgenciaDao(em);
		List<RelatorioDeAgenciasVo> valorTotalDaAgencia = dao.valorTotalDaAgencia(2022, 01);
		
		assertNotNull(valorTotalDaAgencia);
		assertEquals(8, valorTotalDaAgencia.size());
	}

}
