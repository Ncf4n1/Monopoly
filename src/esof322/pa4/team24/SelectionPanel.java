package esof322.pa4.team24;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class SelectionPanel extends JPanel implements ActionListener{
    private static SelectionPanel instance;
    private final JComboBox<String> style;
    private final JComboBox<Integer> playerNum, turnLimit;
    private final JButton start;
    private final JLabel playerLabel;
    private final JLabel chooseTurnLimit, styleLabel;

    private Player[] players;

    public SelectionPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        styleLabel = new JLabel("Choose Game Theme");
        playerLabel = new JLabel("Choose Number of Players");
        chooseTurnLimit = new JLabel("Choose a Turn Limit");
        start = new JButton("Start");
        style = new JComboBox<>(new String[]{"Normal", "Harry Potter"});
        playerNum = new JComboBox<>(new Integer[]{2, 3, 4});
        turnLimit = new JComboBox<>(new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60});

        styleLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        playerLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        chooseTurnLimit.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        style.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        playerNum.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        turnLimit.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        style.setMaximumSize(new Dimension(150, 100));
        playerNum.setMaximumSize(new Dimension(100, 100));
        turnLimit.setMaximumSize(new Dimension(100, 100));

        style.addActionListener(this);
        playerNum.addActionListener(this);
        turnLimit.addActionListener(this);
        start.addActionListener(this);

        styleLabel.setAlignmentX(CENTER_ALIGNMENT);
        style.setAlignmentX(CENTER_ALIGNMENT);
        playerLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerNum.setAlignmentX(CENTER_ALIGNMENT);
        chooseTurnLimit.setAlignmentX(CENTER_ALIGNMENT);
        turnLimit.setAlignmentX(CENTER_ALIGNMENT);
        start.setAlignmentX(CENTER_ALIGNMENT);

        add(playerLabel);
        add(playerNum);
        add(chooseTurnLimit);
        add(turnLimit);
        add(styleLabel);
        add(style);
        add(start);
    }

    public static SelectionPanel getInstance()
    {
        if (instance == null)
        {
            instance = new SelectionPanel();
        }
        return instance;
    }


    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        switch (command) {
            case "Start": GameDriver.setBoardandTokens((String) style.getSelectedItem());
                          playerSetup((String) style.getSelectedItem());
                          CardLayout cl = (CardLayout) MainPanel.getInstance().getLayout();
                          cl.next(MainPanel.getInstance());
                          PlayerInfoPanel.getInstance().infoSetup(players);
                          GameDriver.setTurnLimit((int) turnLimit.getSelectedItem());
                          //initialized = true;
                          break;
        }
    }

    public void playerSetup(String type)
    {
        String[] tnames;
        if(type.equalsIgnoreCase("normal")){
          tnames = NormalTokens.normalTokenNames;
        }
        else{
          tnames = HarryPotterTokens.harryPotterTokenNames;
        }
        players = new Player[(int) playerNum.getSelectedItem()];
        String name = "";
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(tnames));
        String lastTokenChosen = "";
        for (int i = 0; i < players.length; i++)
        {
            if (i != 0)
            {
                for (int j = 0; j < tokens.size(); j++)
                {
                    if (tokens.get(j).equals(lastTokenChosen))
                    {
                        tokens.remove(tokens.get(j));
                    }
                }
            }
            name = JOptionPane.showInputDialog("Please Input a Name for Player " + (i+1));
            lastTokenChosen = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Token", "Token Selection", JOptionPane.PLAIN_MESSAGE, null, tokens.toArray(), tokens.get(0));

            players[i] = new Player(name, lastTokenChosen);
        }

        PlayerInfoPanel.getInstance().setNumPlayers(players.length);
        GameDriver.setPlayers(players);
    }
}
