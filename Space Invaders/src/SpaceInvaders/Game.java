package SpaceInvaders;

import com.sun.org.apache.regexp.internal.recompile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class Game
{

    static int height = 600;
    public static int width = 800;
    static Board game;
    public static int pauseN = 0;
    static JButton play = new JButton();
    static JButton exit = new JButton();
    static JButton resume = new JButton();
    static JFrame frame = new JFrame();
    static JLabel background = new JLabel(Resources.backgroundImg);


    static JPanel panel = new JPanel(new BorderLayout());
    static JPanel pause = new JPanel(new BorderLayout());



    public static JPanel Buttons()
    {
        play.setSize(50,height);
        play.setText("Play");
        play.addActionListener(new playButtonListener());

        exit.setSize(50, height);
        exit.setText("Exit");
        exit.addActionListener(new exitButtonListener());

        panel.add(background,BorderLayout.CENTER);
        panel.add(play,BorderLayout.WEST);
        panel.add(exit,BorderLayout.EAST);

        return panel;
    }
    public static JPanel PauseMenu()
    {
        pause.setOpaque(false);
        resume.setSize(width,50);
        resume.setText("Resume");
        resume.addActionListener(new resumeButtonListener());

        exit.setSize(width, 50);
        exit.setText("Exit");
        exit.addActionListener(new exitButtonListener());
        pause.add(play,BorderLayout.NORTH);
        pause.add(exit, BorderLayout.SOUTH);
        return pause;
    }

    public static void Pause()
    {
        frame.add(PauseMenu());
        pause.setVisible(true);
        pauseN = 1;
    }
    public static void Resume()
    {
        frame.remove(PauseMenu());
        pause.setVisible(false);
        pauseN = 0;
    }


    public static void main(String[] args)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Buttons());
        pause.setVisible(false);
        frame.requestFocusInWindow();
        frame.setSize(width, height);
        frame.setTitle("Space Invaders (c) Eric Turner 2014");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon(Resources.class.getResource("icon.png")).getImage());
    }


    public static class playButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            game = new Board();
            if (panel.isVisible())
            {
                frame.remove(game);
            }
            panel.setVisible(false);
            panel.setFocusable(false);
            Resources.background.loop();

            frame.add(game);
            game.requestFocus();
        }
    }
    public static class exitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    public static class resumeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            pause.setVisible(false);
            game.timer.start();
        }
    }
}
