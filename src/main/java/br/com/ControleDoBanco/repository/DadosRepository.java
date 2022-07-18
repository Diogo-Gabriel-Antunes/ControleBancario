package br.com.ControleDoBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ControleDoBanco.model.DadosBancarios;

@Repository
public interface DadosRepository extends JpaRepository<DadosBancarios,Long> {

	@Query("SELECT D FROM DadosBancarios D WHERE MONTH(dataTransacao) = :mes and YEAR(dataTransacao) = :ano and valorTransacao >= 100000")
	List<DadosBancarios> transacoesSuspeitas(Integer ano, Integer mes);


	

	
}
