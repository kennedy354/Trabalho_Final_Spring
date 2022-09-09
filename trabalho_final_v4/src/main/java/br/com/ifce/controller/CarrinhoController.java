package br.com.ifce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.ifce.repository.PratoRepository;
import br.com.ifce.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();

	@Autowired
	private PratoRepository pratoRepository;

	public void calcularTotal() {
		
		for (ItensCompra it : itensCompra) {
			
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}

	}

	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaPratos", itensCompra);
		return mv;

	}

	@RequestMapping("/removerPrato/{id}")
	public ModelAndView removerPrato(@PathVariable Long id) {

		ModelAndView mv = new ModelAndView("carrinho");

		for (ItensCompra it : itensCompra) {
			if (it.getPrato().getCodigo().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		mv.addObject("listaPratos", itensCompra);

		return mv;

	}

	@RequestMapping("/alterarQuantidade/{id}/{acao}")
	public ModelAndView alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {

		ModelAndView mv = new ModelAndView("carrinho");

		for (ItensCompra it : itensCompra) {
			if (it.getPrato().getCodigo().equals(id)) {
				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				} else if (acao == 0) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				}
				break;
			}
		}
		mv.addObject("listaPratos", itensCompra);

		return mv;

	}

	@RequestMapping("/adicionarCarrinho/{id}")
	public ModelAndView adicionarCarrinho(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("carrinho");

		Optional<Prato> prat = pratoRepository.findById(id);
		Prato prato = prat.get();

		int controle = 0;
		for (ItensCompra it : itensCompra) {
			if (it.getPrato().getCodigo().equals(prato.getCodigo())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setPrato(prato);
			item.setValorUnitario(prato.getPreco());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario()));
			itensCompra.add(item);
		}
		mv.addObject("listaPratos", itensCompra);

		return mv;

	}

}
