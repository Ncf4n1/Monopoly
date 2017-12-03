package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NormalFactory
{
  private static Tokens[] ntokens = new Tokens[4];
  public Image[] nImages = new Image[] {new ImageIcon(this.getClass().getResource("Scottish Terrier.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("BattleShip.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("Top Hat.png")).getImage(),
                    new ImageIcon(this.getClass().getResource("Thimble.png")).getImage()};
  public static String[] nNames = new String[] {"Scottish Terrier", "BattleShip", "Top Hat", "Thimble" };


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
