package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.GuessCountExceededException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NotRightWordLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private static String word = "BAARD";
    private Round round;

    @BeforeEach
    void setUp(){
        this.round = new Round(word);
    }

    @Test
    @DisplayName("First round test before guess")
    void newFirstRoundTest(){
        assertEquals(0, round.getLetterFeedback().size());
        assertFalse(round.checkIfWordGuessed());
    }

    @Test
    @DisplayName("Valid guess without guessing the correct word")
    void validGuessTest(){
        round.guessWord("PAARD");

        assertEquals(List.of('.', 'A', 'A', 'R', 'D'), round.getHint().getHintList());
        assertFalse(round.checkIfWordGuessed());
    }

    @Test
    @DisplayName("invalid guess")
    void invalidGuessTest(){
        assertThrows(NotRightWordLengthException.class, () -> round.guessWord("BAAR"));
    }

    @Test
    @DisplayName("5 Guesses with no correct guess")
    void allGuessesIncorrectWordTest(){
        round.guessWord("SOORT");
        round.guessWord("VAREN");
        round.guessWord("STAAR");
        round.guessWord("PAARD");
        round.guessWord("TAART");
        assertEquals(List.of('.', 'A', 'A', 'R', '.'), round.getHint().getHintList());
        assertFalse(round.checkIfWordGuessed());
    }

    @Test
    @DisplayName("5 Guesses with last correct")
    void allGuessesCorrectWordTest(){
        round.guessWord("SOORT");
        round.guessWord("VAREN");
        round.guessWord("STAAR");
        round.guessWord("PAARD");
        round.guessWord("BAARD");
        assertEquals(List.of('B', 'A', 'A', 'R', 'D'), round.getHint().getHintList());
        assertTrue(round.checkIfWordGuessed());
    }

    @Test
    @DisplayName("6 Guesses with last correct")
    void moreThanAllowedGuessesTest(){
        round.guessWord("SOORT");
        round.guessWord("VAREN");
        round.guessWord("STAAR");
        round.guessWord("PAARD");
        round.guessWord("KAART");
        assertThrows(GuessCountExceededException.class, () -> round.guessWord("BAARD"));
    }
}
