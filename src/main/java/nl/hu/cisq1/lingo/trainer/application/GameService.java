package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.NoGameFoundException;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private WordService wordService;

    private SpringGameRepository gameRepository;

    private Game game;

    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Progress startNewGame(){
        this.game = new Game();
        //Provide random word based on rounds
        String word = this.wordService.provideRandomWord(getWordLength(game));
        this.game.newRound(word);
        this.gameRepository.save(this.game);

        return this.game.createProgress();
    }

    public Progress startNewRound(Long id){
        this.findGame(id);
        String word = this.wordService.provideRandomWord(getWordLength(game));
        this.game.newRound(word);
        this.gameRepository.save(this.game);

        return this.game.createProgress();
    }

    public Progress takeAGuess(long id, String attempt){
        this.findGame(id);

        this.game.attemptGuess(attempt);
        this.gameRepository.save(game);

        return this.game.createProgress();
    }

    private int getWordLength(Game game){
        int length;
        switch(this.game.getRounds().size() + 1) {
            case 2:
                length = 6;
                break;
            case 3:
                length = 7;
                break;
            default:
                length = 5;
        }
        return length;
    }

    public Progress findGame(long id){
        Optional<Game> optionalGame = gameRepository.findById(id);

        if (optionalGame.isPresent()) {
            this.game = optionalGame.get();
            return this.game.createProgress();
        } else {
            throw new NoGameFoundException("No game was found with ID: " + id);
        }
    }
}
