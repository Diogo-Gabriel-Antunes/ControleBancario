package br.com.ControleDoBanco.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.ControleDoBanco.repository.DadosRepository;
import br.com.ControleDoBanco.repository.DadosRepresentacaoRepository;


@Component
public class Arquivo {
	@Autowired
	private DadosRepository dadosRepository;
	@Autowired
	private DadosRepresentacaoRepository representacaoRepository;
	
	private DadosBancarios dados;
	
	private DadosRepresentacao representacao;
	
	public void leituradoarquivo(MultipartFile arquivo, Scanner sc, String caminhoArquivos) {
		try {
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoArquivos + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = new File(caminhoArquivos+arquivo.getOriginalFilename());
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}
	
	public void SalvarDados(MultipartFile arquivo, Scanner sc, String caminhoArquivos, Usuario nome) {
		
		List<DadosBancarios> ListaDados = new ArrayList<>();
		representacao = new DadosRepresentacao();
		
		this.lerOarquivo(arquivo, caminhoArquivos);
		
		File file = new File(caminhoArquivos+arquivo.getOriginalFilename());
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				dados = new DadosBancarios();
				String line = sc.nextLine();
				String[] split = line.split(",");
				if(dados.validacao(split)) {
					continue;
				}else {
					dados = dados.salvar(split, nome);
					ListaDados.add(dados);
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		ListaDados = dados.validarDatas(ListaDados);
		dadosRepository.saveAll(ListaDados);
		representacao = representacao.salvar(nome,ListaDados);
		representacaoRepository.save(representacao);
	}

	public void salvarDadosXml(MultipartFile arquivo,String caminhoArquivos, Usuario nome) {
		this.lerOarquivo(arquivo, caminhoArquivos);
		List<DadosBancarios> listDeDados = new ArrayList<>();
		DadosBancarios dadosBancarios = new DadosBancarios();
		List<String> dadosGerais = new ArrayList<>();
		try {
			File arquivoXML = new File(caminhoArquivos+ arquivo.getOriginalFilename());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(arquivoXML);
			NodeList listaDeTransacoes = document.getElementsByTagName("transacao");
			
			for (int i = 0; i < listaDeTransacoes.getLength(); i++) {
				
				Node noTransacao = listaDeTransacoes.item(i);
				if(noTransacao.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoTransacao = (Element) noTransacao;
					NodeList listaDeProcedencia = elementoTransacao.getChildNodes();
					for (int j = 0; j < listaDeProcedencia.getLength(); j++) {
						Node procedencia = listaDeProcedencia.item(j);
						if(procedencia.getNodeType() == Node.ELEMENT_NODE) {
							Element elementoFilho = (Element) procedencia;
							NodeList listaDeDados = elementoFilho.getChildNodes();
							for (int k = 0; k < listaDeDados.getLength(); k++) {
								Node dados = listaDeDados.item(k);
								if(dados.getNodeType() == Node.ELEMENT_NODE) {
									Element elementoDados = (Element) dados;
									switch(elementoDados.getTagName()) {
									case "banco":
										dadosGerais.add(elementoDados.getTextContent());
										break;
									case "agencia":
										dadosGerais.add(elementoDados.getTextContent());
										break;
									case "conta":
										dadosGerais.add(elementoDados.getTextContent());
										break;
									}
								}
							}
							switch(elementoFilho.getTagName()){
							case "valor":
								dadosGerais.add(elementoFilho.getTextContent());
								break;
							case "data":
								dadosGerais.add(elementoFilho.getTextContent());
								break;
							}
							
						}
						
					}
				}
				dadosBancarios = DadosBancarios.salvarDadosXML(dadosGerais);
			}
			listDeDados.add(dadosBancarios);
			DadosRepresentacao dadosRep = new DadosRepresentacao();
			dadosRep = dadosRep.salvar(nome, listDeDados);
			List<DadosBancarios> listDeDados2 = dadosBancarios.validarDatas(listDeDados);
			dadosRepository.saveAll(listDeDados2);
			representacaoRepository.save(dadosRep);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Use um arquivo XML");
		}
	}
	
	public void lerOarquivo(MultipartFile arquivo,String caminhoArquivos) {
		try {
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoArquivos + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
