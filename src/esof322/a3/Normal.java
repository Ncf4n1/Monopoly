package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Normal implements GameStyle
{

  @Override
  public Board createBoard(){
    Board board = new Board("Normal");
    return board;
  }

  @Override
  public Tokens[] createTokens(){
    NormalTokens tokens = new NormalTokens();
    return tokens.getTokens();
  }
}
