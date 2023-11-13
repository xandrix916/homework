package edu.hw5;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmericanDateHandler implements RequestHandler {
    private static final String MATCH_STRING = "\\d{4}-\\d{1,2}-\\d{1,2}";
    private static final String NAME = "American Date Handler";
    private static final int PRIORITY = 4;

    @Override
    public boolean canHandleRequest(Request rq) {
        Pattern pattern = Pattern.compile(MATCH_STRING);
        Matcher matcher = pattern.matcher(rq.getDateToHandle());
        return matcher.matches();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public void handle(Request rq) {
        String[] date = rq.getDateToHandle().split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
            Integer.parseInt(date[2]));
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return NAME;
    }
}
