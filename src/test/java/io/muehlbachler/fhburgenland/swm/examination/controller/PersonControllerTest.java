package io.muehlbachler.fhburgenland.swm.examination.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @Test
    void testGetById() {
        ResponseEntity<Person> person = personController.get("81150016-8501-4b97-9168-01113e21d8a5");

        assertEquals(HttpStatus.OK, person.getStatusCode(), "person should be found");
        assertEquals("John", person.getBody().getFirstName(), "firstName should be John");
    }

    @Test
    void testCreatePerson() {
        Person newPerson = new Person();
        newPerson.setFirstName("Jane");
        newPerson.setLastName("Doe");
        Person createdPerson = personController.create(newPerson);
        assertEquals(newPerson.getFirstName(), createdPerson.getFirstName());
        assertEquals(newPerson.getLastName(), createdPerson.getLastName());
    }

    @Test
    void testQueryPersons() {
        List<Person> persons = personController.query("John", "Doe");
        assertTrue(persons.isEmpty());
    }
}
