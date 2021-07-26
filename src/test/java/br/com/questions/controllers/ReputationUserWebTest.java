package br.com.questions.controllers;

import br.com.questions.dto.ReputationUserDto;
import br.com.questions.entity.Flag;
import br.com.questions.entity.ReputationUser;
import br.com.questions.entity.User;
import br.com.questions.service.FlagService;
import br.com.questions.service.ReputationUserService;
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

@WebMvcTest(ReputationUserController.class)
public class ReputationUserWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReputationUserService service;

    @Test
    public void findFromService() throws Exception {
        Long id = 1L;
        ReputationUser reputationUserFlag = new ReputationUser(id, new User(), 10L );

        when(service.findByIdReputationUser(id)).thenReturn(reputationUserFlag);
        this.mockMvc.perform(get("/reputationUser/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(reputationUserFlag.getScore()))
                .andExpect(jsonPath("$.id").value(reputationUserFlag.getId()));
    }

    @Test
    public void insertFromService() throws Exception {
        Long id = 1L;
        ReputationUser reputationUserFlag = new ReputationUser(id, new User(), 10L );

        ReputationUserDto reputationUserDto = new ReputationUserDto(1L, 10L);

        when(service.insertReputationUser(reputationUserDto)).thenReturn(reputationUserFlag);
        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/reputationUser")
                .content(asJsonString(reputationUserDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    public void deleteFromService() throws Exception {
        Long id = 1L;
        ReputationUser reputationUserFlag = new ReputationUser(id, new User(), 10L );
        ReputationUserDto reputationUserDto = new ReputationUserDto(1L, 10L);


        when(service.insertReputationUser(reputationUserDto)).thenReturn(reputationUserFlag);

        this.mockMvc.perform( MockMvcRequestBuilders
                .delete("/reputationUser/"+id)
                .content(asJsonString(reputationUserFlag))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void updateFromService() throws Exception {
        Long id = 1L;
        ReputationUserDto reputationUserDto = new ReputationUserDto(1L, 10L);
        ReputationUser reputationUserFlag = new ReputationUser(id, new User(), 10L );

        when(service.insertReputationUser(reputationUserDto)).thenReturn(reputationUserFlag);
        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/reputationUser/"+id)
                .content(asJsonString(reputationUserDto))
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
