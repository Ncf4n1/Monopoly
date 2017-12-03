package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotter implements GameStyle
{
  //override method in interface to create new HarryPotterBoard
  //return type Board
  @Override
  public Board createBoard(){
      Board board = new Board("Harry Potter");
      return board;
  }

  //override method in interface to create new HarryPotterTokens
  //return type Tokens[]
  @Override
  public Tokens[] createTokens(){
    HarryPotterTokens tokens = new HarryPotterTokens();
    return tokens.getTokens();
  }
}
