package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Hint {
    private List<Character> previousHint;

    public Hint(Word wordToGuess, List<LetterFeedback> letterFeedback) {
    }

    public List<Character> receiveHint(List<LetterFeedback> feedback, List<Character> previousHint, Word word){
        String wordToGuess = word.getWord();
        for(var i = 0; i < feedback.size(); i++){
            if(feedback.get(i) == LetterFeedback.CORRECT){
                previousHint.add(wordToGuess.charAt(i));
            }
        }
        return previousHint;
    }
}
