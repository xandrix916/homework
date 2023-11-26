package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleWordHandler implements RequestHandler {

    private static final String TOMORROW = "tomorrow";
    private static final String TODAY = "today";
    private static final String YESTERDAY = "yesterday";

    private static final List<String> ALLOWED_COMMANDS = new ArrayList<>(List.of(TOMORROW, TODAY, YESTERDAY));

    private static final String HANDLER_NAME = "Simple Word Handler";

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
            case TODAY -> LocalDate.now();
            case TOMORROW -> LocalDate.now().plusDays(1);
            case YESTERDAY -> LocalDate.now().minusDays(1);
            default -> null;
        };
        rq.markHandled(localDate);
    }

    @Override
    public String name() {
        return HANDLER_NAME;
    }
}
