package io.muehlbachler.fhburgenland.swm.examination.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for the {@link PersonController} class.
 */
@SpringBootTest
public class PersonControllerTest {
    @Autowired
    private PersonController personController;

    /**
     * Tests the getbyID method using
     * a known person ID to ensure it retrieves the person successfully.
     */
    @Test
    void testGetByIdNotFound() {
        ResponseEntity<Person> person =
		personController.get("81150016-8501-4b97-9168-01113e21d8a2");

        assertEquals(HttpStatus.NOT_FOUND, person.getStatusCode(),
		"person should not be found");
    }

    /**
     * Tests the retrieval of a person by ID.
     */
    @Test
    void testGetById() {
        ResponseEntity<Person> person =
                personController.get("81150016-8501-4b97-9168-01113e21d8a5");

        assertEquals(HttpStatus.OK, person.getStatusCode(), "person should be found");
        assertEquals("John", person.getBody().getFirstName(), "firstName should be John");
    }

    /**
     * Tests the creation of a new person.
     */
    @Test
    void testCreatePerson() {
        Person newPerson = new Person();
        newPerson.setFirstName("Jane");
        newPerson.setLastName("Doe");
        Person createdPerson = personController.create(newPerson);
        assertEquals(newPerson.getFirstName(), createdPerson.getFirstName());
        assertEquals(newPerson.getLastName(), createdPerson.getLastName());
    }

    /**
     * Tests querying persons by first and last name.
     */
    @Test
    void testQueryPersons() {
        List<Person> persons = personController.query("John", "Doe");
        assertFalse(persons.isEmpty());
    }

    /**
     * Tests listing all persons.
     */
    @Test
    void testListAllPersons() {
        List<Person> persons = personController.list();
        assertNotNull(persons, "List of persons should not be null");
        assertFalse(persons.isEmpty(), "List of persons should not be empty");
    }
}
