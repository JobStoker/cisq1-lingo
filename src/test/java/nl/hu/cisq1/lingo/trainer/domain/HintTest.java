package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HintTest {
    private static String word = "BAARD";

    @Test
    @DisplayName("Creating hint and test if hint is changed")
    void createHintTest(){
        List<LetterFeedback> letterFeedback = new ArrayList<LetterFeedback>(Arrays.asList(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT));

        Hint hint = new Hint();
        assertEquals(List.of('B', '.', '.', '.', '.'), hint.receiveHint(letterFeedback, word).getHintList());
    }

    @Test
    @DisplayName("Test if word is already guessed")
    void wordIsGuessedTest(){
        List<LetterFeedback> letterFeedback = new ArrayList<LetterFeedback>(Arrays.asList(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT));

        Hint hint = new Hint();
        assertNotEquals(List.of('B', 'A', 'A', 'R', 'D'), hint.receiveHint(letterFeedback, word).getHintList());
    }

    @Test
    @DisplayName("Test hint wiht invalid feedback")
    void hintWithInvalidFeedback(){
        String word = "BAARDEN";

        List<LetterFeedback> letterFeedback = new ArrayList<LetterFeedback>(Arrays.asList(INVALID, INVALID, INVALID, INVALID, INVALID));

        Hint hint = new Hint();
        assertNotEquals(List.of('B', 'A', 'A', 'R', 'D'), hint.receiveHint(letterFeedback, word).getHintList());
    }
}
