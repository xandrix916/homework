package edu.hw5;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EuropeanDateHandler implements RequestHandler {

    private static final String MATCH_STRING = "\\d/\\d/\\d{2,4}";
    private static final String HANDLER_NAME = "European Date Handler";
    private static final int PRIORITY = 3;
    private static final int SHORT_YEAR_FORMAT_LEN = 2;
    private static final int FULL_YEAR_FORMAT_LEN = 4;


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
        String[] date = rq.getDateToHandle().split("/");
        date[2] = switch (date[2].length()) {
            case SHORT_YEAR_FORMAT_LEN -> "20" + date[2];
            case FULL_YEAR_FORMAT_LEN -> date[2];
            default -> "";
        };
        if (date[2].isEmpty()) {
            rq.markHandled(null);
        }
        LocalDate localDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
            Integer.parseInt(date[0]));
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return HANDLER_NAME;
    }
}
