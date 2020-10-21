package com.beertech.banco.infrastructure;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.beertech.banco.domain.model.Conta;
import com.beertech.banco.domain.model.EPerfil;
import com.beertech.banco.domain.model.Profile;
import com.beertech.banco.domain.repository.ContaRepository;
import com.beertech.banco.domain.service.ProfileService;

@SpringBootApplication
@EntityScan(basePackages = "com.beertech.banco")
public class BeercoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeercoinsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(ProfileService profileService, ContaRepository contaRepository) {

		return args -> {

			Optional<Profile> adminProfile = profileService.findByName(EPerfil.ADMIN.name());
			if (!adminProfile.isPresent()) {
				Profile newAdminRole = new Profile(EPerfil.ADMIN.name());
				adminProfile = Optional.ofNullable(profileService.save(newAdminRole));
			}
			
			Optional<Profile> userProfile = profileService.findByName(EPerfil.USER.name());
			if (!userProfile.isPresent()) {
				Profile newAdminRole = new Profile(EPerfil.USER.name());
				Optional.ofNullable(profileService.save(newAdminRole));
			}
			
			Optional<Conta> admin = contaRepository.findByEmail("admin@email.com");
			if(!admin.isPresent()) {
				Conta newAdmin = new Conta();
				newAdmin.setCnpj("12.345.6789/0001-90");
				newAdmin.setEmail("admin@email.com");
				newAdmin.setNome("Admin");
				newAdmin.setSenha(new BCryptPasswordEncoder().encode("grupocolorado"));
				newAdmin.addProfile(adminProfile.get());
				contaRepository.save(newAdmin);				
			}
		};
	}
}