
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class BadObject3 {
    //identical to BadObject class
    Random rand = new Random();

    private Image image;
    private Game game;
    private static final int DIAMETER = 30;

    int y = 0;
    int x = getRandX();
    int ya = getRandSpeed();
    int randomimage = getRandImage();

    public Image getImage(String path) {
        URL imageURL = BadObject3.class.getResource(path);
        Image tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        return tempImage;
    }

    public void paint(Graphics g) {
        image = getImage(randomimage + ".png");
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, y, DIAMETER, DIAMETER, game);
    }

    public BadObject3(Game game) {
        this.game = game;
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
            x = getRandX();
            randomimage = getRandImage();
            ya = getRandSpeed();
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
