package com.beertech.banco.domain.repository;

import java.util.List;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;

public interface OperacaoRepository {

	List<Operacao> getAll();
	Operacao save(Operacao operacao);
	List<Operacao> getByConta(Conta conta);
}
