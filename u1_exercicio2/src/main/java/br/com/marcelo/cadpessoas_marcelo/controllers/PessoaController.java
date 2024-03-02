package br.com.marcelo.cadPessoas_marcelo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.marcelo.cadPessoas_marcelo.model.Pessoa;
import br.com.marcelo.cadPessoas_marcelo.repositories.PessoaRepository;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/")

public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepo;

	public PessoaController(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

//esse metodo retorna a string assim que acessa a barra (raiz do site localhost)
	@GetMapping
	public String index() {
		return "index.html";
	}

	@GetMapping("/listarPessoas")
	public ModelAndView listarPessoas() {
		List<Pessoa> todasAsPessoas = pessoaRepo.findAll();
		ModelAndView modelAndView = new ModelAndView("listarPessoas");
		modelAndView.addObject("todasAsPessoas", todasAsPessoas);
		return modelAndView;
	}

@GetMapping("/adicionarPessoas")
public ModelAndView formularioAdicionarPessoas() {
	ModelAndView modelAndView = new ModelAndView("adicionarPessoas");
	modelAndView.addObject(new Pessoa());
		return modelAndView;
}
	@PostMapping("/adicionarPessoa")
	public String adicionarPessoa(Pessoa p){
		this.pessoaRepo.save(p);
	return "redirect:/listarPessoas";
	}
		
	@SuppressWarnings("null")
	@GetMapping("/remover/{id}")
	public ModelAndView removerPessoa(@PathVariable("id") long id) {
		Pessoa aRemover = pessoaRepo.findById(id).orElseThrow(
			() -> new IllegalArgumentException("Id inválido" + id));
			pessoaRepo.delete(aRemover);
			return new ModelAndView("redirect/listarPessoas");
	}
	@GetMapping("/editarPessoas")
	public ModelAndView formularioAdicionarPessoas(@PathVariable("id") long id) {
		Pessoa aEditar = pessoaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Invalido" + id));
		ModelAndView modelAndView = new ModelAndView("editarPessoas");
		modelAndView.addObject(aEditar);
		return modelAndView;
}

@PostMapping("/editar/{id}")
	public String editarPessoa(@PathVariable("id") long id, Pessoa p){
	this.pessoaRepo.save(p);
	return "redirect:/listarPessoas";

	}
}
