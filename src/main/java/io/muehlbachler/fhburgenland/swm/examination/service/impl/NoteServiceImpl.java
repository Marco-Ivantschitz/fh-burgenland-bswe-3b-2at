package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.repository.NoteRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;

/**
 * Implementation of the NoteService interface.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Retrieves a note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return Optional containing the retrieved note, if found; otherwise, an empty Optional.
     * @throws IllegalArgumentException if the provided ID is null.
     */
    @Override
    public Optional<Note> get(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return noteRepository.findById(id);
    }

    /**
     * Creates a new note.
     *
     * @param note The note object to create.
     * @return The created note object.
     * @throws IllegalArgumentException if the provided note is null.
     */
    @Override
    public Note create(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        return noteRepository.save(note);
    }

    /**
     * Queries notes by content.
     *
     * @param query The query string to search for in the notes' content.
     * @return A list of notes matching the query. If no matching notes are found, an empty list is returned.
     * @throws IllegalArgumentException if the provided query is null.
     */
    @Override
    public List<Note> queryByContent(String query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        return noteRepository.findByContentContaining(query);
    }
}
