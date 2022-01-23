package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordService wordService;

    @MockBean
    private SpringGameRepository gameRepository;

    @BeforeEach
    void beforeEach() {
        when(wordService.provideRandomWord(5)).thenReturn("baard");
    }

    @Test
    @DisplayName("Start a new game test")
    void startNewGameTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/lingo/start");

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.status").value("PLAY"));
    }
}
