package com.beertech.banco.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beertech.banco.controller.dto.OperacaoDto;
import com.beertech.banco.entity.Operacao;
import com.beertech.banco.service.BancoService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	BancoService bancoService; 

    @PostMapping(value = "/operacao")
    public ResponseEntity<Operacao> salvaOperacao(@RequestBody OperacaoDto operacaoDto) {
    	Operacao operacao = bancoService.salvaOperacao(new Operacao(operacaoDto));
    	return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/saldo", produces ="application/json")
    public ResponseEntity<String> getDataSaldo() {
    	 String saldo = bancoService.getSaldo().toString();
    	return ResponseEntity.ok(saldo);
    }
}