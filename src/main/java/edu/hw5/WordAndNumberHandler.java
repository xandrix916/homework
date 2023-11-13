package edu.hw5;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAndNumberHandler implements RequestHandler {
    private static final String REGEX = "\\d{1,20}";
    private static final String NAME = "Word And Number Handler";
    @Override
    public boolean canHandleRequest(Request rq) {
        String[] parts = rq.getDateToHandle().split(" ");
        Matcher matcher = Pattern.compile(REGEX).matcher(rq.getDateToHandle());
        if (parts.length != 3 || matcher.matches()) {
            return false;
        }
        return (parts[1].equals("day") || parts[1].equals("days"))
            && (parts[2].equals("ago") || parts[2].equals("after"));
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void handle(Request rq) {
        String[] parts = rq.getDateToHandle().toLowerCase().split(" ");
        LocalDate localDate = switch (parts[2]) {
            case "ago" -> LocalDate.now().minusDays(Integer.parseInt(parts[0]) + 1);
            case "after" -> LocalDate.now().plusDays(Integer.parseInt(parts[0]) + 1);
            default -> null;
        };
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return NAME;
    }
}
