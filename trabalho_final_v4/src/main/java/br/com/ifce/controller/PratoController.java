	package br.com.ifce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifce.model.Prato;
import br.com.ifce.service.PratoService;

@Controller
public class PratoController {
	@Autowired
	private PratoService pratoService;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/home/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("formulario");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@RequestMapping("/home/formulario/salvar")
	public ModelAndView salvar(Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
		
		ModelAndView mv = new ModelAndView("formulario");
		
		if(result.hasErrors()) {
			
			return mv;
		}
		
		pratoService.CadastrarPrato(prato, imagem);
		//mv.addObject("mensagem", "Prato cadastrado com sucesso!");
		//return mv;
		
		ModelAndView mv2 = new ModelAndView("salvou");
		return mv2;
		
	}
	
	@GetMapping("/home/lista")
	public ModelAndView listarJogos() {
		List<Prato> pratos = pratoService.listarPratos();
		
		ModelAndView mv = new ModelAndView("lista-pratos");
		mv.addObject("listaDePratos", pratos);
		
		return mv;
	}
	
	@GetMapping("/home/lista/excluir/{codigo}")
	public ModelAndView apagar(@PathVariable Long codigo) {
		pratoService.excluir(codigo);
		
		ModelAndView mv = new ModelAndView("redirect:/home/lista");
		
		return mv;
	}
	
	@RequestMapping("/home/lista/atualizar/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Optional<Prato> prato = pratoService.buscarPorID(codigo);
		
		ModelAndView mv = new ModelAndView("formulario");
		mv.addObject("prato", prato);
		
		return mv;
	}
}