package com.beertech.banco.infrastructure.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beertech.banco.domain.exception.ContaException;
import com.beertech.banco.domain.service.BancoService;
import com.beertech.banco.infrastructure.rest.controller.form.OperacaoForm;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@RequestMapping("/banco")
public class OperacaoController {

	@Autowired
	BancoService bancoService; 

	@ApiIgnore
    @PostMapping(value = "/operacao")
    public ResponseEntity salvaOperacao(@Valid @RequestBody OperacaoForm operacaoDto) {
    	//Operacao operacaoNaoRealizada = new Operacao(operacaoDto.getValor(), operacaoDto.getTipo());
    	try {
    		//Conta conta = bancoService.realizaOperacao(operacaoDto.getHash(), operacaoNaoRealizada);
    		return ResponseEntity.ok().build();    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }


    
    
    
    
}
