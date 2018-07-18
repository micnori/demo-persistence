package it.smartcommunitylab.test.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.smartcommunitylab.test.model.Persona;
import it.smartcommunitylab.test.repository.PersonaRepository;

@Controller
public class PersonaController {
	@Autowired
	private PersonaRepository repository;
	
	@GetMapping("/persone")
	public @ResponseBody List<Persona> getPersone() {
		return repository.findAll();
	}
	
	@GetMapping("/persona/{id}")
	public @ResponseBody Persona getPersona(@PathVariable String id) {
		Optional<Persona> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	@PostMapping("/persona")
	public @ResponseBody Persona add(@RequestBody Persona persona) {
		persona.setId(UUID.randomUUID().toString());
		repository.save(persona);
		return persona;
	}
	
	@PutMapping("/persona/{id}")
	public ResponseEntity<Persona> update(@PathVariable String id,
	@RequestBody Persona persona) {
		if(repository.existsById(id)) {
			persona.setId(id);
			repository.save(persona);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		} else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND); 
		}
	}
	
	@DeleteMapping("/persona/{id}")
	public ResponseEntity<Persona> delete(@PathVariable String id) {
		if(repository.existsById(id)) {
			Optional<Persona> optional = repository.findById(id);
			Persona persona = optional.get();
			repository.deleteById(id);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		} else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/persone/search/nome")
	public @ResponseBody List<Persona> searchByNome(@RequestParam String nome) {
		return repository.findByNome(nome);
	}

	@GetMapping("/persone/search")
	public @ResponseBody List<Persona> search(@RequestParam String keyword) {
		return repository.searchPersona(keyword);
	}
	
	
}
