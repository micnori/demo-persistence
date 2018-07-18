package it.smartcommunitylab.test.repository;

import java.util.List;

import it.smartcommunitylab.test.model.Persona;

public interface PersonaRepositoryCustom {
	
	List<Persona> searchPersona(String keyword);
	
}
