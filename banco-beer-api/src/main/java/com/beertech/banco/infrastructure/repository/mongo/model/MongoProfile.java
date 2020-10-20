package com.beertech.banco.infrastructure.repository.mongo.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.beertech.banco.domain.Profile;

@Document("profiles")
public class MongoProfile implements GrantedAuthority  {
	
	private static final long serialVersionUID = 5170197267229300095L;

	@Id
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public MongoProfile() {
	}
	
	public MongoProfile(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public MongoProfile fromDomain(Profile profile) {
		return new MongoProfile(profile.getId(), profile.getName());
	}
	
	public Profile toDomain(MongoProfile profile) {
		return new Profile(profile.getId(), profile.getName());
	}

	@Override
	public String getAuthority() {
		return this.name;
	}


}
