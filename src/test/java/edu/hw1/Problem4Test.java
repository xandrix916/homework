package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void veryGoodPasswordSix() {
        String mixedString = "123456";
        String response = problem4.fixString(mixedString);
        Assertions.assertThat(response).isEqualTo("214365");
    }

    @Test
    void mixedCorrectNotice() {
        String mixedString = "hTsii  s aimex dpus rtni.g";
        String response = problem4.fixString(mixedString);
        Assertions.assertThat(response).isEqualTo("This is a mixed up string.");
    }

    @Test
    void alphabetFirstFive() {
        String mixedString = "badce";
        String response = problem4.fixString(mixedString);
        Assertions.assertThat(response).isEqualTo("abcde");
    }

    @Test
    void hollowString() {
        String mixedString = "";
        String response = problem4.fixString(mixedString);
        Assertions.assertThat(response).isEqualTo(mixedString);
    }
}
