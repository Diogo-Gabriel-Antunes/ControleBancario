package br.com.ControleDoBanco.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ControleDoBanco.Dao.AgenciaDao;
import br.com.ControleDoBanco.Dao.ContaDao;
import br.com.ControleDoBanco.Vo.RelatorioDeAgenciasVo;
import br.com.ControleDoBanco.Vo.RelatorioDeContasVo;
import br.com.ControleDoBanco.model.DadosBancarios;
import br.com.ControleDoBanco.repository.DadosRepository;

@Controller
public class relatorioController {
	
	@Autowired
	private DadosRepository repository;
	@Autowired
	private EntityManager em;
	
	
	@GetMapping("/relatorios")
	public String paginaRelatoriosComparametros(@RequestParam("data")String data, Model model) {
		String[] split = data.split("-");
		Integer ano = Integer.valueOf(split[0]);
		Integer mes = Integer.valueOf(split[1]);
		List<DadosBancarios> transacoesSuspeitas = repository.transacoesSuspeitas(ano,mes);
		model.addAttribute("dados", transacoesSuspeitas);
		
		AgenciaDao agenciaDao = new AgenciaDao(em);
		List<RelatorioDeAgenciasVo> valorTotalDaAgencia = agenciaDao.valorTotalDaAgencia(ano, mes);
		List<RelatorioDeAgenciasVo> valorFiltrado = RelatorioDeAgenciasVo.filtrarValores(valorTotalDaAgencia);
		
		model.addAttribute("agencias", valorFiltrado);
		
		ContaDao contaDao = new ContaDao(em);
		List<RelatorioDeContasVo> valorTotalDaConta = contaDao.valorTotalDaConta(ano, mes);
		List<RelatorioDeContasVo> ContasFiltradas = RelatorioDeContasVo.filtrarValores(valorTotalDaConta);
		
		model.addAttribute("Contas", ContasFiltradas);
		
		return "relatorios";
		
	}
}

