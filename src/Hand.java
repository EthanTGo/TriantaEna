import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hand {
	ArrayList<Cards> cards_in_hand;
	int score;
	boolean split;
	boolean valid; //if hand score past 21 then it is no longer valid
	int bet_inplaced;
	

	public Hand() {
		this.cards_in_hand = new ArrayList<Cards>();
		this.score = 0;
	}
	
	public Hand(int bet) {
		this();
		this.bet_inplaced = bet;
	}

	public void add(Cards to_add) {
		this.cards_in_hand.add(to_add);
		updateScore(to_add);
		if(this.score > 21) {
			this.valid = false;
		}
	}
	
	public void remove(Cards to_remove) {
		this.cards_in_hand.remove(to_remove);
		score(); //updates the score
	}
	
	public void Bet(int bet_amount) {
	    //this.balance.decreaseBalance(bet_amount);
	    this.bet_inplaced += bet_amount;
	}

	  public int getBet(){
	      return this.bet_inplaced;
	  }

	public void updateScore(Cards c) {
		int value = c.getValue();
		score += value;
	}
	public void aceUpdateScore(Cards c) {
	  if(cards_in_hand.contains(c)) {
	    score += 1;
	  } else { //1 or 11
	    if(score > 20) { //31 
	      score += 1;
	    } else {
	      score += 11;
	    }
	  }
	}
	
	public void printHand() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for (int i = 0; i < this.cards_in_hand.size(); i++) {
			if (i == (this.cards_in_hand.size() - 1)) {
				sb.append(this.cards_in_hand.get(i).getId());
			} else {
				sb.append(this.cards_in_hand.get(i).getId() + " and ");
			}
		}
		System.out.println(sb);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Banker's cards are: ");
		for (int i = 0; i < this.cards_in_hand.size(); i++) {
			if (i == (this.cards_in_hand.size() - 1)) {
				sb.append(this.cards_in_hand.get(i).getId());
			} else {
				sb.append(this.cards_in_hand.get(i).getId() + " and ");
			}
		}
		return sb.toString();
	}
	
	public void printScore() {
		score();
		System.out.println("The score for this hand is " + this.score);
	}
	
	public void getPlayerHand(Deck d) { // inital hand, 2 cards
		Random rand = new Random();
		int random = rand.nextInt(d.sizeDeck());
		Cards a = d.getCard(random);
		d.removeCard(random);
		this.cards_in_hand.add(a);
		int rand2 = rand.nextInt(d.sizeDeck());
		Cards b = d.getCard(rand2);
		d.removeCard(rand2);
		cards_in_hand.add(b);
	}
	
	public void printPlayersHandWithHiddenCard() { //print three cards with score
	  Cards hidden = cards_in_hand.get(0); //hidden inital card
	  Cards two = cards_in_hand.get(1); //face up
	  Cards three = cards_in_hand.get(2); //face up
	  
	  int AceCount = 0;
	  if(hidden.id.equals("Ace")) {
	    AceCount ++;
	  }
	  if(two.id.equals("Ace")) {
        AceCount ++;
      }
	  if(three.id.equals("Ace")) {
        AceCount ++;
      }
	  
	  System.out.println("Your current hand consists of: " + hidden.id + " (hidden); "+ two.id + " (face up); "+ three.id + " (face up)");
	  if(AceCount == 0) {
	    int no_ace_score =  hidden.getValue() + two.getValue() + three.getValue();
	    score = no_ace_score;
	    System.out.println("Your hand's score is: " + no_ace_score);
	  } else if (AceCount == 1) {
	    System.out.println("Your hand includes one Ace!");
	    System.out.println("You have the option of setting the score of the Ace to either: 1 or 11");
	    if(hidden.id.equals("Ace")) {
	      int x = two.getValue() + three.getValue();
	      System.out.println("Your hand's score without the Ace is : " + x);
	      System.out.println("What would you like to set your Ace as?");
	      Scanner scan = new Scanner(System.in);
	      int choice = scan.nextInt();
	      switch(choice) {
	        case 1: 
	          System.out.println("You have selected to set the Ace as 1");
	          x = x + 1;
	          score = x;
	          System.out.println("Your hand's score is: " + x);
	        case 11:
	          System.out.println("You have selected to set the Ace as 11");
	          x = x + 11;
	          score = x;
	          System.out.println("Your hand's score is: " + x);
	      }
	    } else if (two.id.equals("Ace")) {
	      int x = hidden.getValue() + three.getValue();
          System.out.println("Your hand's score without the Ace is : " + x);
          System.out.println("What would you like to set your Ace as?");
          Scanner scan = new Scanner(System.in);
          int choice = scan.nextInt();
          switch(choice) {
            case 1: 
              System.out.println("You have selected to set the Ace as 1");
              x = x + 1;
              score = x;
              System.out.println("Your hand's score is: " + x);
            case 11:
              System.out.println("You have selected to set the Ace as 11");
              x = x + 11;
              score = x;
              System.out.println("Your hand's score is: " + x);
          } 
	    }  
	    else {
            int x = hidden.getValue() + two.getValue();
            System.out.println("Your hand's score without the Ace is : " + x);
            System.out.println("What would you like to set your Ace as?");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch(choice) {
              case 1: 
                System.out.println("You have selected to set the Ace as 1");
                x = x + 1;
                score = x;
                System.out.println("Your hand's score is: " + x);
              case 11:
                System.out.println("You have selected to set the Ace as 11");
                x = x + 11;
                score = x;
                System.out.println("Your hand's score is: " + x);
          }
	    } 
	  } else {
	    System.out.println("Your hand consists of multiple Aces! Your Aces will have the value of one!");
	    if(AceCount == 3) {
	      score = 3;
	      System.out.println("Your hand's score is: " + 3);
	    } else {
	      if(!hidden.getId().equals("Ace")) {
	        score = hidden.getValue() +2;
	        System.out.println("Your hand's score is: " + score);
	      } else if(!two.getId().equals("Ace")) {
	        score = two.getValue() +2;
            System.out.println("Your hand's score is: " + score);
	      } else {
	        score = three.getValue() +2;
            System.out.println("Your hand's score is: " + score);
	      }
	    }
	  }
	  
	}
	
	public void score() { //returns the score based on the hand value
		this.score = 0;
	    int aceCounter = 0;
	    for(int i = 0; i < this.cards_in_hand.size(); i++) {
	        if(cards_in_hand.get(i).getId() == "Ace"){
	          aceCounter += 1;
	        }
	        score += this.cards_in_hand.get(i).getValue();
	    }
	    if(aceCounter > 0){ //Ace Condition
	      for(int j = 0; j < aceCounter; j++){
	        if(score > 11){
	          score += 1;
	        }
	        else{
	          score += 11;
	        }
	      }
	    }
	}
	public void afterHit() {
	  
	  
	}
}
