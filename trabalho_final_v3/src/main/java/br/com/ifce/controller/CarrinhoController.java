package br.com.ifce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifce.model.Carrinho;
import br.com.ifce.service.CarrinhoService;

@Controller
public class CarrinhoController {
	@Autowired
	private CarrinhoService carrinhoService;
	
	@RequestMapping("/home/formulario/salvar-carrinho")
	public ModelAndView salvar(Carrinho carrinho, BindingResult result) {
		
		
		ModelAndView mv = new ModelAndView("adicionar-carrinho");
		
		if(result.hasErrors()) {
			
			return mv;
		}
		
		carrinhoService.AdicionarCarrinho(carrinho);
		//mv.addObject("mensagem", "Prato cadastrado com sucesso!");
		//return mv;
		
		ModelAndView mv2 = new ModelAndView("salvouCarrinho");
		return mv2;
	}
	
	@GetMapping("/home/carrinho")
	public ModelAndView listaCompras() {
		List<Carrinho> carrinho = carrinhoService.listaCompras();
		
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject("listaDeCompras", carrinho);
		
		return mv;
	}
	
	@GetMapping("/home/carrinho/remover/{codigo}")
	public ModelAndView remover(@PathVariable Long codigo) {
		carrinhoService.excluir(codigo);
		
		ModelAndView mv = new ModelAndView("redirect:/home/carrinho");
		
		return mv;
	}
}
