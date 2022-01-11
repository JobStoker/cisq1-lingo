package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "word")
public class Word {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String word;

    public Word(){};
    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}