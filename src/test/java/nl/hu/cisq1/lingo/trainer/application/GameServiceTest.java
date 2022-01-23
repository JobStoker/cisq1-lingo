package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.NoGameFoundException;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import nl.hu.cisq1.lingo.trainer.domain.GameState.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    @Test
    @DisplayName("start Game Test")
    void startGameTest(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        GameService gameService = new GameService(wordService, gameRepository);

        Progress progress = gameService.startNewGame();
        assertEquals(progress.getRounds().size(), 1);
        assertEquals(progress.getHint(), null);
        assertEquals("PLAY", progress.getStatus());
    }

    @Test
    @DisplayName("Start second round within game")
    void startNewRoundTest(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        GameService gameService = new GameService(wordService, gameRepository);
        Game game = new Game();

        when(gameRepository.findById(any())).thenReturn(Optional.of(game));

        Progress gameProgress = game.createProgress();
        Progress roundProgress = gameService.startNewRound(gameProgress.getId());
        assertEquals(1, roundProgress.getRounds().size());
    }

    @Test
    @DisplayName("Find no game with wrong Id")
    void findGameTest(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        GameService gameService = new GameService(wordService, gameRepository);

        when(gameRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoGameFoundException.class, () -> gameService.findGame(12));
    }
}
