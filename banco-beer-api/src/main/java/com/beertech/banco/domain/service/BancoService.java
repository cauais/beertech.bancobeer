package com.beertech.banco.domain.service;

import java.math.BigDecimal;
import java.util.List;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;

public interface BancoService {
	Conta criarConta(Conta conta);
	BigDecimal saldo(String hash);
	BigDecimal realizaOperacao(String hash, Operacao operacao);
	void atualizaConta(Conta conta);
	void transferencia(String hahsDaContaOrigem, String hahsDaContaDestino, BigDecimal valor);
	List<Conta> listaTodasAsContas();
	Conta contaPeloId(String id);
	Conta contaPeloHash(String hash);
	Conta contaPeloEmail(String email);
}
