package esof322.a3;

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
    private final JComboBox<Integer> playerNum, timeLimit;
    private final JButton start;
    private final JLabel playerLabel;
    private final JLabel chooseTimeLimit;
    public boolean initialized = false;

    private Player[] players;

    public SelectionPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        playerLabel = new JLabel("Choose Number of Players");
        chooseTimeLimit = new JLabel("Choose a Time Limit (In Minutes)");
        start = new JButton("Start");
        playerNum = new JComboBox<>(new Integer[]{2, 3, 4});
        timeLimit = new JComboBox<>(new Integer[]{1, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60});

        playerLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        chooseTimeLimit.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        playerNum.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        timeLimit.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        playerNum.setMaximumSize(new Dimension(100, 100));
        timeLimit.setMaximumSize(new Dimension(100, 100));

        playerNum.addActionListener(this);
        timeLimit.addActionListener(this);
        start.addActionListener(this);

        playerLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerNum.setAlignmentX(CENTER_ALIGNMENT);
        chooseTimeLimit.setAlignmentX(CENTER_ALIGNMENT);
        timeLimit.setAlignmentX(CENTER_ALIGNMENT);
        start.setAlignmentX(CENTER_ALIGNMENT);

        add(playerLabel);
        add(playerNum);
        add(chooseTimeLimit);
        add(timeLimit);
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
            case "Start": playerSetup();
                          CardLayout cl = (CardLayout) MainPanel.getInstance().getLayout();
                          cl.next(MainPanel.getInstance());
                          PlayerInfoPanel.getInstance().infoSetup(players);
                          GameDriver.setTimeLimit((int) timeLimit.getSelectedItem());
                          //this.initialized = true;
                          break;
        }
    }

    public void playerSetup()
    {
        players = new Player[(int) playerNum.getSelectedItem()];
        String name = "";
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList("Scottish Terrier", "BattleShip", "Top Hat", "Thimble"));
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
