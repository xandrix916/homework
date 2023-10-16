package edu.project1;

public final class Responses {
    private Responses() {}

    public static final String RESPONSE_1 =
        """
           \nTURN #1
           ANSWER TO FIRST CHOICE: 1
           ANSWER TO SECOND CHOICE:\s
           INPUT: e
           ANSWER TO GIVE UP CHOICE:\s
           INPUT OPTION: TEST TURNS
           RECEIVED CORRECT MESSAGE: 1
           PLAYER PICKED: GUESS LETTER
           ONE LETTER OPENED
           ANOTHER LETTER USED: e

           TURN #2
           ANSWER TO FIRST CHOICE: 1
           ANSWER TO SECOND CHOICE:\s
           INPUT: r
           ANSWER TO GIVE UP CHOICE:\s
           INPUT OPTION: TEST TURNS
           RECEIVED CORRECT MESSAGE: 1
           PLAYER PICKED: GUESS LETTER
           ONE LETTER OPENED
           ANOTHER LETTER USED: r

           TURN #3
           ANSWER TO FIRST CHOICE: 2
           ANSWER TO SECOND CHOICE:\s
           INPUT: red
           ANSWER TO GIVE UP CHOICE:\s
           INPUT OPTION: TEST TURNS
           RECEIVED CORRECT MESSAGE: 2
           PLAYER PICKED: GUESS WORD

           WIN: WORD IS FULLY OPENED
           """;
    public static final String RESPONSE_2 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: d
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: d

            TURN #2
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: e
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: e

            TURN #3
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: h
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: h

            TURN #4
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: t
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: t

            TURN #5
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: death
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD
            WRONG GUESS: -2 ATTEMPTS 3 ATTEMPTS LEFT

            TURN #6
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: depth
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD

            WIN: WORD IS FULLY OPENED
            """;

    public static final String RESPONSE_3 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: e
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: e

            TURN #2
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: c
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS 4 ATTEMPTS LEFT
            WRONG LETTER USED: c

            TURN #3
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: i
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS 3 ATTEMPTS LEFT
            WRONG LETTER USED: i

            TURN #4
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: o
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: o

            TURN #5
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: y
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS 2 ATTEMPTS LEFT
            WRONG LETTER USED: y

            TURN #6
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: u
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS 1 ATTEMPTS LEFT
            WRONG LETTER USED: u

            TURN #7
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: x
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS 0 ATTEMPTS LEFT
            WRONG LETTER USED: x

            TURN #8
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: p
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            WRONG GUESS: -1 ATTEMPTS -1 ATTEMPTS LEFT
            WRONG LETTER USED: p

            FAIL: NO ATTEMPTS LEFT
            """;
    public static final String RESPONSE_4 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: 40
            ANSWER TO SECOND CHOICE: 42
            INPUT:\s
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED INCORRECT OPTION
            PLAYER IGNORES ASK TO INPUT 1 AND RAGE QUITS
            """;
    public static final String RESPONSE_5 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: abubi
            ANSWER TO SECOND CHOICE: ehfjejfhej
            INPUT:\s
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED MISMATCH
            PLAYER IGNORES ASK TO INPUT 1 AND LEAVES GAME
            """;
    public static final String RESPONSE_6 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: y
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: y

            TURN #2
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: e
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            2 LETTERS OPENED
            ANOTHER LETTER USED: e

            TURN #3
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: r
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            2 LETTERS OPENED
            ANOTHER LETTER USED: r

            TURN #4
            ANSWER TO FIRST CHOICE: 1
            ANSWER TO SECOND CHOICE:\s
            INPUT: i
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 1
            PLAYER PICKED: GUESS LETTER
            ONE LETTER OPENED
            ANOTHER LETTER USED: i

            TURN #5
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: ty[ewriter
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD
            INPUT FAILURE: NO FINES

            TURN #6
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: typ3writ3r
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD
            INPUT FAILURE: -%d ATTEMPTS %d ATTEMPTS LEFT

            TURN #7
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: type24iter
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD
            INPUT FAILURE: -%d ATTEMPTS %d ATTEMPTS LEFT

            TURN #8
            ANSWER TO FIRST CHOICE: 2
            ANSWER TO SECOND CHOICE:\s
            INPUT: damn it
            ANSWER TO GIVE UP CHOICE:\s
            INPUT OPTION: TEST TURNS
            RECEIVED CORRECT MESSAGE: 2
            PLAYER PICKED: GUESS WORD
            INPUT FAILURE: -%d ATTEMPTS %d ATTEMPTS LEFT

            FAIL: NO ATTEMPTS LEFT
            """;

    public static final String RESPONSE_7 =
        """

            TURN #1
            ANSWER TO FIRST CHOICE: 3
            ANSWER TO SECOND CHOICE:\s
            INPUT:\s
            ANSWER TO GIVE UP CHOICE: yes
            INPUT OPTION: TEST TURNS
            PLAYER DECIDED TO GIVE UP
            PLAYER REALLY GIVES UP AND QUITS THE GAME
            """;
}
