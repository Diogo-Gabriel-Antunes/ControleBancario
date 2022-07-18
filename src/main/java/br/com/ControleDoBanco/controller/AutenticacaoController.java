package br.com.ControleDoBanco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ControleDoBanco.model.Usuario;
import br.com.ControleDoBanco.repository.UsuarioRepository;

@Controller
public class AutenticacaoController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@RequestMapping("/login")
	public String PaginaInicial() {
		return "autenticacao/login";
	}
	
	@GetMapping("/usuarios")
	public String paginaDeUsuarios(Model model) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		
		return "autenticacao/usuarios";
	}
	
	@GetMapping("/cadastro")
	public String paginaDeCadastro() {
		return "autenticacao/cadastro";
	}
	
	@PostMapping("/cadastroEnviado")
	public String posCadastro(@RequestParam("username") String username, @RequestParam("email") String email, Model model) {
		Usuario usuario1 = Usuario.gerarsenha();
		model.addAttribute("usuarios", usuario1);
		Usuario usuario = Usuario.cadastrar(username, email, usuario1);
		usuarioRepository.save(usuario);
		return "autenticacao/cadastroEnviado";
	}
	
	@GetMapping("/editar")
	public String EditarUsuario(@RequestParam("id") String id, Model model) {
		Long idfinal = Long.parseLong(id);
		Optional<Usuario> findById = usuarioRepository.findById(idfinal);
		model.addAttribute("usuario", findById.get());
		
		return "autenticacao/editar";
	}
	
	@GetMapping("/remover")
	public String deletarUsuario(@RequestParam("id") String id) {
		Long idfinal = Long.parseLong(id);
		Optional<Usuario> usuario = usuarioRepository.findById(idfinal);
		usuario.get().setEnable(0);
		usuarioRepository.save(usuario.get());
		
		return "autenticacao/UsuarioDeletado";
	}
}
