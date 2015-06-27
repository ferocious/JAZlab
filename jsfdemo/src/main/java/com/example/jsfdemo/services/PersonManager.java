package com.example.jsfdemo.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jsfdemo.model.Person;

@Stateless
public class PersonManager {

	@PersistenceContext
	private EntityManager em;
	
	public void addPerson(Person person) {
		person.setId(null);
		em.persist(person);
	}

	public void deletePerson(Person person) {
		person = em.find(Person.class, person.getId());
		em.remove(person);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		return em.createNamedQuery("person.all").getResultList();
	}
}
