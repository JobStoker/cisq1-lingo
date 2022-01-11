package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Feedback {

    public static List<LetterFeedback> getLetterFeedback(String wordToGuess, String attempt){
        if(wordToGuess.length() != attempt.length()){
            return Collections.nCopies(wordToGuess.length(), LetterFeedback.INVALID);
        }

        List<String> letters = new ArrayList<>(List.of(wordToGuess.split("")));
        List<String> attemptLetters = new ArrayList<>(List.of(attempt.split("")));
        List<LetterFeedback> marked = new ArrayList<>();

        for (int i = 0; i < letters.size(); i++){
            String attemptLetter = attemptLetters.get(i);
            if(Objects.equals(letters.get(i), attemptLetter)){
                marked.add(LetterFeedback.CORRECT);
                letters.set(letters.indexOf(attemptLetter), " ");
            } else if(letters.contains(attemptLetter)){
                marked.add(LetterFeedback.PRESENT);
                letters.set(letters.indexOf(attemptLetter), " ");
            } else {
                marked.add(LetterFeedback.ABSENT);
            }
        }

        return marked;
    }
}
