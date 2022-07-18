package br.com.ControleDoBanco.Vo;

import java.util.ArrayList;
import java.util.List;

public class RelatorioDeAgenciasVo {
	private String Banco;
	private String Agencia;
	private Double valorMovimentado;
	
	
	public RelatorioDeAgenciasVo(String banco, String agencia, Double valorMovimentado) {
		Banco = banco;
		Agencia = agencia;
		this.valorMovimentado = valorMovimentado;
	}
	
	public String getBanco() {
		return Banco;
	}
	public void setBanco(String banco) {
		Banco = banco;
	}
	public String getAgencia() {
		return Agencia;
	}
	public void setAgencia(String agencia) {
		Agencia = agencia;
	}
	public Double getValorMovimentado() {
		return valorMovimentado;
	}
	public void setValorMovimentado(Double valorMovimentado) {
		this.valorMovimentado = valorMovimentado;
	}

	public static List<RelatorioDeAgenciasVo> filtrarValores(List<RelatorioDeAgenciasVo> valorTotalDaAgencia) {
		List<RelatorioDeAgenciasVo> dadosFiltrados = new ArrayList<>();
		for (RelatorioDeAgenciasVo relatorioDeAgenciasVo : valorTotalDaAgencia) {
			if(relatorioDeAgenciasVo.getValorMovimentado() > 1000000000) {
				dadosFiltrados.add(relatorioDeAgenciasVo);
			}
		}
		
		return dadosFiltrados;
	}
	
}
