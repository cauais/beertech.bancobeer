package com.beertech.banco.infrastructure.repository.mongo.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.beertech.banco.domain.Conta;

@Document("contas")
public class MongoConta  implements UserDetails {

	private static final long serialVersionUID = -7566034954572301636L;

	@Id
	private String id;
	private String hash;	
	private BigDecimal saldo;
	private String nome;
	private String email;
	private String cnpj;
	private String senha;
	@DBRef
	private List<MongoProfile> profiles;
	
	public MongoConta() {
	}
	
	public MongoConta(String id, String hash, BigDecimal saldo, String nome, String email, String cnpj,
					  String senha, List<MongoProfile> perfil) {
		this.id = id;
		this.hash = hash;
		this.saldo = saldo;
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.senha = senha;
		this.profiles = perfil;
	}
	
	public MongoConta(String hash, BigDecimal saldo, String nome, String email, String cnpj,
					  String senha, List<MongoProfile> perfil) {
		this.hash = hash;
		this.saldo = saldo;
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.senha = senha;
		this.profiles = perfil;
	}
		
	public String getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getSenha() {
		return senha;
	}

	public List<MongoProfile> getProfiles() {
		return profiles;
	}

	public MongoConta fromDomain(Conta conta) {
		if(conta.getId() == null)
			return new MongoConta(conta.getHash()
					, conta.getSaldo()
					, conta.getNome()
					, conta.getEmail()
					, conta.getCnpj()
					, conta.getSenha()
					, conta.getProfiles().stream().map(new MongoProfile()::fromDomain).collect(Collectors.toList()));
		else 
			return new MongoConta(conta.getId()
					,conta.getHash()
					, conta.getSaldo()
					, conta.getNome()
					, conta.getEmail()
					, conta.getCnpj()
					, conta.getSenha()
					, conta.getProfiles().stream().map(new MongoProfile()::fromDomain).collect(Collectors.toList()));
	}
	
	public Conta toDomain(MongoConta mongoConta) {
		return new Conta( 
				mongoConta.getId()
				, mongoConta.getHash()
				, mongoConta.getSaldo()
				, mongoConta.getNome()
				, mongoConta.getEmail()
				, mongoConta.getCnpj()
				, mongoConta.getSenha()
				, mongoConta.getProfiles().stream().map(new MongoProfile()::toDomain).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
