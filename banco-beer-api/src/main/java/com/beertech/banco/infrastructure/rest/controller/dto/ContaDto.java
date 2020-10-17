package com.beertech.banco.infrastructure.rest.controller.dto;

import javax.validation.constraints.NotBlank;

public class ContaDto {
	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private String cnpj;
	@NotBlank
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
