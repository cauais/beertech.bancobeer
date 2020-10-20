package com.beertech.banco.infrastructure.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beertech.banco.infrastructure.repository.mongo.model.MongoConta;
import com.beertech.banco.infrastructure.repository.mongo.model.MongoOperacao;

public interface OperacaoRepository extends MongoRepository<MongoOperacao, String> {
	List<MongoOperacao> findByConta(MongoConta MongoOperacao);

}
