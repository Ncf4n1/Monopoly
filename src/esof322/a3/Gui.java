package esof322.a3;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame{

  public static void main(String[] args){
    new Gui();
  }

  public Gui() {
    startWindow();
  }
  /**
  * method to open starting window for monopoly gui
  * presents options to start a new game or exit the gui
  */
  public void startWindow() {
    setTitle("Monopoly");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //button to begin a new game
    JButton newgame = new JButton("New Game");
    newgame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //hides current panel and replaces it with panel presenting options
              //to start a new game
              addOptionsPanel(centerPanel);
            }
        });
    centerPanel.add(newgame);
    //button to exit the game
    JButton close = new JButton("Exit Game");
    close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //closes gui
              System.exit(0);
            }
        });
    centerPanel.add(close);
    menu();
    add(centerPanel, BorderLayout.CENTER);
    setSize(500, 500);
    setVisible(true);
  }
  /**
  * method to add a new JPanel to the monopoly gui
  * user enters number of players and time limit for the game
  * takes in the current panel as a parameter
  */
  public void addOptionsPanel(JPanel oldPanel) {
    JPanel optionsPanel = new JPanel();
    JLabel plabel = new JLabel("Number of Players");
    optionsPanel.add(plabel);
    Integer[] numplayers = new Integer[] {1,2,3,4};
    //options to choose number of players
    JComboBox<Integer> players = new JComboBox<Integer>(numplayers);
    optionsPanel.add(players);
    JLabel tlabel = new JLabel("Time Limit (minutes)");
    optionsPanel.add(tlabel);
    Integer[] times = new Integer[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
    //options to choose time limit for game
    JComboBox<Integer> timeLimit = new JComboBox<Integer>(times);
    optionsPanel.add(timeLimit);
    //button to start new game with selected options
    JButton start = new JButton("Start");
    start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //create players
            	GameDriver.setNumPlayers(numplayers.length);
            	Player[] players = new Player[numplayers.length];
            	
            	String playername, token = "";
            	String[] possibilities = {"Scottish Terrier", "Battleship", "Automobile", "Top Hat", "Penguin", "T-Rex", "Cat", "Rubber Duck"};
            	for (int i = 0; i < numplayers.length; i++)
            	{
            		playername = JOptionPane.showInputDialog("Please Input a Name for Player " + numplayers[i]);
            		token = (String) JOptionPane.showInputDialog(null, "Please Select a Token", "Token Selection", JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
            		players[i] = new Player(playername, token);
            	}
            	GameDriver.setPlayers(players);
              //start new game
            }
        });
    optionsPanel.add(start);
    //hide current panel to replace it
    oldPanel.setVisible(false);
    add(optionsPanel, FlowLayout.CENTER);
  }
  /**
  * method to add the menu to the monopoly gui
  * presents option to exit the gui
  */
  public void menu(){
    JMenuBar bar = new JMenuBar();
    JMenu exitOption = new JMenu("Exit");
    JMenuItem exit = new JMenuItem("Exit Game");
    exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //closes gui
              System.exit(0);
            }
        });
    exitOption.add(exit);
    bar.add(exitOption);
    setJMenuBar(bar);
  }
  /**
  * method to create a new panel in the monopoly gui
  * returns a JPanel
  */
  public JPanel createPanel() {
    JPanel newpanel = new JPanel();
    newpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    setSize(500,500);
    setVisible(true);
    return newpanel;
  }
}
