package esof322.a3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private static ImagePanel instance;
    private final PlayerInfoPanel infoPanel;
    private final ButtonPanel buttonPanel;
    private final Image boardImage;
    private final Image[] tokens;
    private final String[] tokenNames;
    private final Player[] players;

    public ImagePanel()
    {
        boardImage = new ImageIcon(this.getClass().getResource("BoardResized.png")).getImage();
        tokens = new Image[4];
        tokenNames = new String[4];
        tokens[0] = new ImageIcon(this.getClass().getResource("Scottish Terrier.png")).getImage();
        tokenNames[0] = "Scottish Terrier";
        tokens[1] = new ImageIcon(this.getClass().getResource("BattleShip.png")).getImage();
        tokenNames[1] = "BattleShip";
        tokens[2] = new ImageIcon(this.getClass().getResource("Top Hat.png")).getImage();
        tokenNames[2] = "Top Hat";
        tokens[3] = new ImageIcon(this.getClass().getResource("Thimble.png")).getImage();
        tokenNames[3] = "Thimble";
        GridLayout layout = new GridLayout(0,4);
        infoPanel = PlayerInfoPanel.getInstance();
        buttonPanel = ButtonPanel.getInstance();
        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setVisible(false);
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setVisible(false);
        layout.setHgap(200);
        setLayout(layout);
        add(emptyPanel1, BorderLayout.WEST);
        add(emptyPanel2, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.EAST);
        setBackground(Color.WHITE);

        players = GameDriver.getPlayers();
    }

    public static ImagePanel getInstance()
    {
        if (instance == null)
        {
            instance = new ImagePanel();
        }
        return instance;
    }

    @Override
    public void paintComponent(Graphics g)
    {
         super.paintComponent(g);
         g.drawImage(boardImage, 0, 0, this.getInstance().getHeight(), this.getInstance().getHeight(), null);
         drawTokens(g);
         PlayerInfoPanel.getInstance().updateInfo();
         revalidate();
    }

    public void drawTokens(Graphics g)
    {
        int x = 0;
        int y = 0;
        double scaler = (this.getHeight() / 1500.0);
        for (Player player : GameDriver.getPlayers()) {
             for (int i = 0; i < tokenNames.length; i++) {
                 if (player.token.equals(tokenNames[i])) {
                     x = (int) ((scaler) * GameDriver.getXCoordinate(player) - 10.0);
                     y = (int) ((scaler) * GameDriver.getYCoordinate(player) - 10.0);
                     g.drawImage(tokens[i], x, y, this);
                     break;
                 }
             }
        }
    }
}
