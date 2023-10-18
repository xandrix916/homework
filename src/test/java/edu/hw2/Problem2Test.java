package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class Problem2Test {
    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;

    @Test
    void mediumsThree() {
        Problem2.Rect[] rects = {new Problem2.Rect.Rectangle(1.0, 5.0),
            new Problem2.Rect.Square(2.0), new Problem2.Rect.Rectangle(3.1, 6.993)};
        String response = problem2.mediumAreaHeightWidth(rects);
        Assertions.assertThat(response).isEqualTo("Medium area is 30,678300, medium height" +
            " is 6,100000, medium width is 13,993000\n");
    }

    @Test
    void checkLSPthree() {
        Problem2.Rect[] rects = {new Problem2.Rect.Rectangle(1.0, 5.0),
            new Problem2.Rect.Square(2.0), new Problem2.Rect.Rectangle(3.1, 6.993)};
        String response = problem2.checkLSP(rects);
        Assertions.assertThat(response).isEqualTo("""
            Width - 5,000000, Height - 1,000000
            Width - 2,000000, Height - 2,000000
            Width - 6,993000, Height - 3,100000
            """);
    }
}
