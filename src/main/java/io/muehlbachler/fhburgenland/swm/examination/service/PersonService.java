package io.muehlbachler.fhburgenland.swm.examination.service;

import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;

/**
 * Service interface for managing persons.
 */
public interface PersonService {

    /**
     * Retrieves a list of all persons.
     *
     * @return A list of all persons.
     */
    List<Person> getAll();

    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return Optional containing the retrieved person, if found; otherwise, an empty Optional.
     */
    Optional<Person> get(String id);

    /**
     * Creates a new person.
     *
     * @param person The person object to create.
     * @return The created person object.
     */

    Person create(Person person);

    /**
     * Finds persons by first and last name.
     *
     * @param firstName The first name to search for.
     * @param lastName  The last name to search for.
     * @return If any of the names is empty, only the other name will be used for searching.
     */

    List<Person> findByName(String firstName, String lastName);

    /**
     * Creates a note for a person.
     *
     * @param personId The ID of the person to create the note for.
     * @param note The note object to create.
     * @return Optional containing the created note, if successful; otherwise, an empty Optional.
     */
    Optional<Note> createNote(String personId, Note note);
}
