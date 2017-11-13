package esof322.a3;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener
{
    private static StartPanel instance;
    private final JButton newGame, exitGame;
    
    public StartPanel()
    {
        newGame = new JButton("New Game");
        exitGame = new JButton("Exit Game");
        
        newGame.addActionListener(this);
        exitGame.addActionListener(this);
        
        add(newGame);
        add(exitGame);
    }
    
    public static StartPanel getInstance()
    {
        if (instance == null)
        {
            instance = new StartPanel();
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
            case "New Game": CardLayout cl = (CardLayout) MainPanel.getInstance().getLayout();
            cl.next(MainPanel.getInstance());
            break;
                
            case "Exit Game": GuiFrame.getInstance().dispose();
            break;
        }
    }
    
}
