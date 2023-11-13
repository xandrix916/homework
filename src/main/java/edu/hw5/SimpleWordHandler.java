package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleWordHandler implements RequestHandler {

    private static final List<String> ALLOWED_COMMANDS = new ArrayList<>(List.of("tomorrow", "today", "yesterday"));

    private static final String NAME = "Simple Word Handler";

    @Override
    public boolean canHandleRequest(Request rq) {
        return ALLOWED_COMMANDS.contains(rq.getDateToHandle().toLowerCase());
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void handle(Request rq) {
        LocalDate localDate = switch (rq.getDateToHandle().toLowerCase()) {
            case "today" -> LocalDate.now();
            case "tomorrow" -> LocalDate.now().plusDays(1);
            case "yesterday" -> LocalDate.now().minusDays(1);
            default -> null;
        };
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return NAME;
    }
}
