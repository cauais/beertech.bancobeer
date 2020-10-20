package com.beertech.banco.infrastructure.repository.mongo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.TipoOperacao;

@Document("operacoes")
public class MongoOperacao {

	@Id
	private String id;

	private LocalDateTime dataHora;
	private BigDecimal valor;
	private TipoOperacao tipo;
	
	public MongoOperacao() {
	}
	
	public MongoOperacao(BigDecimal valor, TipoOperacao tipo, LocalDateTime dataHora) {
		this.valor = valor;
		this.tipo = tipo;
		this.dataHora = dataHora;
	}

	public String getId() {
		return id;
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
	
	public MongoOperacao fromDomain(Operacao operacao) {
		return new MongoOperacao(operacao.getValor(), operacao.getTipo(), operacao.getDataHora());
	}
	
	public Operacao toDomain(MongoOperacao mongoOperacao) {
		return new Operacao(mongoOperacao.getId(), mongoOperacao.getDataHora(), mongoOperacao.getValor(), mongoOperacao.getTipo());
	}

}
