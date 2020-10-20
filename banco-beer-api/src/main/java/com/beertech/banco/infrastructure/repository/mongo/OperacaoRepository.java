package com.beertech.banco.infrastructure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beertech.banco.infrastructure.repository.mongo.model.MongoOperacao;

public interface OperacaoRepository extends MongoRepository<MongoOperacao, String> {

}
