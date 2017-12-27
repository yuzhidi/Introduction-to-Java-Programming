package jsf2demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GuessNumber {
  private int number;
  private String guessString;
  
  public GuessNumber() {
    number = (int)(Math.random() * 100);
  }

  public String getGuessString() {
    return guessString;
  }

  public void setGuessString(String guessString) {
    this.guessString = guessString;
  }

  public String getResponse() {
    if (guessString == null)
      return ""; // No user input yet
    
    int guess = Integer.parseInt(guessString);
    if (guess < number) 
      return "Too low";
    else if (guess == number) 
      return "You got it";
    else 
      return "Too high";
  }
}
