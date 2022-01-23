package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.exception.GuessCountExceededException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NotRightWordLengthException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.CORRECT;

@Entity(name = "round")
public class Round
{
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String wordToGuess;

    @ElementCollection
    private List<LetterFeedback> letterFeedback;

    @OneToOne(cascade = CascadeType.ALL)
    private Hint hint;

    private boolean wordIsGuessed = false;

    private int guessCount = 0;

    public Round(String word){
        this.wordToGuess = word;
        this.letterFeedback = new ArrayList<>();
        this.hint = new Hint();
    }

    public Round() {

    }

    public Round guessWord(String attempt){
        this.guessCount++;
        if(attempt.length() != wordToGuess.length()){
            throw new NotRightWordLengthException("Not the correct word length input");
        }
        if(this.guessCount > 5){
            throw new GuessCountExceededException("Can only guess maximum of 5 times");
        }
        this.letterFeedback = Feedback.getLetterFeedback(attempt.toUpperCase(Locale.ROOT), wordToGuess);
        this.hint = hint.receiveHint(this.letterFeedback, attempt);
        this.wordIsGuessed = checkIfWordGuessed();
        return this;
    }

    public boolean checkIfWordGuessed(){
        if(letterFeedback.size() > 0) {
            return this.letterFeedback.stream().allMatch(feedback -> feedback == CORRECT);
        }
        return false;
    }

    public Hint getHint(){
        return this.hint;
    }

    public List<LetterFeedback> getLetterFeedback(){
        return this.letterFeedback;
    }

    public boolean getWordIsGuessed(){ return this.wordIsGuessed; }

    public int getGuessCount(){ return this.guessCount; }
}
