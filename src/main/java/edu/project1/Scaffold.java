package edu.project1;

public class Scaffold {
    private final GameStatus gameStatus;
    private final static int DEFAULT_MAX_FRAMES = 5;
    private final String[] scaffoldFrames = new String[]{
                """
        <>===================П
        ||                   H
        <>===================U
        """,
            """
        []=============|
        ||
        ||
        ||
        ||
        ||
        ||
        ||
        ||
        <>===================П
        ||                   H
        <>===================U
        """,
                """
        []=============|
        ||             |
        ||             O
        ||
        ||
        ||
        ||
        ||
        ||
        <>===================П
        ||                   H
        <>===================U
        """,
                """
        []=============|
        ||             |
        ||             O
        ||           \\ i /
        ||            \\0/
        ||             H
        ||
        ||
        ||
        <>===================П
        ||                   H
        <>===================U
        """,
                """
        []=============|
        ||             |
        ||             O
        ||           \\ i /
        ||            \\0/
        ||             H
        ||            /
        ||           /
        ||
        <>===================П
        ||                   H
        <>===================U
        """,
                """
        []=============|
        ||             |
        ||             O
        ||           \\ i /
        ||            \\0/
        ||             H
        ||            / \\
        ||           /   \\
        ||
        <>===================П
        ||                   H
        <>===================U
        """
    };
    private int currentFrame;
    private int currentAttempts;

    public Scaffold(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        currentFrame = DEFAULT_MAX_FRAMES - gameStatus.getTotalAttempts() - 1;
        currentAttempts = gameStatus.getTotalAttempts();
    }

    public String getFrame() {
        if (currentAttempts > gameStatus.getAttemptsLeft()) {
            int difference = currentAttempts - gameStatus.getAttemptsLeft();
            currentAttempts -= difference;
            currentFrame += difference;
        }
        if (currentFrame < 0) {
            return "";
        }
        return scaffoldFrames[currentFrame];

    }
}
