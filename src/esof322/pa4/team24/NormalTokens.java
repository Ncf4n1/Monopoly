package esof322.pa4.team24;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NormalTokens
{
  private Tokens[] normalTokens = new Tokens[4];        //array of normal themed tokens
  public static Image[] normalTokenImages;              //array of images for normal tokens
  public static String[] normalTokenNames;              //array of names for normal tokens

  public NormalTokens(){
    normalTokenImages = new Image[] {new ImageIcon(this.getClass().getResource("images/Scottish Terrier.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("images/BattleShip.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("images/Top Hat.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("images/Thimble.png")).getImage()};
    normalTokenNames = new String[] {"Scottish Terrier", "BattleShip", "Top Hat", "Thimble" };

    for(int i = 0; i < 4; i++){
      normalTokens[i] = new Tokens(normalTokenImages[i], normalTokenNames[i]);
    }
  }
  //return type Tokens[]
  public Tokens[] getTokens(){
    return normalTokens;
  }
}
