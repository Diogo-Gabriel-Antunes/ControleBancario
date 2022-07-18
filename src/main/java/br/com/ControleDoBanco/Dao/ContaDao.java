package br.com.ControleDoBanco.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ControleDoBanco.Vo.RelatorioDeContasVo;

public class ContaDao {
	
	private EntityManager em;
	
	public ContaDao(EntityManager em) {
		this.setEm(em);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<RelatorioDeContasVo> valorTotalDaConta(Integer ano, Integer mes) {
		String jpql = "SELECT new br.com.ControleDoBanco.Vo.RelatorioDeContasVo( D.bancoOrigem, D.agenciaOrigem,D.contaOrigem, SUM(D.valorTransacao)) FROM DadosBancarios D WHERE MONTH(dataTransacao) = :mes and YEAR(dataTransacao) = :ano GROUP BY D.contaOrigem";
		return em.createQuery(jpql, RelatorioDeContasVo.class)
				.setParameter("mes", mes)
				.setParameter("ano", ano)
				.getResultList();
	}
}
