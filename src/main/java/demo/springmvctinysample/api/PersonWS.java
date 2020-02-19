package demo.springmvctinysample.api;

import demo.springmvctinysample.ex.PersonNotFoundException;
import demo.springmvctinysample.model.Person;
import demo.springmvctinysample.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/person")
@RequiredArgsConstructor
public class PersonWS {

    private final PersonService personService;

    @GetMapping("/{name}")
    public Person findByName(@PathVariable(name = "name") String name)
        throws PersonNotFoundException {
        return personService
            .findByName(name)
            .orElseThrow(PersonNotFoundException::new);
    }

    @GetMapping("/all")
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
