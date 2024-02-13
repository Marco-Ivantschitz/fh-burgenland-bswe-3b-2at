package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.PersonRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;

/**
 * Unit tests for the {@link PersonServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @Mock
    private NoteService noteService;
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personRepository, noteService);
    }

    /**
     * There are no more interactions with repositories or services after each test method.
     */
    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(personRepository);
        Mockito.verifyNoMoreInteractions(noteService);
    }

    /**
     * Test case for retrieving a person by ID.
     */
    @Test
    void testGetById() {
        Mockito.when(personRepository.findById("1"))
                .thenReturn(Optional.of(new Person("1", "John", "Doe", Lists.newArrayList())));

        Optional<Person> person = personService.get("1");

        assertTrue(person.isPresent());
        assertEquals("John", person.get().getFirstName(), "firstName should be John");

        verify(personRepository, times(1)).findById("1");
    }

    /**
     * Test case for successfully retrieving a person by ID.
     */
    @Test
    void testGetByIdSuccess() {
        Mockito.when(personRepository.findById("1"))
                .thenReturn(Optional.of(new Person("1", "John", "Doe", Lists.newArrayList())));

        Optional<Person> person = personService.get("1");

        assertTrue(person.isPresent());
        assertEquals("John", person.get().getFirstName());
        assertEquals("Doe", person.get().getLastName());

        verify(personRepository, times(1)).findById("1");
    }

    /**
     * Test case for retrieving a person by ID when not found.
     */
    @Test
    void testGetByIdNotFound() {
        when(personRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Person> person = personService.get("2");

        assertFalse(person.isPresent());

        verify(personRepository, times(1)).findById("2");
    }

    /**
     * Test case for successfully creating a new person.
     */
    @Test
    void testCreateSuccess() {
        Person newPerson = new Person("3", "Alice", "Smith", Lists.newArrayList());

        when(personRepository.save(newPerson)).thenReturn(newPerson);

        Person createdPerson = personService.create(newPerson);

        assertEquals(newPerson, createdPerson);

        verify(personRepository, times(1)).save(newPerson);
    }

    /**
     * Test case for creating a new person with null input.
     */
    @Test
    void testCreateNullPerson() {
        assertThrows(IllegalArgumentException.class, () -> personService.create(null));

        verify(personRepository, never()).save(any());
    }
}

