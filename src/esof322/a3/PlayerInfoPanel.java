package esof322.a3;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerInfoPanel extends JPanel {
    private static PlayerInfoPanel instance;
    private int numPlayers;
    private Player[] players;
    
    public PlayerInfoPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    
    public static PlayerInfoPanel getInstance()
    {
        if (instance == null)
        {
            instance = new PlayerInfoPanel();
        }
        return instance;
    }
    
    public void setNumPlayers(int numPlayers)
    {
        this.numPlayers = numPlayers;
    }
    
    public void infoSetup(Player[] players)
    {
        for (int i = 0; i < numPlayers; i++)
        {
        JTextArea area = new JTextArea(players[i].getName() + " - " + players[i].token + "\n" +
                              "Money: $" + players[i].getMoneyTotal() + "\n" +
                              "Properties Owned: \n" + getPropertyStrings(players[i]),
                               10, 50);
        
        area.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        area.setEditable(false);
        area.setAlignmentX(CENTER_ALIGNMENT);
        add(area);
        }
        
        this.players = players;
    }
    
    public void updateInfo()
    {
        this.removeAll();
        this.revalidate();
        this.repaint();
        infoSetup(GameDriver.getPlayers());
    }
    
    public String getPropertyStrings(Player player)
    {
    	String list = "";
    	for (int i = 0; i < player.getPropertiesOwned().size(); i++)
    	{
    		list += player.getPropertiesOwned().get(i).getName() + "\n";
    	}
    	return list;
    }
}
