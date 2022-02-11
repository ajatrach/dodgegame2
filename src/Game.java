import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    //  Establish each class controlled by Game as an object
    //  so they can be called within main
    Character character = new Character(this);
    BadObject bad = new BadObject(this);
    BadObject2 bad2 = new BadObject2(this);
    BadObject3 bad3 = new BadObject3(this);
    GoodObject good = new GoodObject(this);
    GoodObject2 good2 = new GoodObject2(this);

    // Establish important variables
    int lives = 3;
    static int score = 0;
    static int highscore;

    //method receives key pressing events, then calls character's movement methods
    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                character.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                character.keyPressed(e);
            }
        });
        setFocusable(true);
        Sound.MUSIC.loop();
    }

    // move is called by main, with move calling each movement method that
    // belongs to other objects in the game
    private void move() throws InterruptedException, java.io.FileNotFoundException {
        character.move();
        bad.move();
        bad2.move();
        bad3.move();
        good.move();
        good2.move();
    }

    // paint is called by main, in which it establishes a graphics object,
    // calls each paint method for every object, and draws the scores and lives.
    // If the score is higher than the highscore, the highscore drawing wil turn
    // red and display the current score.
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        character.paint(g2d);
        bad.paint(g2d);
        bad2.paint(g2d);
        bad3.paint(g2d);
        good.paint(g2d);
        good2.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Courier", Font.BOLD, 20));
        g2d.drawString("Lives: " + String.valueOf(lives), 280, 30);
        g2d.drawString("Score: " + String.valueOf(score), 10, 50);
        if (HighScore.scoreIsLarger()) {
            g2d.setColor(Color.RED);
            g2d.drawString("High Score: " + String.valueOf(score), 10, 30);
        } else {
            g2d.drawString("High Score: " + String.valueOf(highscore), 10, 30);
        }
    }

    // Game over method is called by bad objects when lives=0. If the player gets a highscore, the override
    // method is called, and a different message will be displayed on the game over screen.
    public void gameOver() throws InterruptedException, java.io.FileNotFoundException {
        Sound.GAMEOVER.play();
        if (HighScore.scoreIsLarger()) {
            HighScore.overrideHighScoreFile();
            JOptionPane.showMessageDialog(this, "Congrats! New high score is: " + score,
                    "Game Over", JOptionPane.YES_NO_OPTION);

        } else {
            JOptionPane.showMessageDialog(this, "Your score is: " + score,
                    "Game Over", JOptionPane.YES_NO_OPTION);
        }
        System.exit(ABORT);
    }

    // main establishes the frame that the game takes place in and the game object that
    // allows variables in game to be used by other classes.
    // a while loop that is always true paints and moves the objects continuously while
    // pausing for 10 milliseconds each instance
    public static void main(String[] args) throws InterruptedException,
            LineUnavailableException, UnsupportedAudioFileException, IOException {
        highscore = HighScore.obtainHighScore();
        JFrame frame = new JFrame("Dodge");
        Game game = new Game();
        frame.add(game);
        frame.setSize(400, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.repaint();
            game.move();
            Thread.sleep(10);
        }
    }

}