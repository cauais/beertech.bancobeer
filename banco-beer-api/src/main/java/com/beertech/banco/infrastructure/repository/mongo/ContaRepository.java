package com.beertech.banco.infrastructure.repository.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;

public interface ContaRepository extends MongoRepository<MongoConta, String> {

	Optional<MongoConta> findByHash(String hash);
	Optional<MongoConta> findByEmail(String email);
	Optional<MongoConta> findById(String id);
}
