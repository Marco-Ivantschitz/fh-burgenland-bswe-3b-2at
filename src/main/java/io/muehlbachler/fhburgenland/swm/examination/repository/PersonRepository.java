package io.muehlbachler.fhburgenland.swm.examination.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;

/**
 * Repository interface for managing persons.
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    /**
     * Finds persons by first name.
     *
     * @param firstName The first name to search for.
     * @return A list of persons with the provided first name.
     * @throws IllegalArgumentException if the provided first name is null.
     */
    List<Person> findByFirstName(String firstName);

    /**
     * Finds persons by last name.
     *
     * @param lastName The last name to search for.
     * @return A list of persons with the provided last name.
     * @throws IllegalArgumentException if the provided last name is null.
     */
    List<Person> findByLastName(String lastName);

    /**
     * Finds persons by first and last name.
     *
     * @param firstName The first name to search for.
     * @param lastName The last name to search for.
     * @return A list of persons with the provided first and last names.
     * @throws IllegalArgumentException if the provided first or last name is null.
     */
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
