package com.beertech.banco.infrastructure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beertech.banco.infrastructure.repository.mongo.model.MongoProfile;

public interface ProfileRepository extends MongoRepository<MongoProfile, String> {
	MongoProfile findByName(String name);

}
