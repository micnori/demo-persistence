package it.smartcommunitylab.test.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.smartcommunitylab.test.model.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String>,
	PersonaRepositoryCustom {
	
	List<Persona> findByNome(String nome);
	
}
