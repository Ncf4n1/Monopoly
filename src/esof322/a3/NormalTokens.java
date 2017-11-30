package esof322.a3;

public class NormalTokens extends Tokens
{
  private Tokens normalTokens[4];
  private String normalTokenImages[4];
  private String normalTokenNames[4];

  public NormalTokens(){
    normalTokenImages = {"", "", "", ""};
    normalTokenNames = {"", "", "", "");}

    for(int i = 0; i < 4; i++){
      normalTokens[i].tokenImage = setTokenImage(normalTokenImages[i]);
      normalTokens[i].tokenName = setTokenName(normalTokenNames[i]);
    }
    ImagePanel.getInstance().setTokens(normalTokens);
  }
}
