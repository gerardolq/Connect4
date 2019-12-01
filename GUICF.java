package CFCode;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICF extends CFGame
{
    public CFPlayer Player1;
    public CFPlayer Player2;
    private GameBoard this_board;
    //boolean that'll tell me if the game is Human vs AI
    private boolean AI;
    
    //will return the String containing the winner name or draw
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
    
    //starts Human vs AI game
    public GUICF(CFPlayer ai)
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
        this_board = new GameBoard();
        //number of buttons needed to play the game
        this_board.addButton(7);
        AI = true;
        if(Player1 == ai)
            playAI(Player1);
        
    }
    
    //starts a AI vs AI game
    public GUICF()
    {
        Random rand = new Random();
        int j = rand.nextInt(1) + 1;
    
        Player1 = new HumanPlayer();
        Player2 = new HumanPlayer();
        
        this_board = new GameBoard();
        //number of buttons needed to play the game
        this_board.addButton(7);
    }
    
    //plays the AI's turn
    private void playAI(CFPlayer P)
    {
        boolean k = playGUI(P.nextMove(this));
        while(!k)
        { k = playGUI(P.nextMove(this));}
    }
    
    //plays the column chosen by the player
    private boolean playGUI(int c)
    {
        //if invalid input
        if(c > 7)
            return false;
        
        //if the row is full (invalid input)
        int[][] boardArr = getState();
        int row = -1;
        for(int i = 0; i < 6; i++)
        {
            if(boardArr[c-1][i] == 0)
            {
                row = i;
                break;
            }
        }
        //given that it is full, return false
        if(row == -1)
            return false;
        
        //update the board, if the player who played was red, then update with red
        //otherwise, update with black. isRedTurn() will return the new players turn
        //so they are flipped
        else if (play(c) && isRedTurn())
            this_board.paint(c,row,-1);
        else
            this_board.paint(c,row,1);
        
        return true;
    }
    
    //implementation for human player
    private class HumanPlayer implements CFPlayer
    {
        public int nextMove(CFGame g)
        {return(1);}
        
        public String getName()
        {
            return "Human Player";
        }
    }
    
    
    private class GameBoard extends javax.swing.JPanel
    {
        //tiles that conatain the checkers
        private JLabel[] squares = new JLabel[42];
        //frame for the game
        private JFrame boardFrame = new JFrame();
        private JPanel Board = new JPanel(new GridLayout(6,7));
        //buttons for the game
        private final JButton[] buttons = new JButton[7];
        //boolean that tells whether or not the game has 7 buttons
        //i.e. if humans are playing or not
        private boolean SevenButtons;
        
        //implemention for AI vs AI
        private class BB implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(isRedTurn())
                    playAI(Player1);
                else
                    playAI(Player2);
                if(isGameOver())
                    EndGame();
            }
            
        }
        //implementation for buttons 1 - 7 numbered 0 - 6
        private class B0L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(1))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B1L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(2))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B2L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(3))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B3L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(4))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B4L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(5))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B5L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(6))
                    return;
                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
            }
            
        }
        private class B6L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!playGUI(7))
                    return;

                if(isGameOver())
                    EndGame();
                if(AI)
                {
                    if(isRedTurn())
                        playAI(Player1);
                    else
                        playAI(Player2);
                    if(isGameOver())
                        EndGame();
                }
                
            }
            
        }
        //implementation for end game button
        private class EG implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
            
        }
        
        private GameBoard ()
        {
            // initialize empty board
            boardFrame.setTitle("Connect Four");
            boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //initialize tiles
            for (int i = 0; i < squares.length; i++)
            {
                squares[i] = new JLabel();
                squares[i].setSize(10,10);
                squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                squares[i].setBackground(Color.WHITE);
                squares[i].setOpaque(true);
                Board.add(squares[i]);
            }
            boardFrame.add(Board);
            boardFrame.pack();
            boardFrame.setVisible(true);

        }
        
        private void paint (int x, int y, int color )
        {
            int squareC = 7*(5-y) + (x-1);
            // paints specified coordinate red or black
            if(color == 1)
                squares[squareC].setBackground(Color.RED);
            else
            {squares[squareC].setBackground(Color.BLACK);
                squares[squareC].setBorder(BorderFactory.createLineBorder(Color.GRAY));}
        }
        
        private void addButton(int buttonNum)
        {
            //update sevenbutton boolean and initialize
            //necessary buttons
            if(buttonNum == 7)
                SevenButtons = true;
            else
                SevenButtons = false;
            
            JPanel BPanel = new JPanel(new GridLayout(1,7));
            if(buttonNum == 1)
            {
                buttons[0] = new JButton("Play");
                BPanel.add(buttons[0],BorderLayout.CENTER);
                buttons[0].addActionListener(new BB());
            }
            
            if (buttonNum == 7)
            {
                for(int i = 0; i < 7; i++)
                {
                    buttons[i] = new JButton("\u2193");
                    BPanel.add(buttons[i]);
                }
                buttons[0].addActionListener(new B0L());
                buttons[1].addActionListener(new B1L());
                buttons[2].addActionListener(new B2L());
                buttons[3].addActionListener(new B3L());
                buttons[4].addActionListener(new B4L());
                buttons[5].addActionListener(new B5L());
                buttons[6].addActionListener(new B6L());
            }

            boardFrame.add(BPanel,BorderLayout.NORTH);
        }
    
        //ends the game
    private void EndGame()
    {
        //double-check that the function isn't called by accident
        if(!(isGameOver()))
            return;
        
        //disable buttons necessary to disable
        if(SevenButtons)
        {
            for(int i = 0; i < 7; i++)
                buttons[i].setEnabled(false);
        }
        else
            buttons[0].setEnabled(false);
        
        //add the panel with the ending button to close out of the game
        JFrame Frame = new JFrame();
        //int i = winner();
        String winner = getWinner();
        JPanel ENDGAME = new JPanel();
        if(winner() == 0)
        {
            JButton b = new JButton(winner + "! \n" + "Click here to close");
            b.addActionListener(new EG());
            ENDGAME.add(b,BorderLayout.CENTER);
        }
      else
      {
          JButton d = new JButton(winner + " has won the game!" + "\n" + "Click here to close");
          d.addActionListener(new EG());
          ENDGAME.add(d,BorderLayout.CENTER);
      }
        boardFrame.add(ENDGAME,BorderLayout.CENTER);
        boardFrame.setVisible(true);
    }
    }
    
}
