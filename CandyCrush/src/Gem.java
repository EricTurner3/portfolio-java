/**
 * Created by Eric Turner on 12/9/2014.
 */
public class Gem //The Gem Swapping and Recording Class
{

    int pos1, pos2, pos3,pos4; //Positions to be recorded
    int gemType1,gemType2; //Gems to be recorded
    String gem1, gem2; //Gems to be spat out onto the console for easiness of reading
    int posCount = 0; //Counter for recordPosition method stage
    int gemCount = 0; //Counter for recordGemType method stage

    public Game Game; //Call to already running Game instance

    public void recordPosition(int position, int position2) //Record the Position in the Array for Swapping
    {
        if(posCount == 0) { //The first time the method is called
            pos1 = position;
            pos2= position2;
            System.out.println("------------------------------------------------------");
            System.out.println("First Gem at " + pos1 + ", " + pos2);
            posCount++; //Advance the Method for the Second positions to be recorded
        }
        else { //When the Method is called again
            pos3 = position;
            pos4 = position2;
            System.out.println("Second Gem at " + pos3 + ", " + pos4);
            posCount--;//Set the method back up for initialization
        }


    }
    public void recordGemType(int position, int position2) //Record the integers of the Gems to Swap
    {
        if(gemCount == 0) //The first time the method is called
        {
            if(Game.board[position][position2] == 0)
            {
                gemType1 = 0;
            }
            if(Game.board[position][position2] == 1)
            {
                gemType1 = 1;
            }
            if(Game.board[position][position2] == 2)
            {
                gemType1 = 2;
            }
            System.out.println("First Gem: "+ gem1);
            gemCount++; //Advance the Method for the second Gem to be recorded
        }
        else { //When the Method is called again
            if(Game.board[position][position2] == 0)
            {
                gemType2 = 0;
            }
            if(Game.board[position][position2] == 1)
            {
                gemType2 = 1;
            }
            if(Game.board[position][position2] == 2)
            {
                gemType2 = 2;
            }
            System.out.println("Second Gem: " + gem2);
            gemCount--; //Set Method Back up for initial call
        }

        //----------------------------------------------------------
        //Gem1 Integer to String For Dev Print Out
        if (this.gemType1 == 0)
        {
            gem1 = "Emerald Gem";
        }
        else if (this.gemType1 == 1)
        {
            gem1 = "Banana Gem";
        }
        else if (this.gemType1 == 2)
        {
            gem1 = "Amethyst Gem";
        }
        //---------------------------------------------------------
        //Gem2 Integer to String For Dev Print Out
        if (this.gemType2 == 0)
        {
            gem2 = "Emerald Gem";
        }
        else if (this.gemType2 == 1)
        {
            gem2 = "Banana Gem";
        }
        else if (this.gemType2 == 2)
        {
            gem2 = "Amethyst Gem";
        }
        //---------------------------------------------------------
    }

    public void swap() //The physical swapping method. Prerequisites: recordPosition x2 & recordGemType x2
    {

        System.out.println("Swapping "+this.gem1+" at "+ this.pos1 +"," +this.pos2+ " with "+ this.gem2 +" at "+this.pos3 +"," +this.pos4);
        Game.board[this.pos1][this.pos2] = this.gemType2;  //Take the Gem Type from the Second Position and Make it the First
        Game.board[this.pos3][this.pos4] = this.gemType1;  //Take the Gem Type from the First Position and Make it the Second
        System.out.println("------------------------------------------------------");
        Game.repaintBoard();
    }


}







