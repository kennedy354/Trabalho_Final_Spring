package br.com.ifce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>{

}