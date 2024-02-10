package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.repository.NoteRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.impl.NoteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @BeforeEach
    void setUp() {
        reset(noteRepository);
    }

    @Test
    void testGetNoteByIdSuccess() {
        // Mocking: when findById is called with "1", return a Note object
        Note expectedNote = new Note();
        expectedNote.setContent("Test content");

        // Mocking: Mocking the findById method of the noteRepository
        when(noteRepository.findById("1")).thenReturn(Optional.of(expectedNote));

        // Testing: Call the get method with "1"
        Optional<Note> actualNote = noteService.get("1");

        // Assertion: Ensure the returned note matches the expected content
        assertTrue(actualNote.isPresent());
        assertEquals(expectedNote.getContent(), actualNote.get().getContent());

        // Verification: Verify that findById was called once with "1"
        verify(noteRepository, times(1)).findById("1");
    }


    @Test
    void testGetNoteByIdNotFound() {
        // Mocking: when findById is called with an ID that doesn't exist, return an empty Optional
        when(noteRepository.findById("2")).thenReturn(Optional.empty());

        // Testing: Call the get method with "2"
        Optional<Note> actualNote = noteService.get("2");

        // Assertion: Ensure the returned note is not present
        assertFalse(actualNote.isPresent());

        // Verification: Verify that findById was called once with "2"
        verify(noteRepository, times(1)).findById("2");
    }

    @Test
    void testCreateNoteSuccess() {
        // Creating a new note
        Note newNote = new Note();
        newNote.setContent("New test content");

        // Mocking: when save is called with newNote, return newNote
        when(noteRepository.save(newNote)).thenReturn(newNote);

        // Testing: Call the create method with newNote
        Note createdNote = noteService.create(newNote);

        // Assertion: Ensure the returned note matches the newNote
        assertEquals(newNote, createdNote);

        // Verification: Verify that save was called once with newNote
        verify(noteRepository, times(1)).save(newNote);
    }

    @Test
    void testCreateNoteNull() {
        // Testing: Call the create method with null note
        assertThrows(IllegalArgumentException.class, () -> noteService.create(null));

        // Verification: Ensure that save was not called
        verify(noteRepository, never()).save(any());
    }

    @Test
    void testQueryByContentSuccess() {
        // Mocking: when findByContentContaining is called with a query, return a list of notes
        String query = "test";
        List<Note> expectedNotes = List.of(new Note(), new Note());
        when(noteRepository.findByContentContaining(query)).thenReturn(expectedNotes);

        // Testing: Call the queryByContent method with the query
        List<Note> actualNotes = noteService.queryByContent(query);

        // Assertion: Ensure the returned list of notes matches the expected list
        assertEquals(expectedNotes, actualNotes);

        // Verification: Verify that findByContentContaining was called once with the query
        verify(noteRepository, times(1)).findByContentContaining(query);
    }

    @Test
    void testQueryByContentNull() {
        // Testing: Call the queryByContent method with null query
        assertThrows(IllegalArgumentException.class, () -> noteService.queryByContent(null));

        // Verification: Ensure that findByContentContaining was not called
        verify(noteRepository, never()).findByContentContaining(any());
    }
}
