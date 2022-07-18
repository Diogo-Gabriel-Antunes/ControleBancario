package br.com.ControleDoBanco.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class DadosBancarios {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bancoOrigem;
	private String agenciaOrigem;
	private String contaOrigem;
	private String bancoDestino;
	private String agenciaDestino;
	private String contaDestino;
	private float valorTransacao;
	private LocalDateTime dataTransacao;
	@ManyToOne
	private DadosRepresentacao representacao;
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	//private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	
	
	
	public String getBancoOrigem() {
		return bancoOrigem;
	}
	public void setBancoOrigem(String bancoOrigem) {
		this.bancoOrigem = bancoOrigem;
	}
	public String getAgenciaOrigem() {
		return agenciaOrigem;
	}
	public void setAgenciaOrigem(String agenciaOrigem) {
		this.agenciaOrigem = agenciaOrigem;
	}
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public String getBancoDestino() {
		return bancoDestino;
	}
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}
	public String getAgenciaDestino() {
		return agenciaDestino;
	}
	public void setAgenciaDestino(String agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}
	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}
	public float getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(float valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public DadosBancarios salvar(String[] split, Usuario nome) {
		DadosBancarios dados = new DadosBancarios();
		dados.setBancoOrigem(split[0]);
		dados.setAgenciaOrigem(split[1]);
		dados.setContaOrigem(split[2]);
		dados.setBancoDestino(split[3]);
		dados.setAgenciaDestino(split[4]);
		dados.setContaDestino(split[5]);
		dados.setValorTransacao(Float.parseFloat(split[6]));
		dados.setDataTransacao(LocalDateTime.parse(split[7]));
		dados.setUsuario(nome);
		return dados;
	}
	
	public boolean validacao(String[] elemento) {
		for(int i = 0;i<elemento.length;i++) {
			if(elemento[i].isEmpty() || elemento.equals(null)) {
				return true;
			}
		}
		return false;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<DadosBancarios> validarDatas(List<DadosBancarios> listaDados) {
		List<DadosBancarios> listafinal = new ArrayList<>();
		LocalDate data1 = listaDados.get(0).getDataTransacao().toLocalDate();
		for (DadosBancarios dadosBancarios : listaDados) {
			LocalDate data2 = dadosBancarios.getDataTransacao().toLocalDate();
			Period periodo = Period.between(data1, data2);
			if(periodo.getDays()==0) {
				listafinal.add(dadosBancarios);
			}
		}
		return listafinal;
	}
	public static List<DadosBancarios> filtroDeAgencia(List<DadosBancarios> agenciaSuspeita) {
		List<DadosBancarios> dados = new ArrayList<>();
		
		for (DadosBancarios dadosBancarios : agenciaSuspeita) {
			if(dadosBancarios.getValorTransacao() > 1000000000) {
				dados.add(dadosBancarios);
			}
		}
		
		return dados;
	}
	public static DadosBancarios salvarDadosXML(List<String> dadosGerais) {
		DadosBancarios dados = new DadosBancarios();
		dados.setBancoOrigem(dadosGerais.get(0));
		dados.setAgenciaOrigem(dadosGerais.get(1));
		dados.setContaOrigem(dadosGerais.get(2));
		dados.setBancoDestino(dadosGerais.get(3));
		dados.setAgenciaDestino(dadosGerais.get(4));
		dados.setContaDestino(dadosGerais.get(5));
		dados.setValorTransacao(Float.parseFloat(dadosGerais.get(6)));
		dados.setDataTransacao(LocalDateTime.parse(dadosGerais.get(7)));
		return dados;
	}
	
}
