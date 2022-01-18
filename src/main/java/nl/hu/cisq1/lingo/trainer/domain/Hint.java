package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.WordAlreadyGuessedException;

import java.util.ArrayList;
import java.util.List;

public class Hint {
    private List<Character> hintList;

    public Hint() {
        hintList = new ArrayList<Character>();
    }

    public List<Character> receiveHint(List<LetterFeedback> letterFeedback, String attempt){
        for(var i = 0; i < letterFeedback.size(); i++){
            if(letterFeedback.get(i) == LetterFeedback.CORRECT){
                hintList.add(attempt.charAt(i));
            }
            else{
                hintList.add('.');
            }
        }
        return hintList;
    }
}
