package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NormalTokens
{
  private Tokens[] normalTokens = new Tokens[4];
  public static Image[] normalTokenImages;
  public static String[] normalTokenNames;

  public NormalTokens(){
    normalTokenImages = new Image[] {new ImageIcon(this.getClass().getResource("Scottish Terrier.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("BattleShip.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("Top Hat.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("Thimble.png")).getImage()};
    normalTokenNames = new String[] {"Scottish Terrier", "BattleShip", "Top Hat", "Thimble" };

    for(int i = 0; i < 4; i++){
      normalTokens[i] = new Tokens(normalTokenImages[i], normalTokenNames[i]);
    }
  }

  public Tokens[] getTokens(){
    return normalTokens;
  }
}
