package com.beertech.banco.domain.service.impl;

import com.beertech.banco.domain.Profile;
import com.beertech.banco.domain.repository.ProfileRepository;
import com.beertech.banco.domain.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

	ProfileRepository repository;
	
	public ProfileServiceImpl(ProfileRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Profile getByName(String name) {
		return repository.findByName(name);
	}

}
