package br.com.ifce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ifce.util.AulaFileUtils;

import br.com.ifce.model.Prato;
import br.com.ifce.repository.PratoRepository;

@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepo;
	
	public void CadastrarPrato(Prato prato, MultipartFile imagem) {
		String caminho = "images/"+prato.getNome()+".png";
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		pratoRepo.save(prato);
	}
	
	public List<Prato> listarPratos(){
		return pratoRepo.findAll();
	}
	
	public void excluir(Long codigo) {
		pratoRepo.deleteById(codigo);
	}
	
	public Optional<Prato> buscarPorID(Long codigo) {
		return pratoRepo.findById(codigo);
		
	}
}
