package br.com.ControleDoBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ControleDoBanco.model.DadosRepresentacao;

@Repository
public interface DadosRepresentacaoRepository extends JpaRepository<DadosRepresentacao,Long> {
	
}
