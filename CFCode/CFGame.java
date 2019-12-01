package CFCode;
public class CFGame {
  //state[i][j]= 0 means the i,j slot is empty
  //state[i][j]= 1 means the i,j slot has red
  //state[i][j]=-1 means the i,j slot has black
  private final int[][] state;
    private int GameWinner;
  
  {
    state = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
          state[i][j] = 0;
  }
    
  public int[][] getState() {
    int[][] ret_arr = new int[7][6];
    for (int i=0; i<7; i++)
    {
        for (int j=0; j<6; j++)
            ret_arr[i][j] = state[i][j];
    }
    return ret_arr;
  }
  
    //counts the number of checkers and returns
    //true if even number, false if odd
    public boolean isRedTurn()
    {
        int Count = 0;
        for(int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if(state[i][j] != 0)
                    Count++;
            }
        }
        if ((Count%2) == 0)
            return true;
        
        return false;
    }
  
    //to_ functions check for specified color in the "_" direction
    private int toLeft(int column, int row, int color)
    {
        int count = 0;
        if (column < 1)
            return 0;
        
        if(state[column-1][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toLeft(column-1,row,color)));
    }
    
    private int toRight(int column, int row, int color)
    {
        int count = 0;
        if (column > 5)
            return 0;
        
        if(state[column+1][row] == color)
            count++;
        else
            return 0;
        
        return ((count + toRight(column+1,row,color)));
    }
    
    private int toTop(int column, int row, int color)
    {
        int count = 0;
        if (row > 4)
            return 0;
        
        if (state[column][row+1] == color)
            count++;
        else
            return 0;
        
        return ((count+toTop(column,row+1,color)));
    }
    
    private int toBottom(int column, int row, int color)
    {
        int count = 0;
        if (row < 1)
            return 0;
        
        if (state[column][row - 1] == color)
            count++;
        else
            return 0;
        
        return ((count + toBottom(column,row-1,color)));
    }
    
    private int toTopRight(int column, int row, int color)
    {
        int count = 0;
        if (row > 4 || column > 5)
            return 0;
        
        if (state[column+1][row+1] == color)
            count++;
        else
            return 0;
        
        return ((count + toTopRight(column+1,row+1,color)));
    }
    
    private int toTopLeft(int column, int row, int color)
    {
        int count = 0;
        if (row > 4 || column < 1)
            return 0;
        
        if (state[column-1][row+1] == color)
            count++;
        else
            return 0;
        
        return (count + toTopLeft(column-1,row+1,color));
    }
    
    private int toBottomLeft(int column, int row, int color)
    {
        int count = 0;
        if (row < 1 || column < 1 || state[column-1][row-1] != color)
            return 0;
        
        else if (state[column-1][row-1] == color)
            count++;
        
        return (count + toBottomLeft(column-1,row-1,color));
    }
    
    private int toBottomRight(int column, int row, int color)
    {
        int count = 0;
        int[][] board = getState();
        if (row < 1 || column > 5)
            return 0;
        
        if (state[column+1][row-1] == color)
            count++;
        else
            return 0;
        
        return (count + toBottomRight(column+1,row-1,color));
    }
    
    //updated gameboard or returns false if invalid input
  public boolean play(int column)
    {
        if(column > 7)
            return false;
        
        boolean isFull = true;
        int[][] board = getState();
      for (int i = 0; i < 6 ;i++)
      {
          if(board[column-1][i] == 0)
          {
              isFull = false;
              if(isRedTurn())
                  state[column-1][i] = 1;
              else
                  state[column-1][i] = -1;
              break;
          }
      }
      
      
      if(isFull)
          return false;
      
      return true;
  }
  
    //check if game is over
  public boolean isGameOver()
    {
        boolean isFull = true;
        int[][] board = getState();
        //if the game has no more moves, return true
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(board[i][j] == 0)
                {
                    isFull = false;
                    break;
                }
            }
            
            if(!isFull)
                break;
        }
        
        //given that it is not check if there was a winner
        int NumConnected = 0;
        //if red just went, then it'll be black players turn, so we'll
        //flip the colors that check on the respective turn
        //given that red just went, its impossible for black to win and vise
        //versa, so we'll only check for that color
        GameWinner = (isRedTurn())? -1:1;
        //check if winner
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(board[i][j] == GameWinner)
                {
                    //check row for connect 4
                    NumConnected++;
                    NumConnected = NumConnected + toLeft(i,j,GameWinner);
                    NumConnected = NumConnected + toRight(i,j,GameWinner);
                    if (NumConnected > 3)
                        return true;
                    NumConnected = 0;
                    
                   //check for column connect 4
                    NumConnected++;
                    NumConnected = NumConnected + toTop(i,j,GameWinner);
                    NumConnected = NumConnected + toBottom(i,j,GameWinner);
                    if (NumConnected > 3)
                        return true;
                    NumConnected = 0;
                    
                    //check forward diagonal
                    NumConnected++;
                    NumConnected = NumConnected + toTopRight(i,j,GameWinner);
                    NumConnected = NumConnected + toBottomLeft(i,j,GameWinner);
                    if (NumConnected > 3)
                        return true;
                    NumConnected = 0;
                    
                    //check backward diagonal
                    NumConnected++;
                    NumConnected = NumConnected + toTopLeft(i,j,GameWinner);
                    NumConnected = NumConnected + toBottomRight(i,j,GameWinner);
                    if (NumConnected > 3)
                        return true;
                    NumConnected = 0;
                }
            }
        }
        
        GameWinner = 0;
        if(isFull)
            return true;
        return false;
    }
  
  public int winner()
    {
        return GameWinner;
    }
    
}
