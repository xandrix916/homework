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
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_1);
    }

    @Test
    void testDepth() {
        game = new Game("depth");
        Turn[] turns = new Turn[]{new Turn(1,"1","","d",""),
            new Turn(2,"1","","e",""),
            new Turn(3,"1","","h",""),
            new Turn(4,"1","","t",""),
            new Turn(5,"2","","death",""),
            new Turn(6,"2","","depth","")
        };
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_2);
    }

    @Test
    void testHangover() {
        game = new Game("hangover");
        Turn[] turns = new Turn[]{new Turn(1,"1","","e",""),
            new Turn(2,"1","","c",""),
            new Turn(3,"1","","i",""),
            new Turn(4,"1","","o",""),
            new Turn(5,"1","","y",""),
            new Turn(6,"1","","u",""),
            new Turn(7,"1","","x",""),
            new Turn(8,"1","","p","")
        };
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_3);
    }

    @Test
    void rageTest() {
        game = new Game("");
        Turn[] turns = new Turn[]{new Turn(1, "40", "42", "", "")};
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_4);
    }

    @Test
    void dumbAndDumber() {
        game = new Game("");
        Turn[] turns = new Turn[]{new Turn(1, "abubi", "ehfjejfhej", "", "")};
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_5);
    }

    @Test
    void brokenKeyboard() {
        game = new Game("typewriter");
        Turn[] turns = new Turn[]{new Turn(1,"1","","y",""),
                                  new Turn(2,"1","","e",""),
                                  new Turn(3,"1","","r",""),
                                  new Turn(4,"1","","i",""),
                                  new Turn(5,"2","","ty[ewriter",""),
                                  new Turn(6,"2","","typ3writ3r",""),
                                  new Turn(7,"2","","type24iter",""),
                                  new Turn(8,"2","","damn it",""),
                    };
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_6);
    }

    @Test
    void whySoManyLetters() {
        game = new Game("Pneumonoultramicroscopicsilicovolcanoconiosis");
        Turn[] turns = new Turn[]{new Turn(1, "3", "", "", "yes")};
        GameJournal response = game.run(turns);
        Assertions.assertThat(response.toString()).isEqualTo(Responses.RESPONSE_7);
    }
}
