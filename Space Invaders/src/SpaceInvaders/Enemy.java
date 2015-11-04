package SpaceInvaders;

import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * Created by turneeri001 on 3/12/14.
 */
public class Enemy
{
    private String enemy = "Enemy.png";
    private String explosionGif = "explosion.gif";

    private int x,y;
    private boolean death = false;
    public static double speed = 1;
    private boolean visible = true;
    private Image image;
    private Image explosionImage;


    public Enemy (int x, int y)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(enemy));
        image = ii.getImage();

        //ImageIcon ex = new ImageIcon(this.getClass().getResource(explosionGif));
        //explosionImage = ex.getImage();

        Toolkit t = Toolkit.getDefaultToolkit ();                            //Code for Creating Several Instances of a GIF
        explosionImage = t.createImage(this.getClass().getResource(explosionGif));


        death = false;
        visible = true;
        this.x = x+15;
        this.y = y+15;
    }

    public void setdeath(boolean death)
    {
        //this.visible=!death;
        this.death = death;
    }

    public boolean isDead()
    {
        return death;
    }
    public void setVisible (boolean visible)
    {
        this.visible = visible;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void move()
    {
        if(x<0)
            x = 800;
        x -= speed;
    }


    public Image explode()
    {

        return explosionImage;
    }

    public Image getImage()
    {
        return image;
    }


    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


    public Rectangle getBounds()
    {
        return new Rectangle (x+10,y+10,20,20);
    }
}
