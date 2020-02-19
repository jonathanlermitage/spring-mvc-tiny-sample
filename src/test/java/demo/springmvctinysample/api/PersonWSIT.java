package demo.springmvctinysample.api;

import demo.springmvctinysample.AbstracBaseIT;
import demo.springmvctinysample.model.Person;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

public class PersonWSIT extends AbstracBaseIT {

    private static final String PERSON_API = "/api/v1/person"; // juste pour éviter de se répéter

    @Test
    public void should_find_one_person_by_name() {
        Response response = RestAssured
            .get(PERSON_API + "/Sariyah");

        response.then().statusCode(200);
        Person person = readValue(response, Person.class);
        Assertions.assertThat(person).isEqualTo(new Person("Sariyah", 123));
    }

    @ParameterizedTest
    @MethodSource("dataProviderPersons")
    public void should_find_every_person_by_name(String askedName, Person expectedPerson) {
        Response response = RestAssured
            .get(PERSON_API + "/" + askedName);

        response.then().statusCode(200);
        Person person = readValue(response, Person.class);
        Assertions.assertThat(person)
            .isEqualTo(expectedPerson);
    }

    public Object[][] dataProviderPersons() {
        return new Object[][]{
            {"Sariyah", new Person("Sariyah", 123)},
            {"Bryan", new Person("Bryan", -2)},
            {"Dexter", new Person("Dexter", 1_000_000)}
        };
    }

    @Test
    public void should_not_find_unknown_person_by_name() {
        Response response = RestAssured
            .get(PERSON_API + "/jeNeSaisPas");

        response.then().statusCode(404);
    }

    @Test
    public void should_find_all_persons() {
        Response response = RestAssured
            .get(PERSON_API + "/all");

        response.then().statusCode(200);
        Collection<Person> persons = Arrays.asList(readValue(response, Person[].class));
        Assertions.assertThat(persons)
            .containsExactlyInAnyOrder(
                new Person("Sariyah", 123),
                new Person("Bryan", -2),
                new Person("Dexter", 1_000_000)
            );
    }
}
