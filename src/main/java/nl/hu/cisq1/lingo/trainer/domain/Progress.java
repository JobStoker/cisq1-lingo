package nl.hu.cisq1.lingo.trainer.domain;

import javax.annotation.processing.Generated;
import java.util.List;

public class Progress {
    private Long id;
    private int score;
    private GameState status;
    private Hint hint;
    private List<Round> rounds;
    private GameState gameState;

    public Progress(
            Long id,
            int score,
            GameState status,
            Hint hint,
            List<Round> rounds
    ) {
        this.id = id;
        this.score = score;
        this.status = status;
        this.hint = hint;
        this.rounds = rounds;
    }

    public Long getId() {
        return this.id;
    }

    public int getScore() {
        return this.score;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public Hint getHint() {
        return this.hint;
    }

    public List<Round> getRounds() {
        return this.rounds;
    }
}

