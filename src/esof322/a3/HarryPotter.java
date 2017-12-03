package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotter implements GameStyle
{

  @Override
  public Board createBoard(){
      Board board = new Board("Harry Potter");
      return board;
  }

  @Override
  public Tokens[] createTokens(){
    HarryPotterTokens tokens = new HarryPotterTokens();
    return tokens.getTokens();
  }
}
