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

    public void makeRequest(Request request) {
        var firstHandler = handlers
            .stream()
            .sorted(Comparator.comparing(RequestHandler::getPriority))
            .filter(handler -> handler.canHandleRequest(request))
            .findFirst();
        firstHandler
            .ifPresent(handler -> handler.handle(request));

        if (!request.isHandled()) {
            request.markHandled(null);
        }
    }
}
