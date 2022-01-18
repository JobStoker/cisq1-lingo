package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.application.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.CORRECT;

public class Round
{
    private long id;

    private Word wordToGuess;

    private List<LetterFeedback> letterFeedback;

    private Hint hint;

    private boolean wordIsGuessed = false;

    private int guessCount = 0;

    public Round(){}
    public Round(Word word){
        this.wordToGuess = word;
        this.letterFeedback = new ArrayList<>();
        this.hint = new Hint();
    }

    public Round guessWord(String attempt){
        this.guessCount++;
        this.letterFeedback = Feedback.getLetterFeedback(attempt.toUpperCase(Locale.ROOT), wordToGuess.getWord());
        this.hint = (Hint) hint.receiveHint(this.letterFeedback, attempt);
        return this;
    }

    public boolean checkIfWordGuessed(){
        return this.letterFeedback.stream().allMatch(feedback -> feedback == CORRECT);
    }

    public Hint getHint(){
        return this.hint;
    }

    public List<LetterFeedback> getLetterFeedback(){
        return this.letterFeedback;
    }
}
