package edu.hw4;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public class ValidationError {
    private final Animal object;
    private final String cause;

    private static final String NO_NAME_ERROR = "NO NAME DETECTED";
    private static final String NO_SEX_ERROR = "NO SEX DETECTED";
    private static final String NO_TYPE_ERROR = "NO TYPE DETECTED";
    private static final String VALUE_UNDER_ZERO = "%s VALUE IS BELOW ZERO: %d";
    private static final String VALUE_OVER_LIMITS = "%s VALUE IS OVER ANY SENSIBLE LIMITS: %d";

    public static final int MAX_AGE = 200;
    public static final int MAX_HEIGHT = 1500;
    public static final int MAX_WEIGHT = 13000;


    public ValidationError(Animal animal, String cause) {
        this.object = animal;
        this.cause = cause;
    }

    private static String formCause(ErrorStates state, InnerFields type, int interestingValue) {
        return switch (state) {
            case NO_SEX -> ERRORS_HANDLING.get(ErrorStates.NO_SEX);
            case NO_NAME -> ERRORS_HANDLING.get(ErrorStates.NO_NAME);
            case NO_TYPE -> ERRORS_HANDLING.get(ErrorStates.NO_TYPE);
            case VALUE_BELOW_ZERO -> String.format(
                ERRORS_HANDLING.get(ErrorStates.VALUE_BELOW_ZERO),
                type.toString(), interestingValue);
            case VALUE_OVER_SENSIBLE_LIMITS -> String.format(
                ERRORS_HANDLING.get(ErrorStates.VALUE_OVER_SENSIBLE_LIMITS),
                type.toString(), interestingValue);
        };
    }

    public static Set<ValidationError> checkError(Animal animal) {
        Set<ValidationError> errorSet = new HashSet<>();
        if (animal.name() == null || animal.name().equals("")) {
            errorSet.add(new ValidationError(animal, formCause(ErrorStates.NO_NAME, InnerFields.NAME, 0)));
        }
        if (animal.type() == null) {
            errorSet.add(new ValidationError(animal, formCause(ErrorStates.NO_TYPE, InnerFields.TYPE, 0)));
        }
        if (animal.sex() == null) {
            errorSet.add(new ValidationError(animal, formCause(ErrorStates.NO_SEX, InnerFields.SEX, 0)));
        }
        if (animal.age() < 0) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_BELOW_ZERO, InnerFields.AGE,
                animal.age())));
        }
        if (animal.age() > MAX_AGE) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_OVER_SENSIBLE_LIMITS,
                InnerFields.AGE, animal.age())));
        }
        if (animal.height() < 0) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_BELOW_ZERO, InnerFields.HEIGHT,
                animal.height())));
        }
        if (animal.height() > MAX_HEIGHT) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_OVER_SENSIBLE_LIMITS,
                InnerFields.HEIGHT,
                animal.height())));
        }
        if (animal.weight() < 0) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_BELOW_ZERO,
                InnerFields.WEIGHT,
                animal.weight())));
        }
        if (animal.weight() > MAX_WEIGHT) {
            errorSet.add(new ValidationError(animal, formCause(
                ErrorStates.VALUE_OVER_SENSIBLE_LIMITS,
                InnerFields.HEIGHT,
                animal.weight())));
        }
        return errorSet;
    }

    public static String setToString(Animal animal) {
        var errorSet = checkError(animal);
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 1;
        stringBuilder.append("ErrorList for %s %s:\n".formatted(animal.type(), animal.name()));
        for (var error: errorSet) {
            stringBuilder.append("Error #%d, Cause of error: %s".formatted(counter, error.cause));
        }
        return stringBuilder.toString();
    }

    private static final EnumMap<ErrorStates, String> ERRORS_HANDLING = new EnumMap<>(ErrorStates.class) {{
        put(ErrorStates.NO_NAME, NO_NAME_ERROR);
        put(ErrorStates.NO_SEX, NO_SEX_ERROR);
        put(ErrorStates.NO_TYPE, NO_TYPE_ERROR);
        put(ErrorStates.VALUE_BELOW_ZERO, VALUE_UNDER_ZERO);
        put(ErrorStates.VALUE_OVER_SENSIBLE_LIMITS, VALUE_OVER_LIMITS);
    }};

    @SuppressWarnings("unused")
    public Animal getObject() {
        return object;
    }

    @SuppressWarnings("unused")
    public String getCause() {
        return cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ValidationError validationError = (ValidationError) o;
        return this.cause.equals(validationError.cause);
    }

    @Override
    public int hashCode() {
        return this.cause.hashCode();
    }

    private enum ErrorStates {
        NO_NAME,
        NO_TYPE,
        NO_SEX,
        VALUE_BELOW_ZERO,
        VALUE_OVER_SENSIBLE_LIMITS,
    }

    private enum InnerFields {
        NAME,
        TYPE,
        SEX,
        AGE,
        HEIGHT,
        WEIGHT
    }
}
