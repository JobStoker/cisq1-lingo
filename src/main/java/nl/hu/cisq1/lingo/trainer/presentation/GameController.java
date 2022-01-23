package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lingo")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<Progress> startGame() {
        Progress progress = this.gameService.startNewGame();
        return new ResponseEntity<>(progress, HttpStatus.CREATED);
    }

    @PostMapping("/guess/{id}/{attempt}")
    public Progress guessWord(@PathVariable Long id, @PathVariable  String attempt) {
        return this.gameService.takeAGuess(id, attempt);
    }

    @PostMapping("/game/{id}/round")
    public Progress newRound(@PathVariable Long id) {
        return this.gameService.startNewRound(id);
    }
}
