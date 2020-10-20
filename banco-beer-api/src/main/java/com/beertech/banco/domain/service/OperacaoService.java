package com.beertech.banco.domain.service;

import java.util.List;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;

public interface OperacaoService {
	Operacao save(Operacao operacao);
	List<Operacao> extratoPorConta(Conta conta);
}
