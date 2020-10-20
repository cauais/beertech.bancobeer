package com.beertech.banco.domain.service.impl;

import java.util.List;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.TipoOperacao;
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
	
	

	@Override
	public List<Operacao> extratoPorConta(Conta conta) {
		// TODO Auto-generated method stub
		return repository.getByConta(conta);
	}
}
