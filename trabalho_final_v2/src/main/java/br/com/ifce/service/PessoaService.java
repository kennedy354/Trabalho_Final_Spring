package br.com.ifce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifce.model.Pessoa;
import br.com.ifce.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	public void cadastrarPessoa(Pessoa pessoa) {
		
		pessoaRepo.save(pessoa);
	}
	
	public List<Pessoa> listarPessoas(){
		return pessoaRepo.findAll();
	}
	
	
	public void excluir(Long id) {
		pessoaRepo.deleteById(id);
	}
	
	public Optional<Pessoa> buscarPorID(Long id) {
		return pessoaRepo.findById(id);
		
	}
}
