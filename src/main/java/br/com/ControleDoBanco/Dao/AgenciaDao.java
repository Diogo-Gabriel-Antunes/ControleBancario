package br.com.ControleDoBanco.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ControleDoBanco.Vo.RelatorioDeAgenciasVo;

public class AgenciaDao {
	
	private EntityManager em;
	
	public AgenciaDao(EntityManager em) {
		this.setEm(em);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<RelatorioDeAgenciasVo> valorTotalDaAgencia(Integer ano, Integer mes) {
		String jpql = "SELECT new br.com.ControleDoBanco.Vo.RelatorioDeAgenciasVo( D.bancoOrigem, D.agenciaOrigem, SUM(D.valorTransacao)) FROM DadosBancarios D WHERE MONTH(dataTransacao) = :mes and YEAR(dataTransacao) = :ano GROUP BY D.bancoOrigem";
		return em.createQuery(jpql, RelatorioDeAgenciasVo.class)
				.setParameter("mes", mes)
				.setParameter("ano", ano)
				.getResultList();
	}
}
