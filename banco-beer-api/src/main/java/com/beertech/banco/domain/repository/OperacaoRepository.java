package com.beertech.banco.domain.repository;

import java.util.List;

import com.beertech.banco.domain.Operacao;

public interface OperacaoRepository {

	List<Operacao> getAll();
	Operacao save(Operacao operacao);	
}
