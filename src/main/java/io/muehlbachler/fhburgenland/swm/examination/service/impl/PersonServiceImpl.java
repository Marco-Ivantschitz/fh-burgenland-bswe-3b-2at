package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.PersonRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NoteService noteService;

    /**
     * Retrieves a list of all persons.
     *
     * @return A list of all persons.
     */
    @Override
    public List<Person> getAll() {
        return Lists.newArrayList(personRepository.findAll());
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return Optional containing the retrieved person, if found; otherwise, an empty Optional.
     * @throws IllegalArgumentException if the provided ID is null.
     */
    @Override
    public Optional<Person> get(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return personRepository.findById(id);
    }

    /**
     * Creates a new person.
     *
     * @param person The person object to create.
     * @return The created person object.
     * @throws IllegalArgumentException if the provided person is null.
     */
    @Override
    public Person create(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        return personRepository.save(person);
    }

    /**
     * Finds persons by first and last name.
     *
     * @param firstName The first name to search for.
     * @param lastName The last name to search for.
     * @return A list of persons matching the provided first and last names. If any of the names (first or last) is empty, only the other name will be used for searching.
     */
    @Override
    public List<Person> findByName(String firstName, String lastName) {
        if (firstName.isEmpty() && !lastName.isEmpty()) {
            return personRepository.findByFirstName(lastName);
        } else if (lastName.isEmpty() && !firstName.isEmpty()) {
            return personRepository.findByLastName(firstName);
        }
        return Lists.newArrayList();
    }

    /**
     * Creates a note for a person.
     *
     * @param personId The ID of the person to create the note for.
     * @param note The note object to create.
     * @return Optional containing the created note, if successful; otherwise, an empty Optional.
     * @throws IllegalArgumentException if the provided person ID or note is null.
     */
    @Override
    public Optional<Note> createNote(String personId, Note note) {
        if (personId == null || note == null) {
            throw new IllegalArgumentException("Person ID and note cannot be null");
        }
        return get(personId).map((Person person) -> {
            note.setPerson(person);
            return noteService.create(note);
        });
    }
}
