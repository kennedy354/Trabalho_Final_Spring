package br.com.ifce.controller;

import java.util.List;
import java.util.Optional;

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
		//mv.addObject("mensagem", "Prato cadastrado com sucesso!");
		//return mv;
		
		ModelAndView mv2 = new ModelAndView("salvou");
		return mv2;

	}
	
	@GetMapping("/home/usuarios")
	public ModelAndView listarPessoas() {
		List<Pessoa> pessoa = pessoaService.listarPessoas();
		
		ModelAndView mv = new ModelAndView("lista-usuarios");
		mv.addObject("listaDePessoas", pessoa);
		
		return mv;
	}
	
	@GetMapping("/home/usuarios/excluir/{id}")
	public ModelAndView apagar(@PathVariable Long id) {
		pessoaService.excluir(id);
		
		ModelAndView mv = new ModelAndView("redirect:/home/usuarios");
		
		return mv;
	}
	
	@RequestMapping("/home/usuarios/atualizar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorID(id);
		
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("pessoa", pessoa);
		
		return mv;
	}
}
