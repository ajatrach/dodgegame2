
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class BadObject {
    public BadObject(Game game) {
        this.game = game;
    }


    // Bad object class will be almost identical to good object class except:
    // -There is no badcollision detection, as that is controlled by the good objects
    // -A collision with character will decrease lives by 1, a bad sound will be played, and
    //  the character skin will change to sad.
    // -Random skin/image is a different range of ints for the different skins used by bad objects
    // -bad objects actively test for game over: if lives =0, call game over method
    Random rand = new Random();

    private Image image;
    private Game game;
    private static final int DIAMETER = 30;

    int y = 0;
    int ya = getRandSpeed();
    int x = getRandX();
    int randomimage = getRandImage();


    public Image getImage() {
        Image tempImage = Toolkit.getDefaultToolkit().getImage(randomimage+".png");
        return tempImage;
    }

    public void paint(Graphics g) {
        image = getImage();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, y, DIAMETER, DIAMETER, game);
    }

    void move() throws InterruptedException, java.io.FileNotFoundException {
        if (y + ya > game.getHeight() - DIAMETER) {
            y = 0;
            obtainRandoms();
        } else if (collision()) {
            Character.skinChangeSad();
            Sound.OUCH.play();
            game.lives--;
            if (game.lives == 0) {
                game.gameOver();
            }
            y = 0;
            obtainRandoms();
        }
        y = y + ya;
    }

    private boolean collision() {
        return game.character.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public int getRandImage() {
        randomimage = rand.nextInt(4) + 4;
        return randomimage;
    }

    public int getRandX() {
        x = rand.nextInt(400 - 2 * DIAMETER);
        return x;
    }

    public int getRandSpeed() {
        ya = rand.nextInt(3) + 1;
        return ya;
    }

    public void obtainRandoms() {
        x = getRandX();
        randomimage = getRandImage();
        ya = getRandSpeed();
    }

}
