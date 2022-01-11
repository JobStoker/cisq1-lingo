package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Round
{
    private long id;

    private Word wordToGuess;

    private List<LetterFeedback> letterFeedback;

    public Round(){}
    public Round(Word word){
        this.wordToGuess = word;
        this.letterFeedback = new ArrayList<>();
    }

    public List<LetterFeedback> guessWord(String attempt){
        var feedback = Feedback.getLetterFeedback(attempt.toUpperCase(Locale.ROOT), wordToGuess.getWord());

        if(this.letterFeedback.size() <= 5 ){
            letterFeedback = feedback;
        }
        return feedback;
    }

    public Hint getHint(){ return new Hint(this.wordToGuess, this.letterFeedback); }

}
