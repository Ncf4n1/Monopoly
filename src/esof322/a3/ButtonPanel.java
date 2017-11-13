package esof322.a3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
  Class that creates button and determines what each does when clicked
*/
public class ButtonPanel extends JPanel implements ActionListener
{
    private static ButtonPanel instance;
    private final JButton takeTurn, endTurn, buyProperty, buyHouse, buyHotel;

    public ButtonPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        takeTurn = new JButton("Take Turn");
        endTurn = new JButton("End Turn");
        buyProperty = new JButton("Buy Property");
        buyHouse = new JButton("Buy House");
        buyHotel = new JButton("Buy Hotel");

        endTurn.setEnabled(false);
        buyProperty.setEnabled(false);
        buyHouse.setEnabled(false);
        buyHotel.setEnabled(false);
        takeTurn.setActionCommand("Take Turn");
        setButton(takeTurn);
        setButton(endTurn);
        setButton(buyProperty);
        setButton(buyHouse);
        setButton(buyHotel);
    }

    /*
      Method that formats, aligns, and adds button to the panel
    */
    private void setButton(JButton button)
    {
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 100));
        add(Box.createRigidArea(new Dimension(25, 25)));
        button.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        add(button);
    }

    public void enablePropertyButton()
    {
        buyProperty.setEnabled(true);
    }

    /*
      Method that returns instance of the button panel
    */
    public static ButtonPanel getInstance()
    {
        if (instance == null)
        {
            instance = new ButtonPanel();
        }
        return instance;
    }

    /*
      Method that determines what each button does when it is clicked
    */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        switch (command) {
            case "Take Turn":
                if (GameDriver.getDoublesInARow() != 3)
                {
                    GameDriver.rollDice();
                    System.out.println("took turn");
                    GameDriver.movePlayerToken();
                    ImagePanel.getInstance().repaint();
                    GameDriver.passGo();
                    GameDriver.checkSpace(GameDriver.getCurrentPlayer().getLocation());
                    PlayerInfoPanel.getInstance().updateInfo();
                }
                if (GameDriver.getDoublesInARow() == 0)
                {
                    takeTurn.setEnabled(false);
                    endTurn.setEnabled(true);
                }

                break;
            case "End Turn":
                GameDriver.endTurn();
                endTurn.setEnabled(false);
                takeTurn.setEnabled(true);
                buyProperty.setEnabled(false);
                takeTurn.setText("Take Turn: " + GameDriver.getCurrentPlayer().getName());
                PlayerInfoPanel.getInstance().updateInfo();
                break;
            case "Buy Property":
                GameDriver.buyProperty();
                buyProperty.setEnabled(false);
                PlayerInfoPanel.getInstance().updateInfo();
                break;
            case "Buy House":

                break;
            case "Buy Hotel":

                break;
            default:
                break;
        }
    }
}
