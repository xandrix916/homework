package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;

public class Request {
    private final String dateToHandle;
    private LocalDate result;
    private boolean handled;

    public Request(final String dateToHandle) {
        this.dateToHandle = dateToHandle;
    }

    public String getDateToHandle() {
        return dateToHandle;
    }

    public void markHandled(LocalDate result) {
        this.handled = true;
        this.result = result;
    }

    public Optional<LocalDate> getResult() {
        if (!handled || result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public boolean isHandled() {
        return handled;
    }

    @Override
    public String toString() {
        return String.format("handle date %s", dateToHandle);
    }
}

