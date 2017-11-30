package esof322.a3;

import javax.swing.ImageIcon;

public class Tokens
{
  protected ImageIcon tokenImage;
  protected String tokenName;

  public static void setTokenImage(String name){
    tokenImage = new ImageIcon(this.getClass().getResource(name)).getImage();
  }
  public static void setTokenName(String name){
    tokenName = name;
  }
}
