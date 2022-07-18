package br.com.ControleDoBanco.Vo;

import java.util.ArrayList;
import java.util.List;

public class RelatorioDeContasVo {
	private String banco;
	private String Agencia;
	private String Conta;
	private Double Valor;
	
	public RelatorioDeContasVo(String banco, String agencia, String conta, Double valor) {
		this.banco = banco;
		Agencia = agencia;
		Conta = conta;
		Valor = valor;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return Agencia;
	}

	public void setAgencia(String agencia) {
		Agencia = agencia;
	}

	public String getConta() {
		return Conta;
	}

	public void setConta(String conta) {
		Conta = conta;
	}

	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		Valor = valor;
	}

	public static List<RelatorioDeContasVo> filtrarValores(List<RelatorioDeContasVo> valorTotalDaConta) {
		List<RelatorioDeContasVo> relatoriofinal = new ArrayList<>();
		for (RelatorioDeContasVo relatorioDeContasVo : valorTotalDaConta) {
			if(relatorioDeContasVo.getValor()> 1000000) {
				relatoriofinal.add(relatorioDeContasVo);
			}
		}
		return relatoriofinal;
	}
	
	
}
