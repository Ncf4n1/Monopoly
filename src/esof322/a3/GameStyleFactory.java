package esof322.a3;
import java.lang.*;
import java.awt.Image;

public interface GameStyleFactory {
   Board createBoard(String type);
   Tokens createTokens(Image i, String n);
}
