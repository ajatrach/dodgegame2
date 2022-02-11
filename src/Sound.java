
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    //Sound methods are called during collisions with character and game over

    public static final AudioClip COINS = Applet.newAudioClip(Sound.class.getResource("coins.wav"));
    //coins sound effect "Coins 1" by ProjectsU012 via freesound.org
    // Work is licensed under Creative Commons Attribution License

    public static final AudioClip OUCH = Applet.newAudioClip(Sound.class.getResource("ouch.wav"));
    //ouch sound effect "Retro video game sfx - Ouch" by OwlStorm via freesound.org
    // Work is licensed under Creative Commons 0 License (no copywrite)

    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
    //gameover sound effect "8-bit Game Over Sound/Tune" by EVRetro via freesound.org
    // Work is licensed under Creative Commons 0 License (no copywrite)

    public static final AudioClip MUSIC = Applet.newAudioClip(Sound.class.getResource("music.wav"));
    //game music "Retro arcade music #3" by BloodPixelHero via freesound.org
    // Work is licensed under Creative Commons Attribution License
}
