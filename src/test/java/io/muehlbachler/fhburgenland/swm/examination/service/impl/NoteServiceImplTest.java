package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.repository.NoteRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

/**
 * Unit tests for the {@link NoteServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    /**
     * Test case for successfully retrieving a note by ID.
     */
    @Test
    void testGetNoteByIdSuccess() {
        Note expectedNote = new Note();
        expectedNote.setContent("Test content");

        when(noteRepository.findById("1")).thenReturn(Optional.of(expectedNote));

        Optional<Note> actualNote = noteService.get("1");

        assertTrue(actualNote.isPresent());
        assertEquals(expectedNote.getContent(), actualNote.get().getContent());

        verify(noteRepository, times(1)).findById("1");
    }


    /**
     * Test case for retrieving a note by ID when not found.
     */
    @Test
    void testGetNoteByIdNotFound() {
        when(noteRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Note> actualNote = noteService.get("2");

        assertFalse(actualNote.isPresent());

        verify(noteRepository, times(1)).findById("2");
    }

    /**
     * Test case for successfully creating a new note.
     */
    @Test
    void testCreateNoteSuccess() {
        Note newNote = new Note();
        newNote.setContent("New test content");

        when(noteRepository.save(newNote)).thenReturn(newNote);

        Note createdNote = noteService.create(newNote);

        assertEquals(newNote, createdNote);

        verify(noteRepository, times(1)).save(newNote);
    }

    /**
     * Test case for creating a new note with null input.
     */
    @Test
    void testCreateNoteNull() {
        assertThrows(IllegalArgumentException.class, () -> noteService.create(null));

        verify(noteRepository, never()).save(any());
    }

    /**
     * Test case for successfully querying notes by content.
     */
    @Test
    void testQueryByContentSuccess() {
        String query = "test";
        List<Note> expectedNotes = List.of(new Note(), new Note());
        when(noteRepository.findByContentContaining(query)).thenReturn(expectedNotes);

        List<Note> actualNotes = noteService.queryByContent(query);

        assertEquals(expectedNotes, actualNotes);

        verify(noteRepository, times(1)).findByContentContaining(query);
    }

    /**
     * Test case for querying notes by content with null input.
     */
    @Test
    void testQueryByContentNull() {
        assertThrows(IllegalArgumentException.class, () -> noteService.queryByContent(null));

        verify(noteRepository, never()).findByContentContaining(any());
    }
}
