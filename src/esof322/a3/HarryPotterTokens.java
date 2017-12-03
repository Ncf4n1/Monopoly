package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotterTokens
{
  private Tokens[] harryPotterTokens = new Tokens[4];   //array of harry potter themed tokens
  public static Image[] harryPotterTokenImages;         //array of images for harry potter tokens
  public static String[] harryPotterTokenNames;         //array of names for harry potter tokens

  public HarryPotterTokens(){
    harryPotterTokenImages = new Image[] {new ImageIcon(this.getClass().getResource("Owl.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("Broom.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("Snitch.png")).getImage(),
                      new ImageIcon(this.getClass().getResource("Hat.png")).getImage()};
    harryPotterTokenNames = new String[] {"Owl", "Broom", "Snitch", "Hat" };

    for(int i = 0; i < 4; i++){
      harryPotterTokens[i] = new Tokens(harryPotterTokenImages[i], harryPotterTokenNames[i]);
    }
  }
  //return type Tokens[]
  public Tokens[] getTokens(){
    return harryPotterTokens;
  }
}
