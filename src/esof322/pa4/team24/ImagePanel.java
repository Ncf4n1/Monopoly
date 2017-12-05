package esof322.pa4.team24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private static ImagePanel instance;
    private final PlayerInfoPanel infoPanel;
    private final ButtonPanel buttonPanel;
    private static Image boardImage;    //image of the board
    private static Image[] tokens;      //images of the tokens
    private static String[] tokenNames; //names of the tokens
    private final Player[] players;     //array of players

    public ImagePanel()
    {
        GridLayout layout = new GridLayout(0,5);
        infoPanel = PlayerInfoPanel.getInstance();
        buttonPanel = ButtonPanel.getInstance();
        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setVisible(false);
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setVisible(false);
        JPanel emptyPanel3 = new JPanel();
        emptyPanel3.setVisible(false);
        layout.setHgap(200);
        setLayout(layout);
        add(emptyPanel1, BorderLayout.WEST);
        add(emptyPanel2, BorderLayout.WEST);
        add(emptyPanel3, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.EAST);
        setBackground(Color.WHITE);

        players = GameDriver.getPlayers();
    }

    //set theme of the Game
    //called from the GameDriver
    public static void setType(String type){
      if(type.equalsIgnoreCase("Normal")){
        tokens = NormalTokens.normalTokenImages;
        tokenNames = NormalTokens.normalTokenNames;
        boardImage = NormalBoard.normalboardImage;
      }
      else if(type.equalsIgnoreCase("Harry Potter")){
        tokens = HarryPotterTokens.harryPotterTokenImages;
        tokenNames = HarryPotterTokens.harryPotterTokenNames;
        boardImage = HarryPotterBoard.hpboardImage;
      }
    }

    //return static instance of ImagePanel
    public static ImagePanel getInstance()
    {
        if (instance == null)
        {
            instance = new ImagePanel();
        }
        return instance;
    }

    @Override
    public void paintComponent(Graphics g)
    {
         super.paintComponent(g);
         g.drawImage(boardImage, 0, 0, this.getInstance().getHeight(), this.getInstance().getHeight(), null);
         drawTokens(g);
         PlayerInfoPanel.getInstance().updateInfo();
         revalidate();
    }

    //draw all player tokens on correct locations
    public void drawTokens(Graphics g)
    {
        int x = 0;
        int y = 0;
        double scaler = (this.getHeight() / 1500.0);
        for (Player player : GameDriver.getPlayers()) {
             for (int i = 0; i < tokenNames.length; i++) {
                 if (player.token.equals(tokenNames[i])) {
                     x = (int) ((scaler) * GameDriver.getXCoordinate(player) - 10.0);
                     y = (int) ((scaler) * GameDriver.getYCoordinate(player) - 10.0);
                     g.drawImage(tokens[i], x, y, this);
                     break;
                 }
             }
        }
    }

    //display list of winners at end of game
    public void declareWinner(ArrayList<Player> winners)
    {
    	JOptionPane.showMessageDialog(this, "Winner(s):\n" + getWinnerString(winners), "Winners", JOptionPane.CLOSED_OPTION);
    	System.exit(0);
    }

    //get string of winners at the end of the game to display
    public String getWinnerString(ArrayList<Player> winners)
    {
    	String winnerList = "";
    	for (Player winner : winners)
    	{
    		winnerList += winner.getName() + "\n";
    	}

    	return winnerList;
    }
}
