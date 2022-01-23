package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.GuessCountExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    void beforeEach() {
        this.game = new Game();
        game.newRound("BAARD");
    }

    @Test
    @DisplayName("Start new game test")
    void startNewGameTest(){
        Game game = new Game();
        game.newRound("BAARD");

        assertEquals(game.getGameState(), GameState.PLAY);
        assertEquals(game.getRounds().size(), 1);
    }

    @Test
    @DisplayName("Attempt guess")
    void firstGuessTest(){
        this.game.attemptGuess("PAARD");
        assertEquals(game.getGameState(), GameState.PLAY);
        assertEquals(game.getRounds().size(), 1);
    }

    @Test
    @DisplayName("Test 5 guesses")
    void guess5TimesTest(){
        game.attemptGuess("SOORT");
        game.attemptGuess("VAREN");
        game.attemptGuess("STAAR");
        game.attemptGuess("PAARD");
        game.attemptGuess("BAARD");
        assertEquals(game.getGameState(), GameState.WAIT);
    }
    @Test
    @DisplayName("Test 6 guesses in 1 round")
    void guess6TimesTest(){
        game.attemptGuess("SOORT");
        game.attemptGuess("VAREN");
        game.attemptGuess("STAAR");
        game.attemptGuess("PAARD");
        game.attemptGuess("KAART");
        assertThrows(GuessCountExceededException.class, () -> game.attemptGuess("BAARD"));
    }

    @Test
    @DisplayName("Test 3 rounds with score calculate")
    void roundScoreTest(){
        this.game.newRound("BAARD");
        this.game.newRound("STRAAT");
        this.game.newRound("STRATEN");
        assertEquals(this.game.getScore(), 0);
    }
}