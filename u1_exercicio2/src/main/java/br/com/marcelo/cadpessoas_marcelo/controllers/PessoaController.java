package br.com.marcelo.cadpessoas_marcelo.crontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.marcelo.cadpessoas_marcelo.model.Pessoa;
import br.com.marcelo.cadpessoas_marcelo.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {
	@Autowired
	PessoaRepository pessoaRepo;

	PessoaController(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

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
}