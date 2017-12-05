package esof322.pa4.team24;
import java.lang.*;
import java.awt.Image;

public interface GameStyle {
  //interface for game style

  //method to create new board
   Board createBoard();
   //method to create new Tokens
   Tokens[] createTokens();
}
