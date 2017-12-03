package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotterFactory implements GameStyleFactory
{
  private static Tokens[] ntokens = new Tokens[4];
  public Image[] nImages = new Image[] {new ImageIcon(this.getClass().getResource("Owl.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("Broom.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("Snitch.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("Hat.png")).getImage()};
  public static String[] nNames = new String[] {"Owl", "Broom", "Snitch", "Hat" };

  public Board createBoard(String type){
    Board.createBoard(type);
  }


  public Tokens[] createTokens(){
    for(int i = 0; i < 4; i++){
      ntokens[i] = new Tokens(nImages[i], nNames[i]);
    }
    return ntokens;
  }
}
