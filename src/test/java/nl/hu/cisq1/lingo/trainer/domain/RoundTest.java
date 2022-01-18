package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RoundTest {
    private static Word word = new Word("BAARD");
    private Round round;

    @BeforeEach
    void setUp(){
        this.round = new Round(word);
    }
    @Test
    @DisplayName("Valid guess without guessing the correct word")
    void validGuessTest(){
        round.guessWord("PAARD");

        //assertEquals(List.of('.', 'A', 'A', 'R', 'D'), round.getHint());
        //assertFalse(round.checkIfWordGuessed());
    }

    @Test
    @DisplayName("invalid guess")
    void invalidGuessTest(){}

    @Test
    @DisplayName("Guessing correct word")
    void correctWordTest(){}

    @Test
    @DisplayName("invalid guess")
    void allGuessesIncorrectWordTest(){}

    @Test
    @DisplayName("invalid guess")
    void allGuessesCorrectWordTest(){}
}
