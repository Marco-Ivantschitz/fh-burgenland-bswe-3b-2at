package io.muehlbachler.fhburgenland.swm.examination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;

/**
 * Controller class for managing notes.
 */
@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * Retrieves a note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return The retrieved note, if found; otherwise, returns ResponseEntity with no content.
     * @throws IllegalArgumentException if the provided ID is null or empty.
     */
    @GetMapping("/note/{id}")
    public ResponseEntity<Note> get(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return ResponseEntity.of(noteService.get(id));
    }

    /**
     * Queries notes by content.
     *
     * @param query The query string to search for in the notes' content.
     * @return If no matching notes are found, an empty list is returned.
     * @throws IllegalArgumentException if the provided query is null.
     */
    @GetMapping("/note/query")
    public List<Note> query(@RequestParam("query") String query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        return noteService.queryByContent(query);
    }
}
