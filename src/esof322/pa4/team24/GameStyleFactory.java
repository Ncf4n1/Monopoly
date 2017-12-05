package esof322.pa4.team24;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameStyleFactory
{
  //Factory for implementing a game style

  //method to find the style to implement
  //return the GameStyle
  public GameStyle getStyle(String style){
    //create new instance of Normal if style is normal
    if(style.equalsIgnoreCase("Normal")){
      return new Normal();
    }
    //create new instance of HarryPotter if style is harry potter
    else if(style.equalsIgnoreCase("Harry Potter")){
      return new HarryPotter();
    }
    //else return null
    else{
      return null;
    }
  }
}
