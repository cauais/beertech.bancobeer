package com.beertech.banco.infrastructure.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beertech.banco.domain.service.ContaService;
import com.beertech.banco.domain.service.ProfileService;
import com.beertech.banco.domain.service.impl.ContaServiceImpl;
import com.beertech.banco.domain.service.impl.ProfileServiceImpl;
import com.beertech.banco.infrastructure.repository.mysql.repository.impl.MySqlContaRepositoryImpl;
import com.beertech.banco.infrastructure.repository.mysql.repository.impl.MySqlProfileRepositoryImpl;

@Configuration
public class BeanConfiguration {

	@Autowired
	private MySqlContaRepositoryImpl mySqlContaRepositoryImpl;
	
	@Autowired
	private MySqlProfileRepositoryImpl mySqlProfileRepositoryImpl;
	
	@Bean
    public ContaService bancoService() {
        return new ContaServiceImpl(mySqlContaRepositoryImpl);
    }
	
	@Bean
    public ProfileService profileService() {
        return new ProfileServiceImpl(mySqlProfileRepositoryImpl);
    }
}
