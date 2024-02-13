package io.muehlbachler.fhburgenland.swm.examination.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for the {@link NoteController} class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    /**
     * Tests the retrieval of a note by its ID.
     *
     * @throws Exception if an error occurs during testing.
     */
    @Test
    public void testGetNoteById() throws Exception {
        String noteId = "1";
        Note expectedNote = new Note();
        when(noteService.get(noteId)).thenReturn(Optional.of(expectedNote));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/note/{id}", noteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Note actualNote = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), Note.class);
        assertNotNull(actualNote);
        assertEquals(expectedNote, actualNote);
    }

    /**
     * Tests querying notes by their content.
     *
     * @throws Exception if an error occurs during testing.
     */
    @Test
    public void testQueryNotesByContent() throws Exception {
        String query = "test";
        List<Note> expectedNotes = new ArrayList<>();
        when(noteService.queryByContent(query)).thenReturn(expectedNotes);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/note/query")
                        .param("query", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        List<Note> actualNotes = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Note>>() {});
        assertNotNull(actualNotes);
        assertEquals(expectedNotes.size(), actualNotes.size());
    }

    /**
     * Tests querying notes by non-matching content.
     *
     * @throws Exception if an error occurs during testing.
     */
    @Test
    public void testQueryNotesByContent_NoMatch() throws Exception {
        String query = "nonexistentQuery";

        when(noteService.queryByContent(query)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/note/query?query={query}", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}