package esof322.a3;

import java.awt.*;
import javax.swing.*;

public class GuiFrame extends JFrame{

  private static GuiFrame instance;

  public GuiFrame() {
    super("Monopoly");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container container = getContentPane();
    container.add(MainPanel.getInstance());
    setJMenuBar(MenuBar.getInstance());
    
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    setVisible(true);
  }
  
  public static GuiFrame getInstance()
  {
      if (instance == null)
      {
          instance = new GuiFrame();
      }
      return instance;
  }
}