package br.com.questions.controllers;

import br.com.questions.entity.Role;
import br.com.questions.service.RoleService;
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

@WebMvcTest(RoleController.class)
public class RoleWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService service;

    @Test
    public void findFromService() throws Exception {
        Long id = 1L;
        Role mockRole = new Role(id, "RoleTestMock", true);

        when(service.findById(id)).thenReturn(mockRole);
        this.mockMvc.perform(get("/role/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(mockRole.getDescription()))
                .andExpect(jsonPath("$.id").value(mockRole.getId()));
    }

    @Test
    public void insertFromService() throws Exception {
        Long id = 1L;
        Role mockRole = new Role(id, "RoleTestMock", true);

        when(service.insertRole(mockRole)).thenReturn(mockRole);
        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/role")
                .content(asJsonString(mockRole))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    public void deleteFromService() throws Exception {
        Long id = 1L;
        Role mockRole = new Role(id, "RoleTestMock", true);

        when(service.insertRole(mockRole)).thenReturn(mockRole);

        this.mockMvc.perform( MockMvcRequestBuilders
                .delete("/role/"+id)
                .content(asJsonString(mockRole))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void updateFromService() throws Exception {
        Long id = 1L;
        Role mockRole = new Role(id, "RoleTestMock", true);

        when(service.insertRole(mockRole)).thenReturn(mockRole);
        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/role/"+id)
                .content(asJsonString(mockRole))
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
