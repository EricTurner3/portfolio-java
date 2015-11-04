package Main;

import Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Eric on 5/12/15.
 */
public class Game extends JPanel implements ActionListener  {

    Timer timer;
    AssetManager assets = new AssetManager();
    Player player = new Player();

    //Main.Game Constants
    static int width = Window.width;
    static int height = Window.height-28; //Height minus the Windows title bar gives us the inside panel height
    static int ground = height-80;

    //FPS
    long startTime;
    double fps;

    //Misc
    JButton restart = new JButton("Restart?");
    JButton exit = new JButton("Exit Main.Game");

    // -----------------Main.Game States-----------------------------
    static enum GameState {
        INITIALIZED, PLAYING, PAUSED, GAMEOVER, DESTROYED
    }

    static GameState state;   // current state of the game
    // ---------------------------------------------------------

    //Code to Initialize Main.Game
    public void init(){
        System.out.println("Main.Game Initialized");
        addKeyListener(new gameKeyPressed());
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        setLayout(null);
        timer = new Timer(1000/60, this); //60 Frames per 1000 milliseconds (1 Second)
        Window.frame.setTitle("Welcome to Valkyrie Runner!");

        state = GameState.INITIALIZED;
    }

    //Block used to Set/Reset Main.Game to initial playing phase
    public void gameStart() {
        if (state == GameState.INITIALIZED || state == GameState.GAMEOVER || state == GameState.PAUSED) {
            //Main.Game State
            state = GameState.PLAYING;
            System.out.println("Main.Game Start/Restart");
            //Stop Main.Game and Reset objects to orig position
            timer.stop();
            Window.frame.setTitle("Valkyrie Runner");
            restart.removeActionListener(new restartListener());
            remove(restart);

            //Restart Main.Game
            timer.start();
            requestFocusInWindow();
        }
    }



    public Game()
    {
        init();

    }


    //--------------------------------------------------------
    // Main.Game Key Processing
    //--------------------------------------------------------
    private class gameKeyPressed implements KeyListener {

        public void keyTyped(KeyEvent e) {
            //player.keyTyped(e);
        }


        public void keyReleased(KeyEvent e) {
            switch (state) {
                case PLAYING:

                    break;
            }
        }

        public void keyPressed(KeyEvent e) {

            switch (state) {
                case INITIALIZED:
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        gameStart();
                    }
                    break;
                case PLAYING:
                        player.keyPressed(e);
                    break;
                case PAUSED:
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        state = GameState.PLAYING;
                    }
                    break;
                case GAMEOVER:
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        gameStart();
                    }
                    break;
            }
        }
    }
    //--------------------------------------------------------

    //------------------------------------------------------------------------------------
    // Pause and Main.Game Over Screen Button Listeners
    //------------------------------------------------------------------------------------
    public class restartListener implements ActionListener //Restart Button listener
    {
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Restart Button Clicked");
            gameStart();
        }
    }

    public class exitListener implements ActionListener //Exit Button Listener
    {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    //-----------------------------------------------------------------------------------

    //Render the Main.Game, load all the render/pain methods of the other classes
    public void paintComponent(Graphics g2d){
        super.paintComponent(g2d);
        switch(state)
        {
            case INITIALIZED:
                Image start = (new ImageIcon(this.getClass().getResource(assets.splashScreen))).getImage();
                g2d.drawImage(start, 0, 0, width, height, this);
                break;
            case PLAYING:
                render(g2d);
                break;
            case PAUSED:
                Image pse = (new ImageIcon(this.getClass().getResource(assets.pauseScreen))).getImage();
                g2d.drawImage(pse, 0, 0, width, height, this);
                break;
            case GAMEOVER:
                //System.out.println("Main.Game Over");
                Image fail = (new ImageIcon(this.getClass().getResource(assets.gameOverScreen))).getImage();
                g2d.drawImage(fail, 0, 0, width, height, this);
                break;
            case DESTROYED:
                break;
        }

    }

    public void render(Graphics g2d)
    {
        Graphics g = g2d;
        Image fail = (new ImageIcon(this.getClass().getResource(assets.gameOverScreen))).getImage();
        g.drawImage(fail, 0, 0, width, height, this);
        player.render(g);
        /*Code Used While Main.Game is in Action
        player.render(g);
        enemy.render(g);
        for(int i = 0; i<collectibleNum; i++
        {
            collectible[i].render(g);
        }
        */

    }

    public void actionPerformed(ActionEvent e)
    {
        //ToDO Main.Game Brain, Handles things like objects moving
        switch(state) {
            case INITIALIZED:
                break;
            case PLAYING:
                startTime = System.currentTimeMillis();
                //----------------------
                checkCollisions();
                //----------------------
                fps = (int)((System.currentTimeMillis() - startTime / 1000000000)/1000/(1000/60));
                System.out.println("FPS: " + fps);
                break;
        }



    }

    public void checkCollisions()
    {
        //ToDo Check for Objects Colliding. Add simple collision method in entities/objects classes
    }

}
