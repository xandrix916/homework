package edu.hw7;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Problem1 {
    public int count(int countLimit1, int countLimit2) {
        AtomicInteger value = new AtomicInteger();
        Thread incrementor1 = new Thread(() -> {
            for (int i = 0; i < countLimit1; i++) {
                value.incrementAndGet();
            }
        });
        Thread incrementor2 = new Thread(() -> {
            for (int i = 0; i < countLimit2; i++) {
                value.incrementAndGet();
            }
        });

        incrementor1.start();
        incrementor2.start();
        try {
            incrementor1.join();
            incrementor2.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return value.get();
    }
}
