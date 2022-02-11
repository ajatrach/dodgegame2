
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Character {

    // establish game and image objects, establish final variables for
    // location and size of character, establish changing variables of x position,
    // movement, and character skin
    private Game game;
    private Image image;
    private static final int Y = 390;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 70;

    int x = 0;
    int xa = 0;
    static String skin = "character.png";

    public Character(Game game) {
        this.game = game;
    }

    // set up method for finding an image
    public Image getImage(String path) {

        URL imageURL = Character.class.getResource(path);
        Image tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        return tempImage;
    }

    // paint character that is called by paint method in game
    public void paint(Graphics g) {
        image = getImage(skin);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, Y, WIDTH, HEIGHT, game);
    }

    // method to change skin to sad called by bad object collision and method
    // to change skin to happy/normal called by good object collision
    public static void skinChangeSad() {
        skin = "charactersad.png";
    }

    public static void skinChangeNorm() {
        skin = "character.png";
    }

    // move character that is called by main, xa is altered through key pressing
    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
            x = x + xa;
        }
    }

    // when key not pressed, x movement is 0. when pressed, x movement is 2
    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -2;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 2;
        }
    }

    //determine the bounds of character for collisions
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

}
