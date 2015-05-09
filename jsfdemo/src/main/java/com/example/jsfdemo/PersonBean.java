package com.example.jsfdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.jsfdemo.model.Person;

@Named
@SessionScoped
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Person person = new Person();
	
	private List<Person> personList = new ArrayList<>();

	public void add() {
		personList.add(person);
		person = new Person();
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
}
