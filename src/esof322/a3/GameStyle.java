package esof322.a3;
import java.lang.*;
import java.awt.Image;

public interface GameStyle {
  //interface for game style

  //method to create new board
   Board createBoard();
   //method to create new Tokens
   Tokens[] createTokens();
}
