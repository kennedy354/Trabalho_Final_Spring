package br.com.ifce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItensCompra implements Serializable{

	public ItensCompra() {
		super();
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Prato prato;
	
	@ManyToOne
	private Compra compra;
	
	private Integer quantidade;
	private Double valorUnitario=0.;
	private Double valorTotal=0.;
	private Double somaTotal=0.;
	public Double getSomaTotal() {
		return somaTotal;
	}
	public void setSomaTotal(Double somaTotal) {
		this.somaTotal = somaTotal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Prato getPrato() {
		return prato;
	}
	public void setPrato(Prato prato) {
		this.prato = prato;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Integer getQuantidade() {
		if(quantidade == null) {
			quantidade=0;
		}
		
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Double getValorTotal() {
		if(valorTotal == null) {
			valorTotal=0.;
		}
		
		return valorTotal;
	}
				
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	}
