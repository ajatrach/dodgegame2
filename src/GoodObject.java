
import java.awt.*;
import java.util.Random;
import java.net.URL;

public class GoodObject {

    // establish game, image, random object, a final size variable,
    // starting x position, y position, falling speed, and the skin
    // of the sprite.
    // x, ya, and skin of the sprite will be random and called by random
    // methods below.
    Random rand = new Random();

    private Image image;
    private Game game;
    private static final int DIAMETER = 30;

    int y = 0;
    int ya = getRandSpeed();
    int x = getRandX();
    int randomimage = getRandImage();

    public GoodObject(Game game) {
        this.game = game;
    }

    // set up method for finding an image
    public Image getImage() {

        Image tempImage = Toolkit.getDefaultToolkit().getImage(randomimage+".png");
        return tempImage;
    }

    // paint goodobject that is called by paint method in game
    public void paint(Graphics g) {
        image = getImage();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, y, DIAMETER, DIAMETER, game);
    }

    // move is called continuously by main.
    // if goodobject hits the bottom of the panel, set y back to 0 and obtain randoms (random x, ya,
    // and image/skin). If by chance this random x allows the good object to overlap the bad object,
    // it will choose a new random x to fall from in order to lessen the chance of a complete overlap.
    // if goodobject hits the character, the characters skin will stay or change to be good, a sound
    // effect will play, and the score will increase. y will also be 0 and randoms will be obtained.
    // if none of these events are triggered, y will continue to change by ya.
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

    // booleans for whenever a good object collides with a bad object. reduces the chance of a
    // bad object being hidden behind a good one
    private boolean badcollision() {
        return game.bad.getBounds().intersects(getBounds());
    }

    private boolean badcollision2() {
        return game.bad2.getBounds().intersects(getBounds());
    }

    private boolean badcollision3() {
        return game.bad3.getBounds().intersects(getBounds());
    }

    // receives the bounds of the character and detects a collision
    private boolean collision() {
        return game.character.getBounds().intersects(getBounds());
    }

    // records bounds of the good object to be used to detect collision
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    // a method will contain each random operation for a new x, skin, and speed ya.
    // all of these methods are contained in one "obtain randoms" method
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


