package com.beertech.banco.infrastructure.repository.mongo.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.repository.ContaRepository;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;

@Repository
public class MongoContaRepositoryImpl implements ContaRepository  {
	
	private com.beertech.banco.infrastructure.repository.mongo.ContaRepository contaRepository;

	@Autowired
	public MongoContaRepositoryImpl(com.beertech.banco.infrastructure.repository.mongo.ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	@Override
	public Optional<Conta> findByHash(String hash) {
		return contaRepository.findByHash(hash).map(new MongoConta()::toDomain);
	}

	@Override
	public Conta save(Conta conta) {
		return new MongoConta().toDomain((contaRepository.save(new MongoConta().fromDomain(conta))));
	}

	@Override
	public Optional<Conta> findByEmail(String email) {
		return contaRepository.findByEmail(email).map(new MongoConta()::toDomain);
	}

	@Override
	public Optional<Conta> findById(String id) {
		return contaRepository.findById(id).map(new MongoConta()::toDomain);
	}

	@Override
	public List<Conta> findAll() {
		List<MongoConta> findAll = contaRepository.findAll();
		return findAll.stream().map(new MongoConta()::toDomain).collect(Collectors.toList());
		
	}

}
