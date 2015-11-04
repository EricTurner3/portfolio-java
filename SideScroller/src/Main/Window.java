package Main;

import javax.swing.*;

/**
 * Created by Eric on 5/12/15.
 */
public class Window {


    static int height = 688;
    static int width = 1592;

    static JFrame frame = new JFrame();

    public static void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setTitle("Score: 0 | Health: 100");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    public static void gameGUI() {
        Game game = new Game();

        frame.add(game);
        frame.repaint();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setResizable(false);
        game.requestFocus();
    }

    public static void main(String[] args) //Runs the tk.EricTurner.Main.Game
    {
        createAndShowGUI();
        gameGUI();
    }

}
