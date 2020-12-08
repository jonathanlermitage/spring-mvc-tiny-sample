package demo.springmvctinysample.service.impl;

import demo.springmvctinysample.model.Person;
import demo.springmvctinysample.service.PersonService;
import com.google.common.collect.ImmutableMap;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    /**
     * Une liste en dur de personnes. Idéalement on aurait une base de données avec plein d'individus, mais
     * là on la code en dur pour faire simple.
     */
    private static final Map<String, Person> peopleDatabase = ImmutableMap.of(
        "Sariyah", new Person("Sariyah", 123),
        "Bryan", new Person("Bryan", -2),
        "Dexter", new Person("Dexter", 1_000_000)
    );

    @Override
    public Optional<Person> findByName(String name) {
        if (peopleDatabase.containsKey(name)) {
            return Optional.of(peopleDatabase.get(name));
        }
        return Optional.empty();
    }

    @Override
    public Collection<Person> findAll() {
        return peopleDatabase.values();
    }
}
