package com.beertech.banco.infrastructure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;

public interface ContaRepository extends MongoRepository<MongoConta, Long> {

}
