package esof322.pa4.team24;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
  Class that creates button and determines what each does when clicked
*/
public class ButtonPanel extends JPanel implements ActionListener
{
    private static ButtonPanel instance;
    private final JButton takeTurn, endTurn, buyProperty, buyHouse, buyHotel, sellHouse, sellHotel, mortgage, payToLeaveJail, rollToLeaveJail, useGetOutOfJailCard;
    private final JTextArea turnsTaken;
    
    public ButtonPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        takeTurn = new JButton("Take Turn");
        endTurn = new JButton("End Turn");
        buyProperty = new JButton("Buy Property");
        buyHouse = new JButton("Buy House");
        buyHotel = new JButton("Buy Hotel");
        sellHouse = new JButton("Sell House");
        sellHotel = new JButton("Sell Hotel");
        mortgage = new JButton("Mortgage");
        payToLeaveJail = new JButton("Pay to Leave Jail");
        rollToLeaveJail = new JButton("Roll to Leave Jail");
        useGetOutOfJailCard = new JButton("Use Get Out Of Jail Free Card");

        endTurn.setEnabled(false);
        buyProperty.setEnabled(false);
        buyHouse.setEnabled(false);
        buyHotel.setEnabled(false);
        sellHouse.setEnabled(false);
        sellHotel.setEnabled(false);
        mortgage.setEnabled(false);
        payToLeaveJail.setEnabled(false);
        rollToLeaveJail.setEnabled(false);
        useGetOutOfJailCard.setEnabled(false);
        
        takeTurn.setActionCommand("Take Turn");
        
        setButton(takeTurn);
        setButton(endTurn);
        setButton(buyProperty);
        setButton(buyHouse);
        setButton(buyHotel);
        setButton(sellHouse);
        setButton(sellHotel);
        setButton(mortgage);
        setButton(payToLeaveJail);
        setButton(rollToLeaveJail);
        setButton(useGetOutOfJailCard);
        
