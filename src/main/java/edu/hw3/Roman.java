package edu.hw3;

enum Roman {
    ZERO(""),
    ONE("I"),
    FIVE("V"),
    TEN("X"),
    FIFTY("L"),
    HUNDRED("C"),
    FIVE_HUNDRED("D"),
    THOUSAND("M");
    private final String romanNumber;

    Roman(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    @Override
    public String toString() {
        return this.romanNumber;
    }
}
