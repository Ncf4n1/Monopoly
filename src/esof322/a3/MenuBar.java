package esof322.a3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener
{
    private static MenuBar instance;
    private final JMenu exitOption;
    private final JMenuItem exit;
    
    public MenuBar()
    {
        exitOption = new JMenu("Exit");
        exit = new JMenuItem("Exit Game");
        add(exitOption);
        exitOption.add(exit);
        exit.addActionListener(this);
    }
    
    public static MenuBar getInstance()
    {
        if (instance == null)
        {
            instance = new MenuBar();
        }
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
            switch (command) {
                case "Exit Game": GuiFrame.getInstance().dispose();
                break;
                default: break;
            }
    }

}
