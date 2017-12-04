package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Normal implements GameStyle
{
  //override method in interface to create new NormalBoard
  //return type Board
  @Override
  public Board createBoard(){
    Board board = new Board("Normal");
    return board;
  }

  //override method in interface to create new NormalTokens
  //return type Tokens[]
  @Override
  public Tokens[] createTokens(){
    NormalTokens tokens = new NormalTokens();
    return tokens.getTokens();
  }
}
