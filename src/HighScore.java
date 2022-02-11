
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class HighScore {

    // HighScore class contains the methods to establish a highscore file that can be read
    // and overwritten.

    // method to make file object highscore.txt
    public static File file() throws java.io.FileNotFoundException {
        File highscoretxt = new File("highscore.txt");
        return highscoretxt;
    }

    // uses file method to find the int stored in highscore.txt and set highscore variable to it in main
    public static int obtainHighScore() throws java.io.FileNotFoundException {
        Scanner input = new Scanner(file());
        return (input.nextInt());
    }

    // tracks whether or not the player is getting a higher score than the highscore.
    // called in 2 different methods in order to decide the display of score during the game,
    // and in order to decide the game over message.
    public static boolean scoreIsLarger() {
        return Game.score > Game.highscore;
    }

    // writes the new high score into the highscore.txt file if the boolean scoreIsLarger
    // is true at game over
    public static void overrideHighScoreFile() throws java.io.FileNotFoundException {
        PrintStream output = new PrintStream(file());
        output.println(Game.score);
    }


}
