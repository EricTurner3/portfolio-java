package Main;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

/**
 * Created by turneeri001 on 5/15/2015.
 */
public class Sprite {
    Image[] images;
    int[] heights;
    int[] widths;
    int[] x;
    int[] y;

    public void init(int pictures) //How Many total frames are in said animation
    {
        int total = pictures - 1;
         images = new Image[total];
    }

    public void loadImage(int frame, String imageLocation) //Manually (or by loop) load all images by image file location
    {
        Image image = (new ImageIcon(this.getClass().getResource(imageLocation))).getImage();
        images[frame-1] = image; //Because 1 is normally assumed as start not 0, account for that difference
    }

    public void animate(Graphics g2d, int height, int width, int x, int y)
    {
        Graphics g = g2d;

        for(int i = 0; i<images.length; i++)
        {
            g.drawImage(images[i],x,y,width,height,null);
            if (i == images.length)
                i = 0;
        }


    }
}
