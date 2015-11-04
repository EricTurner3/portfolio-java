package Main;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by turneeri001 on 5/14/2015.
 */
public class AssetManager
{
    String pauseScreen;
    String gameOverScreen;
    String splashScreen;

    AudioClip intro;

    public AssetManager()
    {
        try
        {
            intro = Applet.newAudioClip(this.getClass().getResource("/assets/Audio/Carefree.wav"));
            pauseScreen = "assets/Screens/PauseScreen.gif";
            gameOverScreen ="assets/Screens/GameOverScreen.gif";
            splashScreen = "assets/Screens/SplashScreen.gif";
        }
        catch(Exception localException){}
    }

}
