package br.com.ControleDoBanco.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.ControleDoBanco.model.Arquivo;
import br.com.ControleDoBanco.model.DadosRepresentacao;
import br.com.ControleDoBanco.model.Usuario;
import br.com.ControleDoBanco.repository.DadosRepresentacaoRepository;
import br.com.ControleDoBanco.repository.UsuarioRepository;

@Controller
@RequestMapping("form")
public class FormController {
	//@Autowired
	//private DadosRepository dadosRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private DadosRepresentacaoRepository representacaoRepository;
	
	@Autowired
	private Arquivo arquivoModel;
	
	private Scanner sc;
	
	private static String caminhoArquivos = "C:\\Users\\Diogo\\Documents\\arquivo\\";
	
	@GetMapping
	public String form(Model model) {
		Sort sort = Sort.by("id").descending();
		List<DadosRepresentacao> dados = representacaoRepository.findAll(sort);
		model.addAttribute("dados", dados);
		
		return "form";
	}

	@PostMapping("arquivo")
	public String resultado(@RequestParam("arquivo") MultipartFile arquivo){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(name);
		Optional<Usuario> nome = usuarioRepository.findByEmail(name);
		if(arquivo.getOriginalFilename().contains(".csv")) {
			arquivoModel.SalvarDados(arquivo, sc, caminhoArquivos, nome.get());
		}else {
			arquivoModel.salvarDadosXml(arquivo, caminhoArquivos, nome.get());
		}
		
		return "ArquivoEnviado";
	}
	
	@GetMapping("/detalhes")
	public String paginaDetalhes(@RequestParam("id") String id, Model model) {
		Long idfinal = Long.parseLong(id);
		Optional<DadosRepresentacao> dados = representacaoRepository.findById(idfinal);
		model.addAttribute("dados", dados.get().getDadosBancarios());
		
		Optional<DadosRepresentacao> representacao = representacaoRepository.findById(idfinal);
		model.addAttribute("representacao", representacao.get());
		return "detalhes";
	}
	
	

}
