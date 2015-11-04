package SpaceInvaders;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by turneeri001 on 3/18/14.
 */
public class Resources
{

    private boolean visible = true;


    public static final AudioClip background = Applet.newAudioClip(Resources.class.getResource("backgroundMusic.wav"));
    public static final AudioClip Laser = Applet.newAudioClip(Resources.class.getResource("LaserSound.wav"));
    public static final AudioClip enemyHit = Applet.newAudioClip(Resources.class.getResource("destroy.wav"));
    public static final AudioClip levelUp = Applet.newAudioClip(Resources.class.getResource("levelUp.wav"));
    public static final ImageIcon backgroundImg = new ImageIcon("background.png");

    public void setVisible (boolean visible)
    {
        this.visible = visible;
    }


}
