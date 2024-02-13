package io.muehlbachler.fhburgenland.swm.examination.service;

import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;

/**
 * Service interface for managing notes.
 */
public interface NoteService {

    /**
     * Retrieves a note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return Optional containing the retrieved note, if found; otherwise, an empty Optional.
     */
    Optional<Note> get(String id);

    /**
     * Creates a new note.
     *
     * @param note The note object to create.
     * @return The created note object.
     */
    Note create(Note note);

    /**
     * Queries notes by content.
     *
     * @param query The query string to search for in the notes' content.
     * @return If no matching notes are found, an empty list is returned.
     */
    List<Note> queryByContent(String query);
}
