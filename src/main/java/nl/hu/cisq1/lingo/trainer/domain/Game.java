package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "game")
public class Game {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private GameState gameState;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Round> rounds;

    @Column
    private int score;

    @OneToOne(cascade = CascadeType.ALL)
    private Hint hint;

    private int wordLength = 5;

    //Initalizing new game
    public Game(){
        this.gameState = GameState.NOT_STARTED;
        this.rounds = new ArrayList<>();
    }

    public Round newRound(String word){
        Round round = new Round(word);
        this.gameState = GameState.PLAY;
        rounds.add(round);
        return round;
    }

    public void attemptGuess(String attempt){
        Round round = getCurrentRound();
        round.guessWord(attempt);
        this.hint = round.getHint();
        if (round.checkIfWordGuessed() || round.getGuessCount() > 5) {
            this.gameState = GameState.WAIT;
        } else if (round.getGuessCount() < 5) {
            this.gameState = GameState.PLAY;
        }
    }

    private Round getCurrentRound() {
        return this.rounds.get(rounds.size() - 1);
    }

    private void calculateScore(){
        for (Round round : this.rounds){
            this.score = this.score + 5 * (5 - round.getGuessCount()) + 5;
        }
    }

    public int getScore(){
        return score;
    }

    public Progress createProgress(){
        calculateScore();
        return new Progress(this.id, this.score, this.gameState, this.hint, this.rounds);
    }

    public List<Round> getRounds() {
        return this.rounds;
    }

    public GameState getGameState() { return this.gameState; };
}
