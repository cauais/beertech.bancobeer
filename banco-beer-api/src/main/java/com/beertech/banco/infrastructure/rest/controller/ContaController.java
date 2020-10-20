package com.beertech.banco.infrastructure.rest.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Operacao;
import com.beertech.banco.domain.Profile;
import com.beertech.banco.domain.TipoOperacao;
import com.beertech.banco.domain.exception.ContaException;
import com.beertech.banco.domain.service.BancoService;
import com.beertech.banco.domain.service.OperacaoService;
import com.beertech.banco.domain.service.ProfileService;
import com.beertech.banco.infrastructure.rest.controller.dto.ContaDto;
import com.beertech.banco.infrastructure.rest.controller.dto.OperacaoDto;
import com.beertech.banco.infrastructure.rest.controller.dto.TransferenciaDto;
import com.beertech.banco.infrastructure.rest.controller.form.ContaForm;
import com.beertech.banco.infrastructure.rest.controller.form.OperacaoForm;


@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	BancoService bancoService; 
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	OperacaoService operacaoService;

    @PostMapping(value = "/saque")
	public ResponseEntity saque(@Valid @RequestBody OperacaoForm operacaoDto, Principal principal) {
		try {
			Conta contaPeloEmail = bancoService.contaPeloEmail(principal.getName());
			Operacao operacaoNaoRealizada = new Operacao(operacaoDto.getValor(), TipoOperacao.SAQUE);
			bancoService.realizaOperacao(contaPeloEmail.getHash(), operacaoNaoRealizada);
			return ResponseEntity.ok().build();
		} catch (ContaException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PostMapping(value = "/deposito")
	public ResponseEntity deposito(@Valid @RequestBody OperacaoForm operacaoDto, Principal principal) {
		try {
			Conta contaPeloEmail = bancoService.contaPeloEmail(principal.getName());
			Operacao operacaoNaoRealizada = new Operacao(operacaoDto.getValor(), TipoOperacao.DEPOSITO);
			bancoService.realizaOperacao(contaPeloEmail.getHash(), operacaoNaoRealizada);
			return ResponseEntity.ok().build();
		} catch (ContaException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

    @GetMapping(value = "/saldo/")
    public ResponseEntity getDataSaldo(Principal principal) throws JSONException {
    	try {
    		Conta contaPeloEmail = bancoService.contaPeloEmail(principal.getName());
    		BigDecimal saldo = bancoService.saldo(contaPeloEmail.getHash());
    		return ResponseEntity.ok(saldo);    		
    	} catch(ContaException ex) {
    		ex.printStackTrace();
    		return ResponseEntity.notFound().build();
    	}
    }

    @PostMapping("/cadastro")
    public ResponseEntity criaContaCorrente(@Valid ContaForm contaDto, UriComponentsBuilder uriBuilder) {
    	try {
    		
    		Profile profile = profileService.getByName("ROLE_USER");
    		if(profile == null)
    			return ResponseEntity.badRequest().body("Profile invalido");
    		Conta conta = new Conta(contaDto, profile);
    		conta = bancoService.criarConta(conta);
    		URI uri = uriBuilder.path("/saldo/{hash}").buildAndExpand(conta.getHash()).toUri();
    		return ResponseEntity.created(uri).body(conta);
    	} catch (ContaException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }
    
    @PostMapping("/transferencia")
    public ResponseEntity transferencia(@Valid @RequestBody TransferenciaDto transferenciaDto, Principal principal) {
    	try {
    		Conta contaPeloEmail = bancoService.contaPeloEmail(principal.getName());
    		bancoService.transferencia(contaPeloEmail.getHash(), transferenciaDto.getContaDestino(), transferenciaDto.getValor());
    		return ResponseEntity.ok().build();    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }
    
    @GetMapping
    public ResponseEntity<?> listaContas() {
    	List<ContaDto> listaTodasAsContas = bancoService.listaTodasAsContas().stream().map(ContaDto::new).collect(Collectors.toList());    	
    	return ResponseEntity.ok(listaTodasAsContas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> contaPeloId(@PathVariable String id) {
    	try {
    		Conta contaPeloId = bancoService.contaPeloId(id);
    		return ResponseEntity.ok(new ContaDto(contaPeloId));    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @GetMapping("/hash/{hash}")
    public ResponseEntity<?> contaPeloHash(@PathVariable String hash) {
    	try {
    		Conta contaPeloHash = bancoService.contaPeloHash(hash);
    		return ResponseEntity.ok(new ContaDto(contaPeloHash));    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @GetMapping("/extrato")
    public ResponseEntity<?> extrato(Principal principal) {
    	try {
    		Conta contaPeloId = bancoService.contaPeloEmail(principal.getName());
    		List<Operacao> extratoPorConta = operacaoService.extratoPorConta(contaPeloId);
    		return ResponseEntity.ok(extratoPorConta.stream().map(OperacaoDto::new).collect(Collectors.toList()));    		
    	} catch (ContaException | IllegalArgumentException ex) {
    		return ResponseEntity.notFound().build();
    	}
    }
}
