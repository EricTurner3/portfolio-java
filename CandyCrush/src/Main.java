import javax.swing.*;

/**
 * Created by Eric Turner on 12/6/14.
 */
public class Main {

    static int gameHeight = 563; //Game Height is Set



    public static void createAndShowGUI() //Creates and Displays the GUI of the Game
    {
        Resources Resources = new Resources(); //Initialize the Resources Class
        Game game = new Game();                //Initialize the Game Class

        //Create and set up the window
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(new Dimension(gameWidth,gameHeight));
        frame.setIconImage(Resources.emerald.getImage());
        frame.setTitle("Treasure Gems (c) Eric Turner 2014");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        //Create and set up the content pane
        game.setOpaque(true);
        frame.setContentPane(game);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) //Runs the Game
    {
        createAndShowGUI();
    }
}
