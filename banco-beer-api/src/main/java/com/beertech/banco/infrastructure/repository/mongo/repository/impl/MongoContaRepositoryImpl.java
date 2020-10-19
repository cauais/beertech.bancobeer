package com.beertech.banco.infrastructure.repository.mongo.repository.impl;

import java.util.List;
import java.util.Optional;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.repository.ContaRepository;

public class MongoContaRepositoryImpl implements ContaRepository  {
	
	private com.beertech.banco.infrastructure.repository.mongo.ContaRepository contaRepository;

	@Override
	public Optional<Conta> findByHash(String hash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta save(Conta conta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conta> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conta> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
