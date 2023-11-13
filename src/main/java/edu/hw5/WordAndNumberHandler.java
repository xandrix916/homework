package edu.hw5;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAndNumberHandler implements RequestHandler {

    private static final int REQUIRED_LEN = 3;
    private static final String REGEX = "\\d{1,20}";
    private static final String NAME = "Word And Number Handler";
    private static final String AGO = "ago";
    private static final String AFTER = "after";

    @Override
    public boolean canHandleRequest(Request rq) {
        String[] parts = rq.getDateToHandle().split(" ");
        Matcher matcher = Pattern.compile(REGEX).matcher(rq.getDateToHandle());
        if (parts.length != REQUIRED_LEN || matcher.matches()) {
            return false;
        }
        return (parts[1].equalsIgnoreCase("day") || parts[1].equalsIgnoreCase("days"))
            && (parts[2].equalsIgnoreCase(AGO) || parts[2].equalsIgnoreCase(AFTER));
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void handle(Request rq) {
        String[] parts = rq.getDateToHandle().toLowerCase().split(" ");
        LocalDate localDate = switch (parts[2]) {
            case AGO -> LocalDate.now().minusDays(Integer.parseInt(parts[0]) + 1);
            case AFTER -> LocalDate.now().plusDays(Integer.parseInt(parts[0]) + 1);
            default -> null;
        };
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return NAME;
    }
}
