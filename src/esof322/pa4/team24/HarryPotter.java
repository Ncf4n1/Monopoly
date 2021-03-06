package esof322.pa4.team24;


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
