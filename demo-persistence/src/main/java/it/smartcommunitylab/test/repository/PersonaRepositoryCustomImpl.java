package it.smartcommunitylab.test.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.smartcommunitylab.test.model.Persona;

public class PersonaRepositoryCustomImpl implements PersonaRepositoryCustom {
	@Autowired
	private MongoTemplate template;
	
	@Override
	public List<Persona> searchPersona(String keyword) {
		Criteria criteria = new Criteria();
		if(keyword != null) {
			criteria = criteria.orOperator(
					Criteria.where("nome").regex(".*" + keyword + ".*", "i"),
					Criteria.where("cognome").regex(".*" + keyword + ".*", "i"),
					Criteria.where("tags").regex(".*" + keyword + ".*", "i")
			);
		}
		Query query = new Query(criteria); 
		return template.find(query, Persona.class);
	}

}
