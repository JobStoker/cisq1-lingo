package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "hint")
public class Hint {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private List<Character> hintList;

    public Hint() {
        hintList = new ArrayList<Character>();
    }

    public Hint receiveHint(List<LetterFeedback> letterFeedback, String attempt){
        hintList.clear();
        for(var i = 0; i < letterFeedback.size(); i++){
            if(letterFeedback.get(i) == LetterFeedback.CORRECT){
                hintList.add(attempt.charAt(i));
            }
            else{
                hintList.add('.');
            }
        }
        return this;
    }

    public List<Character> getHintList(){ return this.hintList; }
}