        turnsTaken = new JTextArea("Rounds Completed: " + GameDriver.getTurnsTaken());
        turnsTaken.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnsTaken.setMaximumSize(new Dimension(200, 50));
        add(Box.createRigidArea(new Dimension(25, 25)));
        turnsTaken.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        turnsTaken.setEditable(false);
        add(turnsTaken);
    }

    /*
      Method that formats, aligns, and adds button to the panel
    */
    private void setButton(JButton button)
    {
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
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
                    GameDriver.movePlayerToken();
                    ImagePanel.getInstance().repaint();
                    GameDriver.passGo();
                    GameDriver.displayTurnInfo();
                    GameDriver.checkSpace();
                    PlayerInfoPanel.getInstance().updateInfo();
                }
                else
                {
                	GameDriver.getCurrentPlayer().setJailedStat(true);
                	GameDriver.getCurrentPlayer().setLocation(10);
                }
                
                if ( (GameDriver.getDoublesInARow() == 0 || GameDriver.getDoublesInARow() == 3) || (GameDriver.getCurrentPlayer().getLocation() == 30) )
                {
                    takeTurn.setEnabled(false);
                    endTurn.setEnabled(true);
                }
                break;
                
            case "End Turn":
            	if (GameDriver.getCurrentPlayer().getLocation() == 30)
            	{
            		GameDriver.getCurrentPlayer().setLocation(10);
            		GameDriver.getCurrentPlayer().setJailedStat(true);
            		ImagePanel.getInstance().repaint();
            	}
                GameDriver.endTurn();

                if (GameDriver.getCurrentPlayer().getJailedStat())
                {
                	takeTurn.setEnabled(false);
                	endTurn.setEnabled(false);
                	mortgage.setEnabled(false);
                	buyHouse.setEnabled(false);
                	buyHotel.setEnabled(false);
                	sellHouse.setEnabled(false);
                	sellHotel.setEnabled(false);
                	if (GameDriver.getCurrentPlayer().getTurnsInJail() < 3)
                	{
                		rollToLeaveJail.setEnabled(true);
                		payToLeaveJail.setEnabled(true);
                		useGetOutOfJailCard.setEnabled(GameDriver.getCurrentPlayer().checkForJailCard());
                	}
                	else
                	{
                		rollToLeaveJail.setEnabled(false);
                		payToLeaveJail.setEnabled(true);
                		useGetOutOfJailCard.setEnabled(GameDriver.getCurrentPlayer().checkForJailCard());
                	}
                }
                else
                {
                	endTurn.setEnabled(false);
                	takeTurn.setEnabled(true);
                	
                	if (GameDriver.getCurrentPlayer().getPropertiesOwned().size() > 0)
                    {
                    	for (int i = 0; i < GameDriver.getCurrentPlayer().getPropertiesOwned().size(); i++)
                    	{
                    		if (!GameDriver.getCurrentPlayer().getPropertiesOwned().get(i).mortgaged)
                    		{
                    			mortgage.setEnabled(true);
                    		}
                    	}
                    }
                    else
                    {
                    	mortgage.setEnabled(false);
                    }
                }
                buyProperty.setEnabled(false);
                takeTurn.setText("Take Turn: " + GameDriver.getCurrentPlayer().getName());
                PlayerInfoPanel.getInstance().updateInfo();
                if (GameDriver.getCurrentPlayer().getHouseBuildableProps().size() > 0)
                {
                	buyHouse.setEnabled(true);
                }
                else
                {
                	buyHouse.setEnabled(false);
                }
                if (GameDriver.getCurrentPlayer().getHotelBuildableProps().size() > 0)
                {
                	buyHotel.setEnabled(true);
                }
                else
                {
                	buyHotel.setEnabled(false);
                }
                
                turnsTaken.replaceRange(String.valueOf(GameDriver.getTurnsTaken()), 18, 19);
                break;
                
            case "Buy Property":
                GameDriver.buyProperty();
                buyProperty.setEnabled(false);
                PlayerInfoPanel.getInstance().updateInfo();
                break;
                
            case "Pay to Leave Jail":
            	GameDriver.getCurrentPlayer().payToLeaveJail();
            	GameDriver.checkSpace();
            	rollToLeaveJail.setEnabled(false);
            	payToLeaveJail.setEnabled(false);
            	endTurn.setEnabled(true);
            	ImagePanel.getInstance().repaint();
            	break;
            	
            case "Roll to Leave Jail":
            	GameDriver.getCurrentPlayer().rollToGetOutOfJail();
            	GameDriver.checkSpace();
            	rollToLeaveJail.setEnabled(false);
            	payToLeaveJail.setEnabled(false);
            	endTurn.setEnabled(true);
            	ImagePanel.getInstance().repaint();
            	break;
            	
            case "Use Get Out Of Jail Card":
            	GameDriver.getCurrentPlayer().useJailCard();
            	GameDriver.checkSpace();
            	rollToLeaveJail.setEnabled(false);
            	payToLeaveJail.setEnabled(false);
            	endTurn.setEnabled(true);
            	ImagePanel.getInstance().repaint();
            	break;
                
            case "Buy House":
            	String[] buildableHouses = new String[GameDriver.getCurrentPlayer().getHouseBuildableProps().size()];
            	for (int i = 0; i < buildableHouses.length; i++)
            	{
            		buildableHouses[i] = GameDriver.getCurrentPlayer().getHouseBuildableProps().get(i).getName() + " (" + GameDriver.getCurrentPlayer().getHouseBuildableProps().get(i).getNumHouses() + " Houses on Property)";
            	}
            	String buildHouseOnProperty = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Property to Build Houes On", "Building Selection", JOptionPane.PLAIN_MESSAGE, null, buildableHouses, buildableHouses[0]);
            	for (int j = 0; j < buildableHouses.length; j++)
            	{
            		if (GameDriver.getCurrentPlayer().getHouseBuildableProps().get(j).getName().equals(buildHouseOnProperty))
            		{
            			GameDriver.getCurrentPlayer().buildHouse(GameDriver.getCurrentPlayer().getHouseBuildableProps().get(j));
            		}
            	}
                break;
                
            case "Buy Hotel":
            	String buildHotelOnProperty = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Property to Build Hotel On", "Building Selection", JOptionPane.PLAIN_MESSAGE, null, GameDriver.getCurrentPlayer().getHotelBuildableProps().toArray(), GameDriver.getCurrentPlayer().getHotelBuildableProps().get(0));
            	for (int j = 0; j < GameDriver.getCurrentPlayer().getHotelBuildableProps().size(); j++)
            	{
            		if (GameDriver.getCurrentPlayer().getHotelBuildableProps().get(j).getName().equals(buildHotelOnProperty))
            		{
            			GameDriver.getCurrentPlayer().buildHotel(GameDriver.getCurrentPlayer().getHotelBuildableProps().get(j));
            		}
            	}
                break;
                
            case "Sell House":
            	String[] sellableHouses = new String[GameDriver.getCurrentPlayer().getPropWithSellable().size()];
            	for (int i = 0; i < sellableHouses.length; i++)
            	{
            		sellableHouses[i] = GameDriver.getCurrentPlayer().getPropWithSellable().get(i).getName() + " (" + GameDriver.getCurrentPlayer().getPropWithSellable().get(i).getNumHouses() + " Houses on Property)";
            	}
            	String sellHouseOnProperty = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Property to Sell House On", "Selling Selection", JOptionPane.PLAIN_MESSAGE, null, sellableHouses, sellableHouses[0]);
            	for (int j = 0; j < sellableHouses.length; j++)
            	{
            		if (GameDriver.getCurrentPlayer().getPropWithSellable().get(j).getName().equals(sellHouseOnProperty))
            		{
            			GameDriver.getCurrentPlayer().getPropWithSellable().get(j).sellHouse();
            		}
            	}
                break;
            	
            case "Sell Hotel":
            	String[] sellableHotels = new String[GameDriver.getCurrentPlayer().getPropsWithHotels().size()];
            	for (int i = 0; i < sellableHotels.length; i++)
            	{
            		sellableHotels[i] = GameDriver.getCurrentPlayer().getPropsWithHotels().get(i).getName();
            	}
            	String sellHotelOnProperty = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Property to Sell Hotel On", "Selling Selection", JOptionPane.PLAIN_MESSAGE, null, sellableHotels, sellableHotels[0]);
            	for (int j = 0; j < sellableHotels.length; j++)
            	{
            		if (GameDriver.getCurrentPlayer().getPropsWithHotels().get(j).getName().equals(sellHotelOnProperty))
            		{
            			GameDriver.getCurrentPlayer().getPropsWithHotels().get(j).sellHotel();;
            		}
            	}
                break;
            	
            case "Mortgage":
            	String[] properties = new String[GameDriver.getCurrentPlayer().getPropertiesOwned().size()];
            	for (int i = 0; i < GameDriver.getCurrentPlayer().getPropertiesOwned().size(); i++)
            	{
            		if (!GameDriver.getCurrentPlayer().getPropertiesOwned().get(i).mortgaged)
            		{
            			properties[i] = GameDriver.getCurrentPlayer().getPropertiesOwned().get(i).getName();
            		}
            	}
            	String mortgaged = (String) JOptionPane.showInputDialog(GuiFrame.getInstance(), "Please Select a Property to Mortgage", "Mortgage Selection", JOptionPane.PLAIN_MESSAGE, null, properties, properties[0]);
            	for (int j = 0; j < GameDriver.getCurrentPlayer().getPropertiesOwned().size(); j++)
            	{
            		if (GameDriver.getCurrentPlayer().getPropertiesOwned().get(j).getName().equals(mortgaged))
            		{
            			GameDriver.getCurrentPlayer().mortgage(GameDriver.getCurrentPlayer().getPropertiesOwned().get(j));
            		}
            		break;
            	}
            	PlayerInfoPanel.getInstance().updateInfo();
            	
            default:
                break;
        }
    }
}
