package esof322.pa4.team24;
import java.awt.Image;

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
