package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.PersonRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @Mock
    private NoteService noteService;
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personRepository, noteService);
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(personRepository);
        Mockito.verifyNoMoreInteractions(noteService);
    }

    @Test
    void testGetById() {
        Mockito.when(personRepository.findById("1"))
                .thenReturn(Optional.of(new Person("1", "John", "Doe", Lists.newArrayList())));

        Optional<Person> person = personService.get("1");

        assertTrue(person.isPresent());
        assertEquals("John", person.get().getFirstName(), "firstName should be John");

        verify(personRepository, times(1)).findById("1");
    }

    @Test
    void testGetByIdSuccess() {
        // Mocking: when findById is called with "1", return a Person object
        Mockito.when(personRepository.findById("1"))
                .thenReturn(Optional.of(new Person("1", "John", "Doe", Lists.newArrayList())));

        // Testing: Call the get method with "1"
        Optional<Person> person = personService.get("1");

        // Assertion: Ensure the returned person is present and has correct details
        assertTrue(person.isPresent());
        assertEquals("John", person.get().getFirstName());
        assertEquals("Doe", person.get().getLastName());

        // Verification: Verify that findById was called once with "1"
        verify(personRepository, times(1)).findById("1");
    }

    @Test
    void testGetByIdNotFound() {
        // Mocking: when findById is called with "2", return an empty Optional
        when(personRepository.findById("2")).thenReturn(Optional.empty());

        // Testing: Call the get method with "2"
        Optional<Person> person = personService.get("2");

        // Assertion: Ensure the returned person is not present
        assertFalse(person.isPresent());

        // Verification: Verify that findById was called once with "2"
        verify(personRepository, times(1)).findById("2");
    }

    @Test
    void testCreateSuccess() {
        // Creating a new person
        Person newPerson = new Person("3", "Alice", "Smith", Lists.newArrayList());

        // Mocking: when save is called with newPerson, return newPerson
        when(personRepository.save(newPerson)).thenReturn(newPerson);

        // Testing: Call the create method with newPerson
        Person createdPerson = personService.create(newPerson);

        // Assertion: Ensure the returned person matches the newPerson
        assertEquals(newPerson, createdPerson);

        // Verification: Verify that save was called once with newPerson
        verify(personRepository, times(1)).save(newPerson);
    }

    @Test
    void testCreateNullPerson() {
        // Testing: Call the create method with null person
        assertThrows(IllegalArgumentException.class, () -> personService.create(null));

        // Verification: Ensure that save was not called
        verify(personRepository, never()).save(any());
    }
}

