package edu.hw5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RequestManager {
    private List<RequestHandler> handlers;

    public RequestManager() {
        buildChain();
    }

    private void buildChain() {
        handlers = new ArrayList<>(List.of(new SimpleWordHandler(), new AmericanDateHandler(),
            new EuropeanDateHandler(), new WordAndNumberHandler()));
    }

    public void makeRequest(Request rq) {
        handlers
            .stream()
            .sorted(Comparator.comparing(RequestHandler::getPriority))
            .filter(handler -> handler.canHandleRequest(rq))
            .findFirst()
            .ifPresent(handler -> handler.handle(rq));
        if (!rq.isHandled()) {
            rq.markHandled(null);
        }
    }
}
