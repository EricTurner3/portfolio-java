import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by Eric Turner on 11/10/2014.
 */
public class Game extends JPanel { //The Game Class

    static Resources Resources; //Call the Images into the Game
    static Gem Gem; //Call the Swapping Methods into the game
    Random rand = new Random(); //Random number generator to place gems on the board

    int Emerald = 0; //Emerald Gem Piece
    int Banana = 1;  //Banana Gem Piece
    int Amethyst = 2; // Amethyst Gem Piece

    int emeraldCount; //Dev counter for Emerald gems on board
    int bananaCount; //Dev counter for Banana gems on board
    int amethystCount; //Dev counter for Amethyst gems on board

    int arrayProgress = 0;//Dev counter to see how the array is filling, 81 is full array
    int count=0; //Counter for mouseEvents to know when to swap the gems
    public Integer board[][] = new Integer[9][9]; // Create a 9 row, 9 column game board
    JButton gemImage[][] = new JButton[9][9]; //Create the physical gems to be used
    int gem; //Number just for use in the gemValue method


    JLabel scoreLabel = new JLabel(); //The ScoreLabel that is currently unused


    public void Board() {

        //Populate Game Boards with Gems
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) { //For loop to cover all 81 positions in the 2D-array

                board[i][j] = rand.nextInt(3); // Randomly Choose a Piece: Amethyst, Banana, Emerald;
                arrayProgress++; // Progress to see How Array is Filling

                    //System.out.println("Elements in Array " + arrayProgress); //Development Purposes to See if Array Is full or not


                if (board[i][j] == Emerald) {
                    emeraldCount++;            //Keep Count of Emerald Gems Added to Board
                } else if (board[i][j] == Banana) {
                    bananaCount++;            //Keep Count of Banana Gems Added to Board
                } else if (board[i][j] == Amethyst) {
                    amethystCount++;          //Keep Count of Amethyst Gems Added to Board
                }

                if (arrayProgress == 81) { //Print to Console total of different Gem types once array is filled
                    System.out.println("There are: " + emeraldCount + " total emerald gems on the board!");
                    System.out.println("There are: " + bananaCount + " total banana gems on the board!");
                    System.out.println("There are: " + amethystCount + " total amethyst gems on the board!");
                }
            }
        }


    } //The Backbone, contains array with gems

    public void guiBoard() //Mouse Listeners and GUI Gems
    {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) { //For loop to cover all 81 positions in the 2D-array
                gemImage[i][j] = new JButton();             //Initialize each Gem as a Button
                gemImage[i][j].setContentAreaFilled(false); //Set Background of each button as transparent
                gemImage[i][j].setBorderPainted(false);     //Remove the colored border from each button
                gemImage[i][j].setPreferredSize(new Dimension(42, 50));   //Gem Button Size
                gemImage[i][j].setMargin(new Insets(2, 5, 2, 5)); //Get the padding inside of the button


                if (board[i][j] == Emerald)
                    gemImage[i][j].setIcon(Resources.emerald);  //If Board is Emerald, Draw Emerald

                if (board[i][j] == Banana)
                    gemImage[i][j].setIcon(Resources.banana);  //If Board is Banana, Draw Banana

                if (board[i][j] == Amethyst)
                    gemImage[i][j].setIcon(Resources.amethyst); // If Board is Amethyst, Draw Amethyst



                this.add(gemImage[i][j]); //Add Image to Board
                
                final int pos1 = i; //Store i to a position
                final int pos2 = j; //Store j to a position

                gemImage[pos1][pos2].addMouseListener(new MouseAdapter() {  //Mouse Event Listeners
                    @Override
                    public void mouseEntered(MouseEvent e) { //Stuff to do when mouse is hovered over the Gems
                        if (board[pos1][pos2] == Emerald)
                            gemImage[pos1][pos2].setIcon(Resources.emerald_glow); //Set the Emerald gem to glow when hovered upon

                        if (board[pos1][pos2] == Banana)
                            gemImage[pos1][pos2].setIcon(Resources.banana_glow); //Set the Banana gem to glow when hovered upon

                        if (board[pos1][pos2] == Amethyst)
                            gemImage[pos1][pos2].setIcon(Resources.amethyst_glow); //Set the Amethyst gem to glow when hovered upon
                    }

                    @Override
                    public void mouseExited(MouseEvent e) { //Stuff to do when the mouse exits the gem's boundaries
                        if (board[pos1][pos2] == Emerald)
                            gemImage[pos1][pos2].setIcon(Resources.emerald); //Set the Emerald gem back to normal upon exit

                        if (board[pos1][pos2] == Banana)
                            gemImage[pos1][pos2].setIcon(Resources.banana); //Set the Banana gem back to normal upon exit

                        if (board[pos1][pos2] == Amethyst)
                            gemImage[pos1][pos2].setIcon(Resources.amethyst);//Set the Amethyst gem back to normal upon exit
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) { //Stuff to do when a gem is clicked

                        Gem.recordPosition(pos1, pos2); //Record the Position Clicked
                        Gem.recordGemType(pos1,pos2);  //Record the Gem Type Clicked


                        if(count ==1) // Once recordPosition and recordGemType are called twice then swap those gems
                        {
                            Gem.swap();
                            count--; //Set Count back to 0 so the methods can be recalled
                        }
                        else{
                            count++; //Called after recordPosition and recordGemType are called once to set up for swap
                        }


                    }
                });

            }

        }
    }

    public void repaintBoard() //To redraw all gems on board, specifically the ones which were swapped
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) { //For loop to cover all 81 positions in the 2D-array

                if (board[i][j] == Emerald)
                    gemImage[i][j].setIcon(Resources.emerald);  //If Board is Emerald, Draw Emerald

                if (board[i][j] == Banana)
                    gemImage[i][j].setIcon(Resources.banana);  //If Board is Banana, Draw Banana

                if (board[i][j] == Amethyst)
                    gemImage[i][j].setIcon(Resources.amethyst); // If Board is Amethyst, Draw Amethyst

                gemImage[i][j].repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) //The painting method for background and glitter ScoreLabel
    {
        super.paintComponent(g); //Calls the paintComponent method for the parent, Game(), constructor
        g.drawImage(Resources.background.getScaledInstance(775, 535, 100), 0, 0, this); //Add the Background
        g.drawImage(Resources.scoreLabel.getImage(), 0, Main.gameHeight - 74, this);    //Add the Score Label


    }



    public Game() // Main Constructor
    {
        super(); //Calls the Parent, this, constructor
        setPreferredSize(new Dimension(705,535)); //Size of Panel
        setLayout(new GridLayout(10,9,-10,0)); //Set Layout to Grid

        Board();   // Populate Gems
        guiBoard(); //GUI Gems and Mouse Listeners
        scoreLabel.setText("test"); //Set the text of the scoreLabel
        scoreLabel.setFont(Resources.Dark_Crystal); //Custom font to match the flashing .gif Scorelabel
        add(scoreLabel); //Add Score Label to Board

    }






}
