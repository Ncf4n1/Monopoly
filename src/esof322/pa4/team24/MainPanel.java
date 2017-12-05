package esof322.pa4.team24;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private static MainPanel instance;
    
    public MainPanel()
    {
        setLayout(new CardLayout());
        add(StartPanel.getInstance());
        add(SelectionPanel.getInstance());
        add(ImagePanel.getInstance());
        
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.first(this);  
    }
    
    public static JPanel getInstance()
    {
        if (instance == null)
        {
            instance = new MainPanel();
        }
        return instance;
    }
}
