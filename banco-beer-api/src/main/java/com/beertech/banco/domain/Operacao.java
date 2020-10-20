package com.beertech.banco.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class Operacao {	

	private String id;
	private LocalDateTime dataHora;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipo;
	
	private Conta conta;

	public Operacao() {
	}
	
	public Operacao(BigDecimal valor, TipoOperacao tipo) {
		this.dataHora = LocalDateTime.now();
		this.valor = valor;
		this.tipo = tipo;
	}

	public Operacao(String id, LocalDateTime dataHora, BigDecimal valor, TipoOperacao tipo, Conta conta) {
		this.id = id;
		this.dataHora = dataHora;
		this.valor = valor;
		this.tipo = tipo;
		this.conta = conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public String getId() {
		return id;
	}

	public Conta getConta() {
		return conta;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public TipoOperacao getTipo() {
		return tipo;
	}
}
