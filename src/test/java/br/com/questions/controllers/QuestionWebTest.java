package br.com.questions.controllers;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.Flag;
import br.com.questions.entity.Question;
import br.com.questions.entity.User;
import br.com.questions.service.QuestionService;
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

@WebMvcTest(QuestionController.class)
public class QuestionWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService service;

    @Test
    public void findFromService() throws Exception {
        Long id = 1L;
        Question mockQuestion = new Question(id, new User(), new Flag(),"O que Teste Integrado", false );

        when(service.findById(id)).thenReturn(mockQuestion);
        this.mockMvc.perform(get("/question/"+id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value(mockQuestion.getComment()))
                .andExpect(jsonPath("$.id").value(mockQuestion.getId()));
    }

    @Test
    public void insertFromService() throws Exception {
        Long id = 1L;
        Question mockQuestion = new Question(id, new User(), new Flag(),"O que Teste Integrado", false );
        QuestionDto questionDtoMock = new QuestionDto(false, "O que Teste Integrado", 1L, 1L);

        when(service.insertQuestion(questionDtoMock)).thenReturn(mockQuestion);
        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/question")
                .content(asJsonString(questionDtoMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    public void deleteFromService() throws Exception {
        Long id = 1L;
        Question mockQuestion = new Question(id, new User(), new Flag(),"O que Teste Integrado", false );
        QuestionDto questionDtoMock = new QuestionDto(false, "O que Teste Integrado", 1L, 1L);

        when(service.insertQuestion(questionDtoMock)).thenReturn(mockQuestion);

        this.mockMvc.perform( MockMvcRequestBuilders
                .delete("/question/"+id)
                .content(asJsonString(mockQuestion))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void updateFromService() throws Exception {
        Long id = 1L;
        Question mockQuestion = new Question(id, new User(), new Flag(),"O que Teste Integrado", false );
        QuestionDto questionDtoMock = new QuestionDto(false, "O que Teste Integrado", 1L, 1L);

        when(service.insertQuestion(questionDtoMock)).thenReturn(mockQuestion);
        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/question/"+id)
                .content(asJsonString(questionDtoMock))
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
