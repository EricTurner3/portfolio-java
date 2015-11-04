package Entities;

import Main.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Eric on 5/12/15.
 */
public abstract class Entity {

    public Sprite standing = new Sprite();
    public Sprite walking = new Sprite();
    public Sprite jumping = new Sprite();

    public int x;
    public int origX;
    public int dx;
    public int y;
    public int origY;
    public int dy;
    public int height = 80;
    public int width = 80;


    public boolean isJumping = false;
    public boolean isRunning = false;
    public boolean isAlive;


    public Entity(){
        x = origX;
        y = origY;
        isAlive = true;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public Sprite setWalk(int frames)
    {
        walking.init(frames);
        return walking;
    }
    public Sprite loadWalk(int pos, String path)
    {
        walking.loadImage(pos, path);
        return walking;
    }
    public Sprite setJump(int frames)
    {
        standing.init(frames);
        return standing;
    }
    public Sprite loadJump(int pos, String path)
    {
        jumping.loadImage(pos, path);
        return jumping;
    }
    public Sprite setStand(int frames)
    {
        jumping.init(frames);
        return jumping;
    }
    public Sprite loadStand(int pos, String path)
    {
        standing.loadImage(pos, path);
        return standing;
    }


    //Used for Painting said object. Must be general so the other classes can extend (Player and Enemy)
    public void render(Graphics g2d){
        Graphics g = g2d;
        if(isRunning)
            walking.animate(g,height,width,x,y);
        else if(isJumping)
            jumping.animate(g,height,width,x,y);
        else
            standing.animate(g,height,width,x,y);

    }

    abstract void keyPressed(KeyEvent e);
}
