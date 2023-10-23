package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Problem6Test {
    @Test
    void exampleTest() {
        Problem6 problem6 = new Problem6();
        String response = problem6.exchangeExample();
        Assertions.assertEquals("""
            GAZP stock added. Current price is 170,510000
            VTBR stock added. Current price is 0,026090
            Most valuable stock is GAZP, its price is 170,510000
            SBER stock added. Current price is 271,540000
            Most valuable stock is SBER, its price is 271,540000
            YNDX stock added. Current price is 2674,400000
            SBER price change. Current price is 271,460000
            TCSG stock added. Current price is 3490,500000
            Most valuable stock is TCSG, its price is 3490,500000
            #1 TCSG 3490,500000₽
            #2 YNDX 2674,400000₽
            #3 SBER 271,460000₽
            #4 GAZP 170,510000₽
            #5 VTBR 0,026090₽
            """, response);
    }

}
