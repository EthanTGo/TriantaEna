package specific;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Players {
//Should have: name, Balance
  String name;
  Balance balance;
  ArrayList<Hand> totalHand = new ArrayList<Hand>();
  //This is the in-game attributes of the player
  boolean still_playing; //whether the player's still has valid amount of hands

public Players(String name){
  this.name = name;
  this.balance = new Balance(0);
}

  public void getPlayerHand(Deck d){ //inital hand
	  
  }

//Player should be able to: Hit, Stand, Split, Double up, Bet
public int Play(Hand play_hand) {
    boolean valid_number = false;
    int input = 0;
    Scanner scan = new Scanner(System.in);
    if(play_hand.checkSplit()) { //if the split is not possible
        System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp, 3 for Split");
        while(!valid_number) {
            System.out.println("Enter a valid number");
            input = scan.nextInt();
            if(input >= 0 & input < 4) {
                valid_number = true;
            }
        }
        return input;
    }
    else {
        System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp");
        while(!valid_number) {
            System.out.println("Enter a valid number");
            input = scan.nextInt();
            if(input >= 0 & input < 3) {
                valid_number = true;
            }
        }
        return input;
   }
}


  public void updatebalance(int i){
          if(i > 0) {
              this.balance.addBalance(i);
          }
          else {
              this.balance.decreaseBalance(i);
          }
  }

  public Boolean checkPlayerBalance(){
      if(balance.getMoney() <= 0){
          return false;
      }
      else{
          return true;
      }
  }

  
  public void printScore(Hand score, int num){
      System.out.println("Player's score for hand " + num + " is " + score.score);
      }

  public void initalizeBalance(int i){
      balance = new Balance(i);
  }
  public int getBalance(){
      return balance.getMoney();
  }

  public boolean containSplit() {
    if(totalHand.get(0).hand.get(0).getId() == totalHand.get(0).hand.get(1).getId() ) {
      return true;
    } else {
      return false;
    }

  }
}
