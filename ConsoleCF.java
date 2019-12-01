package CFCode;
import java.util.Random;
import java.util.Scanner;

public class ConsoleCF extends CFGame
{
    public CFPlayer Player1;
    public CFPlayer Player2;
    
    public ConsoleCF()
    {
        Player1 = new HumanPlayer();
        Player2 = new HumanPlayer();
    }

    //AI vs Human
    public ConsoleCF(CFPlayer ai)
    {
        Random rand = new Random();
        int j = rand.nextInt(1) + 1;
        if (j == 1)
        {
            Player1 = new HumanPlayer();
            Player2 = ai;
        }
        else
        {
            Player1 = ai;
            Player2 = new HumanPlayer();
        }
    }  
    
    //plays the game on the console
    public void playOut()
    {
        while(!isGameOver())
        {
            boolean Moved = play(Player1.nextMove(this));
            while(!Moved)
            {
                Moved = play(Player1.nextMove(this));
            }
            
            //check for winner
            if(isGameOver())
            {
                System.out.print(getWinner());
                break;
            }
            
            Moved = play(Player2.nextMove(this));
            while(!Moved)
            {
                Moved = play(Player2.nextMove(this));
            }
            //check for winner
            if(isGameOver())
            {
                System.out.print(getWinner());
                break;
            }
        }
    }
    
    public String getWinner()
    {
        int win = winner();
        if(win == 1)
            return (Player1.getName());
        else if(win == -1)
            return (Player2.getName());
        else
            return ("Draw");
    }
    
    //Human player implementation
    private class HumanPlayer implements CFPlayer
    {
        public int nextMove(CFGame g)
        {
            Scanner Move = new Scanner(System.in);
            int[][] board = getState();
            for(int i = 5; i > -1; i--)
            {
                for(int j = 0; j < 7; j++)
                {
                    char print;
                    if (board[j][i] == -1)
                        print = 'B';
                    else if(board[j][i] == 1)
                        print = 'R';
                    else
                        print = ' ';
                    System.out.print("|" + print + "|");
                }
                System.out.println();
            }
            
            System.out.println("What's your next move?");
            int Col = Move.nextInt();
            return(Col);
        }
            
            public String getName()
            {
                return "Human Player";
            }
    }
    
}
