package br.com.questions.controllers;

import br.com.questions.entity.Role;
import br.com.questions.service.RoleService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(RoleController.class)
public class RoleWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService service;

    @Test
    public void rolesFromService() throws Exception {
        Integer id = 1;
        Role mockRole = new Role(id, "RoleTestMock", LocalDate.now(), LocalDate.now(), true);

        when(service.findById(id)).thenReturn(mockRole);
        this.mockMvc.perform(get("/role/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(mockRole.getDescription()))
                .andExpect(jsonPath("$.id").value(mockRole.getId()));
    }
}
