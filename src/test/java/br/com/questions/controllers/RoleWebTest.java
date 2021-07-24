package br.com.questions.controllers;

import br.com.questions.entity.Role;
import br.com.questions.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

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
    public void rolesFromService() throws Exception {
        Long id = 1L;
        Role mockRole = new Role(id, "RoleTestMock", true);

        when(service.findById(id)).thenReturn(mockRole);
        this.mockMvc.perform(get("/role/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(mockRole.getDescription()))
                .andExpect(jsonPath("$.id").value(mockRole.getId()));
    }
}
