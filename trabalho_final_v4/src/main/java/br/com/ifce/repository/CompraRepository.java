package br.com.ifce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.model.Compra;




@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	
}
