package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class Problem1Test {
    @BeforeEach
    void setUp(){
        problem1 = new Problem1();
    }

    private Problem1 problem1;

//    @Test
//    void sayOtherShouldThrowException(){
//        String word = "other";
//        Assertions.assertThatThrownBy(() -> {
//            problem1.say(word);
//        }).isInstanceOf(IllegalArgumentException.class);
//    }

    @Test
    void oneMinute() {
        String time = "01:00";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(60);
    }

    @Test
    void randomCorrectTime() {
        String time = "45:23";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(2723);
    }

    @Test
    void sixtyPlusSeconds() {
        String time = "10:60";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тест на случай введения отрицательного времени, либо в строке есть посторонние символы")
    void timeBelowZeroAndOddSymbols() {
        String time = "-1:00";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тест на случай, если строка слишком короткая")
    void tooShortString() {
        String time = "1:00";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тест на случай, если в строке не окажется разделителя :")
    void noSeparatorString() {
        String time = "12300";
        int response = problem1.minutesToSeconds(time);
        Assertions.assertThat(response).isEqualTo(-1);
    }

}
