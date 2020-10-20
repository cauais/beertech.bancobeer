package com.beertech.banco.infrastructure.repository.mongo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.TipoOperacao;

@Document("operacoes")
public class MongoOperacao {

	@Id
	private String id;
	private LocalDateTime dataHora;
	private BigDecimal valor;
	private TipoOperacao tipo;
	@DBRef
	private MongoConta conta;
	
	public MongoOperacao() {
	}
	
	public MongoOperacao(String id, BigDecimal valor, TipoOperacao tipo, LocalDateTime dataHora, MongoConta conta) {
		this.id = id;
		this.valor = valor;
		this.tipo = tipo;
		this.dataHora = dataHora;
		this.conta = conta;
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
		return new MongoOperacao(operacao.getId(), operacao.getValor(), operacao.getTipo(), operacao.getDataHora(), new MongoConta().fromDomain(operacao.getConta()));
	}
	
	public Operacao toDomain(MongoOperacao mongoOperacao) {
		return new Operacao(mongoOperacao.id, mongoOperacao.getDataHora(), mongoOperacao.getValor(), mongoOperacao.getTipo(), new MongoConta().toDomain(mongoOperacao.conta));
	}

}
