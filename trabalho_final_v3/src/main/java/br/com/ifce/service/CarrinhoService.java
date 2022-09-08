package br.com.ifce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifce.model.Carrinho;
import br.com.ifce.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepo;
	
public void AdicionarCarrinho(Carrinho carrinho) {
		
		carrinhoRepo.save(carrinho);
	}

public List<Carrinho> listaCompras(){
	return carrinhoRepo.findAll();
}

public void excluir(Long codigo) {
	carrinhoRepo.deleteById(codigo);
}
	
}
