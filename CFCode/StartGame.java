package CFCode;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGame extends CFGame
{
	private MainMenu main;
	public StartGame(){
		main = new MainMenu();
		main.addButton();
        }

	private class MainMenu extends javax.swing.JPanel
	{
	
		private JFrame menuFrame = new JFrame();
		private JPanel menu = new JPanel();
		private final JButton[] butt = new JButton[2];

		// returns value for player vs AI
		private class A0L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
            	menuFrame.dispatchEvent(new WindowEvent(menuFrame, WindowEvent.WINDOW_CLOSING));
                new GUICF(new SmartAI());
            }
            
        }

        // returns value for player vs player
        private class A1L implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
            	menuFrame.dispatchEvent(new WindowEvent(menuFrame, WindowEvent.WINDOW_CLOSING));
            	new GUICF();
            }
            
        }

	private void addButton()
	{
		butt[0] = new JButton("Play vs AI");
		butt[1] = new JButton("Player vs Player");
		butt[0].addActionListener(new A0L());
        butt[1].addActionListener(new A1L());
        JPanel BPanel = new JPanel(new GridLayout(1,2));
        BPanel.add(butt[0],BorderLayout.CENTER);
        BPanel.add(butt[1],BorderLayout.CENTER);
        menuFrame.add(BPanel,BorderLayout.CENTER);
        menuFrame.setPreferredSize(new Dimension(500, 700));
        menuFrame.pack();
        menuFrame.setVisible(true);
	}

	public MainMenu()
	{
		menuFrame.setTitle("Connect 4: Main Menu");
        menuFrame.add(menu);
        menuFrame.pack();
        menuFrame.setVisible(true);
	}

	}
}