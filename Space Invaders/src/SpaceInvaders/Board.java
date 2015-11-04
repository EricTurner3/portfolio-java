package SpaceInvaders;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class Board extends JPanel implements ActionListener{

    public Timer timer;
    private Friendly friendly;
    private Enemy enemy;
    JLabel score = new JLabel();
    JLabel Level = new JLabel();
    JProgressBar health = new JProgressBar(0,100);


    JProgressBar ehealth = new JProgressBar(0,100);
    int hitPoints = 2;
    int hit;
    int enHealth;
    int newHealth;
    int Health=100;

    private Image image;
    public static JLabel Bullets = new JLabel();
    private ArrayList enemies;
    private int deathX;
    private int deathY;
    int Score = 0;
    int level = 1;
    public static int bullets = 30;

    private Font myFont = new Font("Cooper Black", Font.BOLD, 20);


    ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("background.jpg"));
    Image img = imgIcon.getImage();

    ImageIcon fireimgIcon = new ImageIcon(this.getClass().getResource("fireZone.png"));
    Image fireZone = fireimgIcon.getImage();


    Color newColor = new Color(102,102,102);


//Second Number Max 500
   private int[][] position =
    {
       {1000,100}, {1450,210},{1350,270}, {1025, 300}, {1500,350},{2000,400},{1900,450},{1800,500}, {1300,200}, {1200,250}, {1100,150}, {1700,130},{1600,170},
    };


    public Board()
    {

        addKeyListener(new Listener());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        score.setText("Score: " + Score + "                                                                              ");
        score.setForeground(Color.CYAN);
        score.setFont(myFont);

        Level.setText("Level: " + level + "                                                                              ");
        Level.setForeground(Color.RED);
        Level.setFont(myFont);


        Bullets.setText("Bullets " + bullets + "                                                                              ");
        Bullets.setForeground(Color.YELLOW);
        Bullets.setFont(myFont);

        friendly = new Friendly();
        initEnemies();
        timer = new Timer(5, this);
        timer.start();



        add(Level);
        add(score);
        add(Bullets);
        add(health);
        add(ehealth);
        health.setValue(100);
        health.setValue(100);

        setBackground(newColor);           //Top Part Color
        System.out.println("Initialized with " + hitPoints + " hitpoints");
    }
    public void initEnemies()
    {
        enHealth=100;
        enemies = new ArrayList();




        for(int i = 0; i<position.length; i++)
        {
            enemies.add(new Enemy(position[i][0], position [i][1]));
            ehealth.setValue(enHealth);
        }

    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img,0,100,Game.width,Game.height-100,null);
        g2d.drawImage(fireZone,0,100,95,Game.height,null);

        g.setColor(Color.CYAN);
        g2d.drawLine(0, 100, Game.width, 100);
        g2d.drawLine(0,0,Game.width,0);
        g.setColor(Color.YELLOW);
        g2d.drawLine(95,100,95,Game.height);

        ArrayList ms = friendly.getMissile();


        (int i = 0; i < ms.size(); i++)
        {
            Missile m = (Missile)ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), 50,50, this);
        }
        for (int i=0; i<enemies.size(); i++)
        {
            Enemy e = (Enemy)enemies.get(i);


            if(e.isDead())
            {
        for
                g2d.drawImage(e.explode(), e.getX(), e.getY(), 45, 45, this);

                //g2d.dispose();
                e.setVisible(false);
                //repaint();

            }

            if(!e.isDead())
                g2d.drawImage(e.getImage(), e.getX(),e.getY(), 45,45, this);



        }
        if(friendly.isVisible())
        {
            g2d.drawImage(friendly.getImage(), friendly.getX(), friendly.getY(), 40, 40, this);
        }


    }

    public void actionPerformed(ActionEvent e)
    {

        ArrayList ms = friendly.getMissile();

            for(int i = 0; i < ms.size(); i++)
            {
                Missile m = (Missile)ms.get(i);
                    if(m.isVisible())
                        m.move();
                    else
                        ms.remove(i);
            }

        for(int i = 0; i < enemies.size(); i++)
        {
            Enemy en  = (Enemy)enemies.get(i);
            if(en.isVisible())
                en.move();
            else
            {
                enemies.remove(i);
            }
        }

        if( enemies.size()==0)                 //End of Wave Code
        {
            initEnemies();
            newHealth=100;
            Score+=50;
            score.setText("Score: " + Score + "                                                                          ");
            level++;
            Enemy.speed+=0.4;
            //Game.width-=20;
            for (int i = 0; i <5; i++)
            {
                bullets++;
                Bullets.setText("Bullets: " + bullets + "                                                                                                                ");
            }
            Level.setText("Level: " + level + "                                                                                                                ");
            Resources.levelUp.play();
        }
        if( level==11)                 //End of Level 10

        {

            JOptionPane.showMessageDialog(this,
                    "You Win! You beat Level 10!!!",
                    "Game Over",
                    JOptionPane.ERROR_MESSAGE);
            Game.panel.setVisible(true);
            this.stop();
            Resources.background.stop();
        }



        ehealth.setValue(newHealth);
        friendly.move();
        checkCollisions();
        repaint();
    }

    public void checkCollisions()
    {
        Rectangle player = friendly.getBounds();

        for (int i =0; i <enemies.size(); i++)
        {
            Enemy e =(Enemy)enemies.get(i);
            Rectangle enemy = e.getBounds();

            if(player.intersects(enemy))
            {
                System.out.println(hitPoints + " hitpoints are left at  hit ");
                hitPoints-=1;
                hit = 50;
                Health=Health-hit;
                health.setValue(Health);
                e.setVisible(false);
            }

            if(hitPoints<=0 )
            {
                System.out.println(hitPoints + " hitpoints are left");
                //friendly.setVisible(false);
                //e.setVisible(false);

                JOptionPane.showMessageDialog(this,
                        "You Died. You had a score of " + Score + " and made it to Level: " + level,
                        "Game Over",
                        JOptionPane.ERROR_MESSAGE);
                Game.panel.setVisible(true);
                this.stop();
                Resources.background.stop();

            }

        }



        ArrayList ms = friendly.getMissile();



        for(int i = 0; i<ms.size(); i++)
        {
            Missile m = (Missile)ms.get(i);
            Rectangle bullet = m.getBounds();

            for (int j = 0; j<enemies.size() ; j++)
            {
                Enemy e = (Enemy)enemies.get(j);
                Rectangle enemy = e.getBounds();

                if (bullet.intersects(enemy))
                {
                    m.setVisible(false);
                    bullets++;
                    Bullets.setText("Bullets: " + bullets + "                                                                                                                ");
                    deathX = e.getX();
                    deathY = e.getY();
                    //e.setVisible(false);
                    //enemies.remove(j);
                    e.setdeath(true);

                    newHealth=enHealth-=8;


                    Resources.enemyHit.play();
                    Score++;
                    score.setText("Score: " + Score + "                                                                                                                ");

                }
                if(!bullet.intersects(enemy))
                {
                    if(bullets==0)
                    {
                    JOptionPane.showMessageDialog(this,
                            "You ran out of Bullets. You had a score of " + Score + " and made it to Level: " + level,
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE);
                        Game.panel.setVisible(true);
                        this.stop();
                        Resources.background.stop();
                    }
                }
            }



        }
    }



    public class Listener implements KeyListener
    {

        public void keyTyped(KeyEvent e)
        {

        }
        public void keyReleased(KeyEvent e)
        {
            friendly.keyReleased(e);
        }
        public void keyPressed(KeyEvent e)
        {
            friendly.keyPressed(e);
        }
    }

    public void reset()
    {
        initEnemies();
        System.out.println("Reset to " + hitPoints +" hitpoints");

        Score = 0;
        level = 1;
        bullets = 30;
        Enemy.speed=1;
        newHealth = 100;
        enHealth=100;
        Health=100;
        hitPoints=2;
        health.setValue(100);
        ehealth.setValue(100);
        friendly.setVisible(true);

        score.setText("Score: " + Score + "                                                                                                                ");
        Level.setText("Level: " + level + "                                                                                                                ");
        Bullets.setText("Bullets " + bullets + "                                                                                                                ");
        timer.start();
        friendly.reset();
    }
    public void stop()
    {
        Score = 0;
        level = 1;
        bullets = 30;
        Enemy.speed=1;
        newHealth = 100;
        enHealth=100;
        Health=100;
        hitPoints=2;
        health.setValue(100);
        ehealth.setValue(100);
        friendly.setVisible(true);
        timer.stop();
        score.setText("Score: " + Score + "                                                                                                                ");
        Level.setText("Level: " + level + "                                                                                                                ");
        Bullets.setText("Bullets " + bullets + "                                                                                                                ");
        repaint();
        revalidate();

    }


}
