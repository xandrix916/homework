package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

class Problem7Test {
    @BeforeEach
    void setUp() {
        problem7 = new Problem7();
    }

    private Problem7 problem7;

    @Test
    void exampleTest() {
        TreeMap<String, String> exampleTree = problem7.makingCrutch(new String[]{}, new String[]{}, "test");
        Assertions.assertThat(exampleTree.containsKey(null)).isTrue();
    }

    @Test
    void orderedNameList() {
        TreeMap<Integer, String> nameTree = problem7.makingCrutch(new Integer[]{1, 4, 5, 9},
            new String[]{"Brad Pitt", "Keanu Reeves", "Jason Statham", "Matthew McConaughey"},
            "Alexander \"Mr. Multiuniverse\" Nevsky");
        Assertions.assertThat(nameTree.containsKey(null)).isTrue();
    }

    @Test
    void wrongInput() {
        TreeMap<Integer, Integer> wrongTree = problem7.makingCrutch(new Integer[]{2, 4, 9}, new Integer[]{3, 7, 11, 25}, 1);
        Assertions.assertThat(wrongTree.isEmpty()).isTrue();
    }
}
