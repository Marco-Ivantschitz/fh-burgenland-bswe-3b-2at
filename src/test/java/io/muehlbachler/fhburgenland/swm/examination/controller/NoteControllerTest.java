package io.muehlbachler.fhburgenland.swm.examination.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
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
        String validId = "validId";
        Note mockNote = new Note(); // create a mock Note object

        // Mock the behavior of noteService.get(id)
        when(noteService.get(validId)).thenReturn(Optional.of(mockNote));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/note/{id}", validId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(validId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(mockNote.getContent()));
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

    @Test
    public void testGetNoteById_NullId() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/note/{id}", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
