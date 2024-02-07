package io.muehlbachler.fhburgenland.swm.examination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;

/**
 * Controller class for managing persons.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * Retrieves a list of all persons.
     *
     * @return A list of all persons.
     */
    @GetMapping("/")
    public List<Person> list() {
        return personService.getAll();
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return ResponseEntity containing the retrieved person, if found; otherwise, returns ResponseEntity with no content.
     * @throws IllegalArgumentException if the provided ID is null or empty.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return ResponseEntity.of(personService.get(id));
    }

    /**
     * Creates a new person.
     *
     * @param person The person object to create.
     * @return The created person object.
     */
    @PostMapping("/")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    /**
     * Queries persons by first and last name.
     *
     * @param firstName The first name to search for.
     * @param lastName The last name to search for.
     * @return A list of persons matching the provided first and last names.
     * @throws IllegalArgumentException if the provided first name or last name is null.
     */
    @GetMapping("/query")
    public List<Person> query(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name cannot be null");
        }
        return personService.findByName(firstName, lastName);
    }

    /**
     * Creates a note for a person.
     *
     * @param id The ID of the person to create the note for.
     * @param note The note object to create.
     * @return ResponseEntity containing the created note, if successful; otherwise, returns ResponseEntity with no content.
     * @throws IllegalArgumentException if the provided ID is null or empty.
     */
    @PostMapping("/{id}/note")
    public ResponseEntity<Note> createNote(@PathVariable String id, @RequestBody Note note) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return ResponseEntity.of(personService.createNote(id, note));
    }
}
