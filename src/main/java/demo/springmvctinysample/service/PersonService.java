package demo.springmvctinysample.service;

import demo.springmvctinysample.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findByName(String name);

    Collection<Person> findAll();
}
