package io.muehlbachler.fhburgenland.swm.examination.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;

/**
 * Repository interface for managing notes.
 */
public interface NoteRepository extends CrudRepository<Note, String> {

    /**
     * Finds notes by content.
     *
     * @param content The content to search for in the notes.
     * @return A list of notes containing the provided content.
     * @throws IllegalArgumentException if the provided content is null.
     */
    List<Note> findByContentContaining(String content);
}
