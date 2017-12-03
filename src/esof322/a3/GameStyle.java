package esof322.a3;
import java.lang.*;
import java.awt.Image;

public interface GameStyle {
   Board createBoard();
   Tokens[] createTokens();
}
