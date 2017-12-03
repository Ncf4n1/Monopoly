package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameStyleFactory
{
  public GameStyle getStyle(String style){
    if(style.equalsIgnoreCase("Normal")){
      return new Normal();
    }
    else if(style.equalsIgnoreCase("Harry Potter")){
      return new HarryPotter();
    }
    else{
      return null;
    }
  }
}
