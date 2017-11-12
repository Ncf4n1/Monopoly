package esof322.a3;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Gui extends JFrame{

  private GameDriver driver = new GameDriver();

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
      menu();
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
                    humanNames[i] = (String) JOptionPane.showInputDialog("Please Input a Name for Human Player " + (i+1));
                  }
                  //for when we implement AI Players
                  /*for(int j = 0; j < computers; j++){
                    computerNames[j] = (String) JOptionPane.showInputDialog("Please Input a Name for Computer Player " + (j+1));
                  }*/
                  driver.setNumPlayers(humans);
                  driver.setPlayerNames(humanNames);
                  int time = (int)timeLimit.getSelectedItem();
                  //driver.setTimeLimit((long)time);
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
      menu();
      setSize(1500, 1500);
      setVisible(true);
      JPanel boardPanel = new JPanel();
      boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
      try{
        File boardFile = new File("Board.png");
        BufferedImage boardImage = ImageIO.read(boardFile);
        JLabel picLabel = new JLabel(new ImageIcon(boardImage));
        boardPanel.add(picLabel);
      }
      catch(IOException e){
        System.out.println(e);
      }
      boardPanel.setVisible(true);
      add(boardPanel);
    }
  }
}
