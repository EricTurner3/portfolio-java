package Entities;

import Entities.Entity;
import Main.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Eric on 5/12/15.
 */
public class Player extends Entity {

    public Player(){
        //Walking Animation Setup
        super.setWalk(2);
        super.loadWalk(1, "assets/Animations/player-walking/000.gif");
        super.loadWalk(2, "assets/Animations/player-walking/001.gif");
        //Standing Animation Setup
        super.setStand(5);
        super.loadStand(1, "path");
        //Jumping Animation Setup
        super.setJump(5);
        super.loadJump(1, "path");
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isJumping = true;
            final int jumph = 4;
            final int steps = 6;

            for (int i = 0; i < steps; i++) {
                dy = (-1 * i * i + jumph * i);
            }

        }
    }
}
