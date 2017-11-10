package esof322.a3;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.*;

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
    setSize(500, 1000);
    setVisible(true);
  }
  /**
  * method to add a new JPanel to the monopoly gui
  * user enters number of players and time limit for the game
  * takes in the current panel as a parameter
  */
  public void addOptionsPanel(JPanel oldPanel) {
    JPanel playerPanel = new JPanel();
    JLabel plabel = new JLabel("Number of Players    ");
    playerPanel.add(plabel);
    Integer[] numplayers = new Integer[] {1,2,3,4};
    //options to choose number of players
    JComboBox<Integer> players = new JComboBox<Integer>(numplayers);
    playerPanel.add(players);
    JPanel timePanel = new JPanel();
    timePanel.setLayout(new BoxLayout(timePanel,BoxLayout.LINE_AXIS));
    JLabel tlabel = new JLabel("Time Limit (minutes)    ");
    timePanel.add(tlabel);
    Integer[] times = new Integer[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
    //options to choose time limit for game
    JComboBox<Integer> timeLimit = new JComboBox<Integer>(times);
    timePanel.add(timeLimit);
    //button to start new game with selected options
    JButton start = new JButton("Start");
    start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //create players
              //start new game
              addBoard(timePanel, playerPanel);
            }
        });
    timePanel.add(start);
    //hide current panel to replace it
    oldPanel.setVisible(false);
    add(timePanel);
    timePanel.add(playerPanel);
  }

  public void addBoard(JPanel oldPanel1, JPanel oldPanel2){
    oldPanel1.setVisible(false);
    oldPanel2.setVisible(false);

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
  public JPanel createPanel(int x ,int y) {
    JPanel newpanel = new JPanel();
    newpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    setSize(x,y);
    setVisible(true);
    return newpanel;
  }

}
