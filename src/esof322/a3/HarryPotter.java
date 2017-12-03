package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotter implements GameStyle
{
  public Board board;
  public HarryPotterTokens tokens;

  @Override
  public Board createBoard(){
      board = new Board("Harry Potter");
      return board;
  }

  @Override
  public Tokens[] createTokens(){
    tokens = new HarryPotterTokens();
    return tokens.getTokens();
  }
}
