
import java.awt.*;
import java.net.URL;
import java.util.Random;


public class GoodObject2 {
    // Identical to GoodObject class
    Random rand = new Random();

    private static final int DIAMETER = 30;
    private Image image;
    private Game game;

    int y = 0;
    int x = getRandX();
    int ya = getRandSpeed();
    int randomimage = getRandImage();

    public GoodObject2(Game game) {
        this.game = game;
    }

    public Image getImage(String path) {
        URL imageURL = GoodObject2.class.getResource(path);
        Image tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        return tempImage;
    }

    public void paint(Graphics g) {
        image = getImage(randomimage + ".png");
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, y, DIAMETER, DIAMETER, game);
    }

    void move() {
        if (y + ya > game.getHeight() - DIAMETER) {
            y = 0;
            obtainRandoms();
            if (badcollision() || badcollision2() || badcollision3()) {
                y = 0;
                getRandX();
            }
        } else if (collision()) {
            Sound.COINS.play();
            Character.skinChangeNorm();
            game.score++;
            y = 0;
            obtainRandoms();
            if (badcollision() || badcollision2() || badcollision3()) {
                y = 0;
                getRandX();
            }
        }
        y = y + ya;
    }

    private boolean badcollision() {
        return game.bad.getBounds().intersects(getBounds());
    }

    private boolean badcollision2() {
        return game.bad2.getBounds().intersects(getBounds());
    }

    private boolean badcollision3() {
        return game.bad3.getBounds().intersects(getBounds());
    }

    private boolean collision() {
        return game.character.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public int getRandImage() {
        randomimage = rand.nextInt(3) + 1;
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


