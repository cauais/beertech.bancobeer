package com.beertech.banco.infrastructure.rest.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.TipoOperacao;
import com.beertech.banco.domain.exception.ContaException;
import com.beertech.banco.domain.service.BancoService;
import com.beertech.banco.infrastructure.rest.controller.dto.ContaDto;
import com.beertech.banco.infrastructure.rest.controller.dto.OperacaoDto;
import com.beertech.banco.infrastructure.rest.controller.dto.TransferenciaDto;


@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	BancoService bancoService; 

    @PostMapping(value = "/saque")
	public ResponseEntity saque(@Valid @RequestBody OperacaoDto operacaoDto, Principal principal) {
		//Operacao operacaoNaoRealizada = new Operacao(operacaoDto.getValor(), TipoOperacao.SAQUE);
		try {
			System.out.println(principal.getName());
			//Conta conta = bancoService.realizaOperacao(operacaoDto.getHash(), operacaoNaoRealizada);
			return ResponseEntity.ok().build();
		} catch (ContaException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PostMapping(value = "/deposito")
	@PreAuthorize("hasRole('COMUM')")
	public ResponseEntity deposito(@Valid @RequestBody OperacaoDto operacaoDto) {
		Operacao operacaoNaoRealizada = new Operacao(operacaoDto.getValor(), TipoOperacao.DEPOSITO);
		try {
			//Conta conta = bancoService.realizaOperacao(operacaoDto.getHash(), operacaoNaoRealizada);
			return ResponseEntity.ok().build();
		} catch (ContaException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

    @GetMapping(value = "/saldo/{hash}")
    public ResponseEntity getDataSaldo(@PathVariable String hash) throws JSONException {
    	try {
    		BigDecimal saldo = bancoService.saldo(hash);
    		return ResponseEntity.ok(saldo);    		
    	} catch(ContaException ex) {
    		ex.printStackTrace();
    		return ResponseEntity.notFound().build();
    	}
    }

    @PostMapping("/cadastro")
    public ResponseEntity criaContaCorrente(@Valid ContaDto contaDto, UriComponentsBuilder uriBuilder) {
    	try {
    		Conta conta = new Conta("1");
    		conta = bancoService.criarConta(conta);
    		URI uri = uriBuilder.path("/saldo/{hash}").buildAndExpand(conta.getHash()).toUri();
    		return ResponseEntity.created(uri).body(conta);
    	} catch (ContaException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }
    
    @PostMapping("/transferencia")
    public ResponseEntity transferencia(@Valid @RequestBody TransferenciaDto transferenciaDto) {
    	try {
    		//bancoService.transferencia(transferenciaDto.getContaOrigem(), transferenciaDto.getContaDestino(), transferenciaDto.getValor());
    		return ResponseEntity.ok().build();    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }
}
