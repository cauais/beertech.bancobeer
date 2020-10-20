package com.beertech.banco.domain.service.impl;

import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.repository.OperacaoRepository;
import com.beertech.banco.domain.service.OperacaoService;

public class OperacaoServiceImpl implements OperacaoService  {

	private final OperacaoRepository repository;
	
	public OperacaoServiceImpl(OperacaoRepository operacaoaRepository) {
		this.repository = operacaoaRepository;
	}

	@Override
	public Operacao save(Operacao operacao) {
		return repository.save(operacao);
	}
}
