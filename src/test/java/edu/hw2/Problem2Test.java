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
        Assertions.assertThat(response).isEqualTo("Medium area is approximately 31, medium height is approximately 7, medium width is approximately 14\n");
    }

    @Test
    void checkLSPthree() {
        Problem2.Rect[] rects = {new Problem2.Rect.Rectangle(1.0, 5.0),
            new Problem2.Rect.Square(2.0), new Problem2.Rect.Rectangle(3.0, 6.0)};
        String response = problem2.checkLSP(rects);
        Assertions.assertThat(response).isEqualTo("""
            Width - 5, Height - 1
            Width - 2, Height - 2
            Width - 6, Height - 3
            """);
    }
}
