package br.com.ifce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.model.ItensCompra;



@Repository
public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long>{

}
