package CFCode;
import java.util.Random;

public class GerardoAI implements CFPlayer {
    private int[][] ProbableMove = new int[2][1];
    private int[][] OpponentMove = new int[2][1];
    private int[][] board = new int[7][6];
    
    //checks whether theres checkers of specified color to the left
    private int toLeft(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if(board[column][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toLeft(column-1,row,color)));
    }
    
    //checks whether theres checkers of specified color to the right
    private int toRight(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if(board[column][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toRight(column+1,row,color)));
    }
    
    //checks whether theres checkers of specified color on top
    private int toTop(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if (board[column][row] == color)
            count++;
        else
            return 0;
        
        return ((count+toTop(column,row+1,color)));
    }
    
    //checks whether theres checkers of specified color below
    private int toBottom(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if (board[column][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toBottom(column,row-1,color)));
    }
    
    
    //checks whether theres checkers of specified color on top right
    private int toTopRight(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if (board[column][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toTopRight(column+1,row+1,color)));
    }
    
    //check whether theres checkers of a specified color on top left
    private int toTopLeft(int column, int row, int color)
    {
        int count = 0;
        if (column < 0 || column > 6 || row > 5 || row < 0)
            return 0;
        
        if (board[column][row] == color)
            count++;
        else
            return 0;
        
        return (count + toTopLeft(column-1,row+1,color));
    }
    
    //returns the next move
    public int nextMove(CFGame g)
    {
        //gets the color of GerardoAI
        int color = g.isRedTurn()? 1:-1;;
        board = g.getState();
        ProbableMove[0][0] = 1000;
        ProbableMove[1][0] = 1000;
        OpponentMove[0][0] = 1000;
        OpponentMove[1][0] = 1000;
        
        //checks for the best possible move
        Possibilities(color, ProbableMove);
        //checks the opponents best move
        Possibilities((-1*color), OpponentMove);
        
        //checks whether the move is valid
        if(ProbableMove[1][0] > 7)
        {
            Random randCol = new Random();
            int Col = randCol.nextInt(7) + 1;
            return Col;
        }
        
        //checks whose closest to winning
        else if(ProbableMove[0][0] < OpponentMove[0][0])
            return ProbableMove[1][0];
        
        //if the opponent is closer to winning, block them
        else
            return OpponentMove[1][0];
    }
    
    //checks all the possibilities and update the array with the best possibility
    private void Possibilities(int color, int[][] Moves)
    {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(board[i][j] == color)
                {
                    //check row for connect 4
                    //NumConnectedL on the left
                    //NumConnectedR on the right
                    int NumConnectedL = 1 + toLeft(i-1,j,color);
                    int NumConnectedR = 1 + toRight(i+1,j,color);
                    
                    //if there is at least one chip in a row to the left
                    if (NumConnectedL > 0)
                    {
                        //check how many empty spaces to the left after the last chip
                        int m = toLeft(i-NumConnectedL,j,0);
                        //if enough to make 4, update the array
                        if(m+NumConnectedL > 3 && i-NumConnectedL < 8)
                        {
                            if(4-NumConnectedL < Moves[0][0])
                            {
                                if(board[i-NumConnectedL][j] == 0)
                                {
                                    Moves[0][0] = 4 - NumConnectedL;
                                    Moves[1][0] = i-NumConnectedL+1;
                                    
                                }
                            }
                        }
                    }
                    
                    //if there is at least one chip in a row to the right
                    if (NumConnectedR > 0)
                    {
                        //check how many empty spaces to the right after the last chip
                        int m = toLeft(i+NumConnectedR,j,0);
                        //if enough to make 4, update the array
                        if(m+NumConnectedR > 3 && i+NumConnectedR < 8)
                        {
                            if(4-NumConnectedR < Moves[0][0])
                            {
                                if(board[i+NumConnectedR][j] == 0)
                                {
                                    Moves[0][0] = 4-NumConnectedR;
                                    Moves[1][0] = i+NumConnectedR+1;
                                    
                                }
                            }
                        }
                    }
                    
                    //check for column connect 4
                    int NumConnectedT = 1 + toTop(i,j+1,color);
                    if (NumConnectedT > 0)
                    {
                        int m = toTop(i,j+NumConnectedT,0);
                        if(m+NumConnectedT > 3 && i < 8)
                        {
                            if(4-NumConnectedT < Moves[0][0])
                            {
                                if(board[i][j+NumConnectedT] == 0)
                                {Moves[0][0] = 4-NumConnectedT;
                                    Moves[1][0] = i+1;}
                            }
                        }
                    }
                    
                    //check forward diagonal
                    int NumConnectedTR = 1 + toTopRight(i+1,j+1,color);
                    if (NumConnectedTR > 0)
                    {
                        int m = toTopRight(i+NumConnectedTR,j+NumConnectedTR,0);
                        if(m+NumConnectedTR > 3 && i+1 < 8)
                        {
                            int missing = 0;
                            int k = 1;
                            while(k < 4-NumConnectedTR)
                            {
                                missing = toBottom(i+k,j+k,0);
                                k++;
                            }
                            if(missing+4-NumConnectedTR < Moves[0][0])
                            {
                                if(board[i+NumConnectedTR][j+NumConnectedTR] == 0)
                                { Moves[0][0] = missing+4-NumConnectedTR;
                                    Moves[1][0] = i+NumConnectedTR+1;}
                            }
                        }
                    }
                    
                    //check the backward diagonal
                    int NumConnectedTL = 1 + toTopLeft(i-1,j+1,color);
                    if (NumConnectedTL > 0)
                    {
                        int m = toTopLeft(i-NumConnectedTL,j+NumConnectedTL,0);
                        if(m+NumConnectedTL > 3 && i-1 < 8)
                        {
                            int missing = 0;
                            int k = 1;
                            while(k < 4-NumConnectedTL)
                            {
                                missing = toBottom(i-k,j+k,0);
                                k++;
                            }
                            if(missing+4-NumConnectedTL < Moves[0][0])
                            {
                                if(board[i-NumConnectedTL][j+NumConnectedTL] == 0)
                                { Moves[0][0] = missing+4-NumConnectedTL;
                                    Moves[1][0] = i-NumConnectedTL+1;}
                            }
                        }
                    }
                }
         
            }
            
        }
    }
    
    public String getName()
    {return ("Gerardo's Player");}

}
