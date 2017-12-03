package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Normal implements GameStyle
{
  public Board board;
  public NormalTokens tokens;

  @Override
  public Board createBoard(){
    board = new Board("Normal");
    return board;
  }

  @Override
  public Tokens[] createTokens(){
    tokens = new NormalTokens();
    return tokens.getTokens();
  }
}
