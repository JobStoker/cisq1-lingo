package nl.hu.cisq1.lingo.trainer.domain.exception;

public class WordAlreadyGuessedException extends RuntimeException{
    public WordAlreadyGuessedException(String string) {super(string);}
}
