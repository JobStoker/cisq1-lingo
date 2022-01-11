package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;

public class GameService {
    private SpringGameRepository gameRepository;
    private WordService wordService;

    private Game game;

    public GameService(SpringGameRepository gameRepository, WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    public Progress startNewGame(){
        this.game = new Game();

        this.game.newRound(wordService);
        this.gameRepository.save(this.game);

        return this.game.createProgress();
    }
}
