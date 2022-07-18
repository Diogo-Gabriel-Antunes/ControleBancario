package br.com.ControleDoBanco.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DadosRepresentacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	@JoinColumn(name = "representacao_id")
	private List<DadosBancarios> dadosBancarios = new ArrayList<>();
	private LocalDateTime dataTransacao;
	private LocalDateTime dataDoEnvio;
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public LocalDateTime getDataDoEnvio() {
		return dataDoEnvio;
	}

	public void setDataDoEnvio(LocalDateTime dataDoEnvio) {
		this.dataDoEnvio = dataDoEnvio;
	}

	public DadosRepresentacao salvar(Usuario nome, List<DadosBancarios> listaDados) {
		DadosRepresentacao representacao = new DadosRepresentacao();
		for (DadosBancarios dadosBancarios : listaDados) {
			representacao.getDadosBancarios().add(dadosBancarios);
		}
		representacao.setDataTransacao(listaDados.get(0).getDataTransacao());
		representacao.setDataDoEnvio(LocalDateTime.now());
		representacao.setUsuario(nome);
		return representacao;
	}

	public List<DadosBancarios> getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(List<DadosBancarios> dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

}
