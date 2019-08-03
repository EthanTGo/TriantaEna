import java.util.ArrayList;
import java.util.Random;

public class Hand {
	ArrayList<Cards> cards_in_hand;
	int score;
	boolean split;
	boolean valid; //if hand score past 21 then it is no longer valid
	int bet_inplaced;

	public Hand() {
		this.cards_in_hand = new ArrayList<Cards>();
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
		if (c.id == "Ace") {
			if (score > 11) {
				score += 1;
			} else {
				score += 11;
			}
		} else {
			score += value;
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
		sb.append("Your cards are: ");
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
}
