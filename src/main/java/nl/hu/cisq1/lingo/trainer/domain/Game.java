package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.application.WordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {

    private long id;

    private GameState gameState;

    private List<Round> rounds;

    private int score;

    private int wordLength;

    //Initalizing new game
    public Game(){
        this.gameState = GameState.NOT_STARTED;
        this.rounds = new ArrayList<>();
    }

    public Round newRound(WordService wordService){
        //WordService wordService = new WordService();

        Round round = new Round();

        return round;
    }

    public int setScore(Round round){
        return score;
    }

    public Progress createProgress(){
        return null;
    }
}
