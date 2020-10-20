package com.beertech.banco.infrastructure.repository.mongo.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.repository.OperacaoRepository;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoOperacao;

@Repository
public class MongoOperacaoRepositoryImpl implements OperacaoRepository  {
	
	private com.beertech.banco.infrastructure.repository.mongo.OperacaoRepository operacaoRepository;

	@Autowired
	public MongoOperacaoRepositoryImpl(com.beertech.banco.infrastructure.repository.mongo.OperacaoRepository operacaoRepository) {
		this.operacaoRepository = operacaoRepository;
	}

	@Override
	public List<Operacao> getAll() {
		List<MongoOperacao> findAll = operacaoRepository.findAll();
		return findAll.stream().map(new MongoOperacao()::toDomain).collect(Collectors.toList());
	}

	@Override
	public Operacao save(Operacao operacao) {
		return new MongoOperacao().toDomain((operacaoRepository.save(new MongoOperacao().fromDomain(operacao))));
	}

	@Override
	public List<Operacao> getByConta(Conta conta) {
		List<MongoOperacao> findByConta = operacaoRepository.findByConta(new MongoConta().fromDomain(conta));
		return findByConta.stream().map(new MongoOperacao()::toDomain).collect(Collectors.toList());
	}
	
	
	

}
