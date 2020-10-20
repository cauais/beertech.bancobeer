package com.beertech.banco.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.beertech.banco.domain.Conta;
import com.beertech.banco.domain.Profile;
import com.beertech.banco.domain.repository.ContaRepository;
import com.beertech.banco.domain.repository.ProfileRepository;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;

@SpringBootApplication
@EntityScan(basePackages = "com.beertech.banco")
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProfileRepository roleRepository, ContaRepository contaRepository) {

		return args -> {

			Profile adminProfile = roleRepository.findByName("ADMIN");
			if (adminProfile == null) {
				Profile newAdminRole = new Profile("ADMIN");
				adminProfile = roleRepository.save(newAdminRole);
			}
			
			Optional<Conta> admin = contaRepository.findByEmail("admin@email.com");
			if(!admin.isPresent()) {
				Conta newAdmin = new Conta();
				newAdmin.setCnpj("CNPJ");
				newAdmin.setEmail("admin@email.com");
				newAdmin.setNome("Admin");
				newAdmin.setSenha(new BCryptPasswordEncoder().encode("grupocolorado"));
				List<Profile> profiles = new ArrayList<>();
				profiles.add(adminProfile);
				newAdmin.setProfiles(profiles);
				contaRepository.save(newAdmin);				
			}
		};

	}

}
