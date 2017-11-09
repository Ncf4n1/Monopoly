package esof322.a3;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame{

  public static void main(String[] args){
    new Gui();
  }

  public Gui() {
    startWindow();
  }

  public void startWindow() {
    setTitle("Monopoly");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton newgame = new JButton("New Game");
    newgame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              System.out.println("new game");
              addOptionsPanel(centerPanel);
            }
        });
    centerPanel.add(newgame);
    JButton close = new JButton("Close Program");
    close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        });
    centerPanel.add(close);
    menu();
    add(centerPanel, BorderLayout.CENTER);
    setSize(500, 500);
    setVisible(true);
  }

  public void addOptionsPanel(JPanel oldPanel) {
    JPanel optionsPanel = new JPanel();
    JButton btn = new JButton("enter");
    optionsPanel.add(btn);
    oldPanel.setVisible(false);
    add(optionsPanel, FlowLayout.CENTER);
  }

  public void menu(){
    JMenuBar bar = new JMenuBar();
    JMenu exitOption = new JMenu("Exit");
    JMenuItem exit = new JMenuItem("Exit Game");
    exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        });
    exitOption.add(exit);
    bar.add(exitOption);
    setJMenuBar(bar);
  }

  public JPanel createPanel() {
    JPanel newpanel = new JPanel();
    newpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    setSize(500,500);
    setVisible(true);
    return newpanel;
  }

}
