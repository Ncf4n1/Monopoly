package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Tokens
{
  public String name;   //name of token
  public Image image;   //image for token

  //create new token with a name and image
  public Tokens(Image i, String n){
    name = n;
    image = i;
  }
}
