package edu.hw5;

public interface RequestHandler {
    boolean canHandleRequest(Request rq);

    int getPriority();

    void handle(Request rq);

    String name();
}
