package com.example.jsfdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.jsfdemo.model.Person;
import com.example.jsfdemo.model.PersonFilter;

@Named
@SessionScoped
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Person person = new Person();
	
	private PersonFilter personFilter = new PersonFilter();
	
	private List<Person> personList = new ArrayList<>();

	private List<Person> filteredList;
	
	public void add() {
		if (validate()) {
			person.setCreateDate(new Date());
			
			personList.add(person);
			person = new Person();
		}
	}
	
	public void filter() {
		filteredList.clear();
		
		for (Person p : personList) {
			if (personFilter.getFirstName() != null && !personFilter.getFirstName().isEmpty()) {
				if (!p.getFirstName().equals(personFilter.getFirstName())) {
					continue;
				}
			}
			
			if (personFilter.getLastName() != null && !personFilter.getLastName().isEmpty()) {
				if (!p.getLastName().equals(personFilter.getLastName())) {
					continue;
				}
			}
			
			if (personFilter.getPesel() != null && !personFilter.getPesel().isEmpty()) {
				if (!p.getPesel().equals(personFilter.getPesel())) {
					continue;
				}
			}
			
			if (personFilter.getBirthdateFrom() != null) {
				if (!p.getBirthDate().after(personFilter.getBirthdateFrom())) {
					continue;
				}
			}
			
			if (personFilter.getBirthdateTo() != null) {
				if (!p.getBirthDate().before(personFilter.getBirthdateTo())) {
					continue;
				}
			}
			
			if (personFilter.getWeightFrom() != null) {
				if (!(p.getWeight() > personFilter.getWeightFrom())) {
					continue;
				}
			}
			
			if (personFilter.getWeightTo() != null) {
				if (!(p.getWeight() < personFilter.getWeightTo())) {
					continue;
				}
			}
			
			if (personFilter.getHeightFrom() != null) {
				if (!(p.getHeight() > personFilter.getHeightFrom())) {
					continue;
				}
			}
			
			if (personFilter.getHeightTo() != null) {
				if (!(p.getHeight() < personFilter.getHeightTo())) {
					continue;
				}
			}
			
			if (personFilter.getCreatedFrom() != null) {
				if (!p.getCreateDate().after(personFilter.getCreatedFrom())) {
					continue;
				}
			}
			
			if (personFilter.getCreatedTo() != null) {
				if (!p.getCreateDate().before(personFilter.getCreatedTo())) {
					continue;
				}
			}
			
			filteredList.add(p);
		}
	}
	
	public String sortByCreateDate() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getCreateDate().compareTo(o2.getCreateDate());
			}
		});
		
		return null;
	}
	
	public String sortByFirstName() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
			}
		});
		
		return null;
	}
	
	public String sortByLastName() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getLastName().compareToIgnoreCase(o2.getLastName());
			}
		});
		
		return null;
	}
	
	public String sortByPesel() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getPesel().compareTo(o2.getPesel());
			}
		});
		
		return null;
	}
	
	public String sortByWeight() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				if (o1.getWeight() == null || o2.getWeight() == null) {
					return 0;
				}
				
				return o1.getWeight().compareTo(o2.getWeight());
			}
		});
		
		return null;
	}
	
	public String sortByHeight() {
		Collections.sort(filteredList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				if (o1.getHeight() == null || o2.getHeight() == null) {
					return 0;
				}
				
				return o1.getHeight().compareTo(o2.getHeight());
			}
		});
		
		return null;
	}
	
	private boolean validate() {
		if (!validatePeselFormat()) {
			String msg = ResourceBundle.getBundle("com.example.jsfdemo.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("peselValidator");
			FacesContext.getCurrentInstance().addMessage("personForm:pesel", new FacesMessage(msg));
			
			return false;
		}	
		
		if (!validatePeselUnique()) {
			String msg = ResourceBundle.getBundle("com.example.jsfdemo.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("peselUniqueValidator");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			
			return false;
		}
		
		return true;
	}
	
	private boolean validatePeselFormat() {
		if (person.getPesel().length() != 11) {
			return false;
		}
		
		char[] chars = person.getPesel().toCharArray();
		int[] ints = new int[chars.length];
		
		for (int i = 0; i < chars.length; i++) {
			ints[i] = Integer.valueOf(chars[i]);
		}
		
		int checksum = ints[0] + 3*ints[1] + 7*ints[2] + 9*ints[3] + ints[4] + 3*ints[5] + 7*ints[6]
				+ 9*ints[7] + ints[8] + 3*ints[9] + ints[10];
		
		return checksum % 10 == 0;
	}

	private boolean validatePeselUnique() {
		for (Person p : personList) {
			if (p.getPesel().equals(person.getPesel())) {
				return false;
			}
		}
		
		return true;
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

	public List<Person> getFilteredList() {
		if (filteredList == null) {
			filteredList = new ArrayList<>(personList);
		}
		
		return filteredList;
	}

	public void setFilteredList(List<Person> filteredList) {
		this.filteredList = filteredList;
	}

	public PersonFilter getPersonFilter() {
		return personFilter;
	}

	public void setPersonFilter(PersonFilter personFilter) {
		this.personFilter = personFilter;
	}
	
	
}
