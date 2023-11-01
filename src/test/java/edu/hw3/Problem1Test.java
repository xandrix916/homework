package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem1Test {
    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
    }

    private Problem1 problem1;

    @Test
    void hiPlanet() {
        String original = "Hello world!";
        String cyphered = problem1.atbash(original);
        Assertions.assertEquals("Svool dliow!", cyphered);
    }

    @Test
    void reallySmartQuote() {
        String original = "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler";
        String cyphered = problem1.atbash(original);
        Assertions.assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
            " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi", cyphered);
    }

    @Test
    void pseudoSmartQuote() {
        String original = "There were 2 goats. How many? - Jacque Fresco";
        String cyphered = problem1.atbash(original);
        Assertions.assertEquals("Gsviv dviv 2 tlzgh. Sld nzmb? - Qzxjfv Uivhxl", cyphered);
    }
}
