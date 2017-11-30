package esof322.a3;

public class HarryPotterTokens
{
  private Tokens harryPotterTokens[4];
  private String harryPotterTokenImages[4];
  private String harryPotterTokenNames[4];

  public HarryPotterTokens(){
    harryPotterTokenImages = {"", "", "", ""};
    harryPotterTokenNames = {"", "", "", "");}

    for(int i = 0; i < 4; i++){
      harryPotterTokens[i].tokenImage = setTokenImage(harryPotterTokenImages[i]);
      harryPotterTokens[i].tokenName = setTokenName(harryPotterTokenNames[i]);
    }
    ImagePanel.getInstance().setTokens(harryPotterTokens);
  }
}
