package br.com.questions.controllers;

import br.com.questions.entity.Flag;
import br.com.questions.service.FlagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlagController.class)
public class FlagWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlagService service;

    @Test
    public void findFromService() throws Exception {
        Long id = 1L;
        Flag mockFlag = new Flag(id, "Dev", true );

        when(service.findById(id)).thenReturn(mockFlag);
        this.mockMvc.perform(get("/flag/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(mockFlag.getDescription()))
                .andExpect(jsonPath("$.id").value(mockFlag.getId()));
    }

    @Test
    public void insertFromService() throws Exception {
        Long id = 1L;
        Flag mockFlag = new Flag(id, "Dev", true );

        when(service.insertFlag(mockFlag)).thenReturn(mockFlag);
        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/flag")
                .content(asJsonString(mockFlag))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    public void deleteFromService() throws Exception {
        Long id = 1L;
        Flag mockFlag = new Flag(id, "Dev", true );

        when(service.insertFlag(mockFlag)).thenReturn(mockFlag);

        this.mockMvc.perform( MockMvcRequestBuilders
                .delete("/flag/"+id)
                .content(asJsonString(mockFlag))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void updateFromService() throws Exception {
        Long id = 1L;
        Flag mockFlag = new Flag(id, "Dev", true );

        when(service.insertFlag(mockFlag)).thenReturn(mockFlag);
        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/flag/"+id)
                .content(asJsonString(mockFlag))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            String result = new ObjectMapper().writeValueAsString(obj);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
