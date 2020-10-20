package com.beertech.banco.infrastructure.repository.mongo.repository.impl;

import org.springframework.stereotype.Repository;

import com.beertech.banco.domain.Profile;
import com.beertech.banco.domain.repository.ProfileRepository;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoProfile;

@Repository
public class MongoProfileRepositoryImpl implements ProfileRepository {

	com.beertech.banco.infrastructure.repository.mongo.ProfileRepository profileRepository;
	
	public MongoProfileRepositoryImpl(com.beertech.banco.infrastructure.repository.mongo.ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	@Override
	public Profile findByName(String name) {
		MongoProfile findByName = profileRepository.findByName(name);
		if(findByName == null)
			return null;
		return new MongoProfile().toDomain(findByName);
	}

	@Override
	public Profile save(Profile profile) {
		return new MongoProfile().toDomain((profileRepository.save(new MongoProfile().fromDomain(profile))));
	}

}
