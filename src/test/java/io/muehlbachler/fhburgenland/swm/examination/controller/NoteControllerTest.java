package io.muehlbachler.fhburgenland.swm.examination.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;


    @Test
    public void testGetNoteById() throws Exception {
        // Arrange
        String noteId = "1";
        Note expectedNote = new Note();
        when(noteService.get(noteId)).thenReturn(Optional.of(expectedNote));

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/note/{id}", noteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Assert
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        // Überprüfen, ob das empfangene Note-Objekt gültig ist
        Note actualNote = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Note.class);
        assertNotNull(actualNote);
        // Überprüfen, ob das empfangene Note-Objekt das erwartete Note-Objekt ist
        assertEquals(expectedNote, actualNote);
    }

    @Test
    public void testQueryNotesByContent() throws Exception {
        // Arrange
        String query = "test";
        List<Note> expectedNotes = new ArrayList<>();
        when(noteService.queryByContent(query)).thenReturn(expectedNotes);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/note/query")
                        .param("query", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Assert
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        List<Note> actualNotes = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Note>>() {});
        assertNotNull(actualNotes);
        assertEquals(expectedNotes.size(), actualNotes.size());
    }

    @Test
    public void testQueryNotesByContent_NoMatch() throws Exception {
        // Arrange
        String query = "nonexistentQuery";

        // Mock the behavior of noteService.queryByContent(query)
        when(noteService.queryByContent(query)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/note/query?query={query}", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}
