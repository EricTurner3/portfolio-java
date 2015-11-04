package SpaceInvaders;

import javax.swing.*;
import java.awt.*;

/**
 * Created by turneeri001 on 3/12/14.
 */
public class Missile {

    private int x, y;
    private Image image;
    boolean visible = true;


    public Missile(int x, int y)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("Bullet.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
    }

    public Image getImage(){return image;}

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean isVisible(){return visible;}

    public void setVisible(boolean visible){this.visible = visible;}

    public void move()
    {

        x+=2;
        if(x>800)
            visible = false;
    }

    public Rectangle getBounds()
    {
        return new Rectangle (x,y,20,20);
    }


}
