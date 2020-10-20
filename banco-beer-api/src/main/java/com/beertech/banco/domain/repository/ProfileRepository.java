package com.beertech.banco.domain.repository;

import com.beertech.banco.domain.Profile;

public interface ProfileRepository {
	Profile findByName(String name);
	Profile save(Profile profile);
}
