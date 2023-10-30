package edu.hw3;

enum Arabian {
    TEN(10),
    HUNDRED(100),
    THOUSAND(1000);
    private final int arabianNumber;

    Arabian(int arabianNumber) {
        this.arabianNumber = arabianNumber;
    }

    public int getValue() {
        return arabianNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(arabianNumber);
    }
}
