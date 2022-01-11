package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {
    static Stream<Arguments> examples(){
        return Stream.of(
                Arguments.of("BAARD", "BERGEN", List.of(INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BEDDE", List.of(CORRECT, ABSENT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BAARD", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource("examples")
    @DisplayName("Verify compared word based on attempted word and given word")
    void checkWordGuessed(String word, String attempt, List<LetterFeedback> feedback){
        var returnedFeedback = Feedback.getLetterFeedback(word, attempt);

        assertEquals(feedback, returnedFeedback);
    }

}
