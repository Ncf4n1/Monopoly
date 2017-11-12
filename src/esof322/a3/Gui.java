package esof322.a3;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Gui extends JFrame{

  public boolean initialized = false;

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
    menu();
    setSize(1500, 1500);
    setVisible(true);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //button to begin a new game
    JButton newgame = new JButton("New Game");
    newgame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              //dispose current frame and open options frame to start a new game
              OptionsFrame optionsFrame = new OptionsFrame();
              dispose();
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
    add(centerPanel, BorderLayout.CENTER);
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
  /* class for new JFrame that contains the game set up options
   * user selects number of players and a time limit
   */
  private class OptionsFrame extends JFrame{
    public OptionsFrame(){
      setTitle("Monopoly");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1500, 1500);
      setVisible(true);
      JPanel playerPanel = new JPanel();
      playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
      //get human players
      JLabel hplabel = new JLabel("Number of Human Players");
      playerPanel.add(hplabel);
      Integer[] humanPlayerOptions = new Integer[] {2,3,4};
      Integer[] computerPlayerOptions = new Integer[] {0,1,2,3};
      //options to choose number of players
      JComboBox<Integer> humanPlayers = new JComboBox<Integer>(humanPlayerOptions);
      playerPanel.add(humanPlayers);
      //get computer players
      JLabel cplabel = new JLabel("Number of Computer Players");
      //playerPanel.add(cplabel);
      JComboBox<Integer> computerPlayers = new JComboBox<Integer>(computerPlayerOptions);
      //playerPanel.add(computerPlayers);
      //get time limit
      JLabel tlabel = new JLabel("Time Limit (minutes)");
      playerPanel.add(tlabel);
      Integer[] times = new Integer[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
      //options to choose time limit for game
      JComboBox<Integer> timeLimit = new JComboBox<Integer>(times);
      playerPanel.add(timeLimit);
      //button to start new game with selected options
      JButton start = new JButton("Start");
      start.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                //create players

                int humans = (int)humanPlayers.getSelectedItem();
                String[] humanNames = new String[humans];
                //for when we implement AI Players
                /*int computers = (int)computerPlayers.getSelectedItem();
                String[] computerNames = new String[computers]; */
                  for (int i = 0; i < humans; i++)
                  {
                    humanNames[i] = JOptionPane.showInputDialog("Please Input a Name for Human Player " + (i+1));
                  }
                  for (int i = 0; i < humans; i++)
                  {
                    System.out.println(humanNames[i]);
                  }
                  //for when we implement AI Players
                  /*for(int j = 0; j < computers; j++){
                    computerNames[j] =  JOptionPane.showInputDialog("Please Input a Name for Computer Player " + (j+1));
                  }*/
                  GameDriver.setNumPlayers(humans);
                  GameDriver.setPlayerNames(humanNames);
                  int time = (int)timeLimit.getSelectedItem();
                  GameDriver.setTimeLimit((long)time);
                  initialized = true;
                  BoardFrame boardFrame = new BoardFrame();
                  dispose();
                  //for when we implement AI Players
              /*  else{
                  JDialog error = new JOptionPane("Number of players must be between 2 and 8.", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog("Error");
                  error.setAlwaysOnTop(true);
                  error.setVisible(true);
                  error.dispose();
                  }*/
                }
          });
      playerPanel.add(start);
      add(playerPanel);
    }
  }
  /**
  * class for a new JFrame that contains the board
  */
  private class BoardFrame extends JFrame{
    public BoardFrame(){
      setTitle("Monopoly");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1500, 1500);
      setVisible(true);

      BoardPanel boardPanel = new BoardPanel();
      add(boardPanel);
    }

    private class BoardPanel extends JPanel{
      public BoardPanel(){
        setVisible(true);
        boolean moveDone = false;
        JButton move = new JButton("Take Turn");
        move.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                  //take a turn
                  GameDriver.startTurn();
                  int roll = GameDriver.rollDice();
                  System.out.println("die 1: " + GameDriver.getDie1());
                  System.out.println("die 2: " + GameDriver.getDie2());
                  System.out.println("doubles: " + GameDriver.getDoublesInARow());
                  if(GameDriver.getDoublesInARow() != 3){
                    move.setVisible(true);
                    GameDriver.movePlayerToken();
                    System.out.println("location: " + GameDriver.getCurrentPlayer().getLocation());
                    GameDriver.passGo();
                    GameDriver.checkSpace(GameDriver.getCurrentPlayer().getLocation());
                    System.out.println("Money: " + GameDriver.getCurrentPlayer().getMoneyTotal());
                    repaint();
                  }
                  if(GameDriver.getDoublesInARow() == 0){
                    move.setVisible(false);
                  }
                  //System.out.println("doubles in a row: " + GameDriver.doublesInARow);
                }
            });
        repaint();
        add(move);
        if(GameDriver.getDoublesInARow() == 0){
          JButton endTurn = new JButton("End Turn");
          endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              GameDriver.endTurn();
              move.setVisible(true);
            }
          });
          add(endTurn);
        }

      }
      @Override
      public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < GameDriver.getPlayers().length; i++){
          int x = GameDriver.getXCoordinate(GameDriver.getPlayers()[i])/4;
          int y = GameDriver.getYCoordinate(GameDriver.getPlayers()[i])/4;
          Graphics2D g2d = (Graphics2D) g;
          g2d.drawRect(x-50, y-10, 100, 20);
          g2d.drawString(GameDriver.getPlayers()[i].token, x-47, y+5);
        }
      }
      public void addBuyPropertyButton(){
        //int price = GameDriver.getPrice();
        JButton buy = new JButton("Buy Space for $");
        add(buy);
        buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  //buy property currently on
                  GameDriver.buyProperty();
                }
            });

      }
    }
  }
}
