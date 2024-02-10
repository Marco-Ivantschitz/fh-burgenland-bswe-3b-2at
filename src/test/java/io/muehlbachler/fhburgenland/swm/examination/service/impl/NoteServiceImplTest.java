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
        Note expectedNote = new Note();
        expectedNote.setContent("Test content");

        when(noteRepository.findById("1")).thenReturn(Optional.of(expectedNote));

        Optional<Note> actualNote = noteService.get("1");

        assertTrue(actualNote.isPresent());
        assertEquals(expectedNote.getContent(), actualNote.get().getContent());

        verify(noteRepository, times(1)).findById("1");
    }


    @Test
    void testGetNoteByIdNotFound() {
        when(noteRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Note> actualNote = noteService.get("2");

        assertFalse(actualNote.isPresent());

        verify(noteRepository, times(1)).findById("2");
    }

    @Test
    void testCreateNoteSuccess() {
        Note newNote = new Note();
        newNote.setContent("New test content");

        when(noteRepository.save(newNote)).thenReturn(newNote);

        Note createdNote = noteService.create(newNote);

        assertEquals(newNote, createdNote);

        verify(noteRepository, times(1)).save(newNote);
    }

    @Test
    void testCreateNoteNull() {
        assertThrows(IllegalArgumentException.class, () -> noteService.create(null));

        verify(noteRepository, never()).save(any());
    }

    @Test
    void testQueryByContentSuccess() {
        String query = "test";
        List<Note> expectedNotes = List.of(new Note(), new Note());
        when(noteRepository.findByContentContaining(query)).thenReturn(expectedNotes);

        List<Note> actualNotes = noteService.queryByContent(query);

        assertEquals(expectedNotes, actualNotes);

        verify(noteRepository, times(1)).findByContentContaining(query);
    }

    @Test
    void testQueryByContentNull() {
        assertThrows(IllegalArgumentException.class, () -> noteService.queryByContent(null));

        verify(noteRepository, never()).findByContentContaining(any());
    }
}
