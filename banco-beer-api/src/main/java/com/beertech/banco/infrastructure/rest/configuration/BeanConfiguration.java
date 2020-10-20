package com.beertech.banco.infrastructure.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beertech.banco.domain.service.BancoService;
import com.beertech.banco.domain.service.OperacaoService;
import com.beertech.banco.domain.service.ProfileService;
import com.beertech.banco.domain.service.impl.BancoServiceImpl;
import com.beertech.banco.domain.service.impl.OperacaoServiceImpl;
import com.beertech.banco.domain.service.impl.ProfileServiceImpl;
import com.beertech.banco.infrastructure.repository.mongo.repository.impl.MongoContaRepositoryImpl;
import com.beertech.banco.infrastructure.repository.mongo.repository.impl.MongoOperacaoRepositoryImpl;
import com.beertech.banco.infrastructure.repository.mongo.repository.impl.MongoProfileRepositoryImpl;

@Configuration
public class BeanConfiguration {

	@Autowired
	private MongoProfileRepositoryImpl mongoProfileRepositoryImpl;
	@Autowired
	private MongoContaRepositoryImpl mongoContaRepositoryImpl;
	@Autowired
	private MongoOperacaoRepositoryImpl mongoOperacaoRepositoryImpl;
	
	@Bean
    public BancoService bancoService() {
        return new BancoServiceImpl(mongoContaRepositoryImpl, mongoOperacaoRepositoryImpl);
    }
	
	@Bean
    public ProfileService profileService() {
        return new ProfileServiceImpl(mongoProfileRepositoryImpl);
    }
	
	@Bean
	public OperacaoService operacaoService() {
		return new OperacaoServiceImpl(mongoOperacaoRepositoryImpl);
	}
}
