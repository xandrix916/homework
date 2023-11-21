package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;

public class Problem3 {
    Optional<LocalDate> parseDate(String string) {
        var requestManager = new RequestManager();
        Request rq = new Request(string);
        requestManager.makeRequest(rq);
        return rq.getResult();
    }
}
