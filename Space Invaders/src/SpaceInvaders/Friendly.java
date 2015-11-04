package SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by turneeri001 on 3/12/14.
 */
public class Friendly {

    private String friendly = "MainChar.png";

    private int x, y, dx, dy, height, width;
    private Image image;
    private boolean visible = true;
    private ArrayList missile;
    //private Resources resources = new Resources();

    public Friendly(){

        ImageIcon ii = new ImageIcon((this.getClass().getResource(friendly)));
        image = ii.getImage();
        missile = new ArrayList();
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 40;
        y = Game.height/2;
    }

    public void move()
    {

        if(x < 0)
            x=1;
        else if (x > 50)
            x=49;
        else if(y<100)
            y=99;

        else if (y>535)
            y=534;

         x += dx;
         y += dy;

    }

    public void reset()
    {
        x = 40;
        y = Game.height/2;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImage(){
        return image;
    }

    public void setVisible (boolean visible)
    {
        this.visible = visible;
    }


    public boolean isVisible()
    {
        return visible;
    }



    public ArrayList getMissile(){return missile;}

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT )
            dx = -2;
        if(key == KeyEvent.VK_RIGHT)
            dx = 2;
        if(key == KeyEvent.VK_UP)
            dy = -2;
        if(key == KeyEvent.VK_DOWN)
            dy = 2;
        /*if(key == KeyEvent.VK_ESCAPE)
        {
            if(Game.pauseN == 1)
            {
                System.out.println("Escape Pressed (Resume)");
                Game.game.timer.start();
                Game.Resume();
                Game.game.repaint();
            }
            if(Game.pauseN == 0)
            {
                System.out.println("Escape Pressed (Pause)");
                Game.game.timer.stop();
                Game.Pause();
                Game.game.repaint();
            }
        }*/

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT)
            dx = 0;
        if(key == KeyEvent.VK_RIGHT)
            dx = 0;
        if(key == KeyEvent.VK_UP)
            dy = 0;
        if(key == KeyEvent.VK_DOWN)
            dy = 0;

        if(key == KeyEvent.VK_SPACE)
        {
            fire();
            Resources.Laser.play();
            Board.bullets--;
            Board.Bullets.setText("Bullets: " + Board.bullets + "                                                                                                                ");
        }

    }

    public void fire()
    {
        missile.add(new Missile(getX(), getY()));

    }

    public Rectangle getBounds()
    {
        return new Rectangle (x+10,y+10,20,20);
    }
}