package be.inetumrealdolmen.springtrack.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Random;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private GameState state;

    private Integer moves;

    public boolean isFinished() {
        return !GameState.IN_PROGRESS.equals(this.getState());
    }

    public boolean isWon() {
        return GameState.WON.equals(this.getState());
    }

    public void move() {
        this.setMoves(++moves);
        if (moves >= 100) {
            finishGame();
        }
    }

    private void finishGame() {
        if (new Random().nextBoolean()) {
            this.setState(GameState.WON);
        }
    }
}
