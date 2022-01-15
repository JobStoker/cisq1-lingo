package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;

public class GameService {
    private WordService wordService;

    private Game game;

    public GameService(WordService wordService) {
        this.wordService = wordService;
    }

    public Progress startNewGame(){
        this.game = new Game();

        this.game.newRound(wordService);

        return this.game.createProgress();
    }
}
