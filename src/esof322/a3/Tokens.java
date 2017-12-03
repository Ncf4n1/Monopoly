package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Tokens implements GameStyleFactory
{
  String name;
  Image image;

  public Tokens(Image i, String n){
    name = n;
    image = i;
  }

  @Override
  public Tokens createTokens(Image i, String n){
    name = n;
    image = i;
  }
}
