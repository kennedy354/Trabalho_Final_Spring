package br.com.ifce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifce.model.Pessoa;
import br.com.ifce.service.PessoaService;

@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/home/cadastro")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}
	
	@RequestMapping("/home/cadastro/salvar")
	public ModelAndView salvar(Pessoa pessoa, BindingResult result) {
		
		
		ModelAndView mv = new ModelAndView("cadastro");
		
		if(result.hasErrors()) {
			
			return mv;
		}
		
		pessoaService.cadastrarPessoa(pessoa);
		
		ModelAndView mv2 = new ModelAndView("salvou");
		return mv2;

	}
	
	

	
}
