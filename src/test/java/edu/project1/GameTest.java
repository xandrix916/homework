package edu.project1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game game;

    @Test
    void testRed() {
        game = new Game("red");
        Turn[] turns = new Turn[]{new Turn(1,"1","","e",""),
            new Turn(2,"1","","r",""),
            new Turn(3,"2","","red","")
        };
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.response1);
    }
}
