package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Board
{
	private static Space[] board;  //Data structure for the spaces on the board
	//Property Parameters: name, buy price, rent rates, house cost, mortgage value, first second or third part of a monopoly, x coordinate on the board, y coordinate on the board, monopoly color, properties in monopoly
	//Railroad Parameters: Name, x coordinate, y coordinate
	//Utility Parameters: Name, x coordinate, y coordinate
  private static Image boardImage;

	public Board(String type){
		if(type.equalsIgnoreCase("normal")){
			NormalBoard nb = new NormalBoard();
			board = nb.getBoard();
			boardImage = nb.getImage();
		}
		else{
			HarryPotterBoard hpb = new HarryPotterBoard();
			board = hpb.getBoard();
			boardImage = hpb.getImage();
		}
	}

	public Image getBoardImage(){
		return boardImage;
	}

	public static Space getSpace(int location){
		return board[location];
	}
}
