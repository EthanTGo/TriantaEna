package specific;

import java.util.ArrayList;
import java.util.Random;

public class Dealer {
	public Hand dhand; // dealerhands
	private Cards show;
	private Cards hidden;

	public Dealer() {
		dhand = new Hand();
	}

	public Cards getCard(Deck input) {
		Random rand = new Random();
		int random = rand.nextInt(input.sizeDeck());
		// check deck find card
		Cards a = input.getCard(random);
		// deck removeCard
		input.removeCard(random);
		// get card
		return a;
	}

	public int getScore() {
		return dhand.score;
	}
	
	public void printintialdhand() {
		System.out.println("One of the dealer's card is " + this.dhand.hand.get(0).getId());
	}

	public void printdhand() { //prints first card - always run for player to know
		System.out.print("The dealer's hand is: ");
		dhand.printHand();
	}

	public void getdhand(Deck input) { // use to get dealer's inital hand (add 2 cards)
		Random rand = new Random();
		int random = rand.nextInt(input.sizeDeck());
		Cards a = input.getCard(random);
		input.removeCard(random);
		show = a;
		dhand.add(show); // The first card to be shown
		int rand2 = rand.nextInt(input.sizeDeck());
		Cards b = input.getCard(rand2);
		input.removeCard(rand2);
		hidden = b;
		dhand.add(hidden); // the second card is hidden (as game)
	}

	public void dstand(int score_to_beat , Deck d) { //assuming score to beat is less than valid score
		updatescore();
		while (dhand.score < 17 && dhand.score < score_to_beat) {
			adddhand(d); // add to dealers hand
			printallcards(); // show all current cards (reveal hidden cards)
			updatescore(); // show score
		}
	}

	public void adddhand(Deck input) { // add one card
		Random rand = new Random();
		int random = rand.nextInt(input.sizeDeck());
		Cards a = input.getCard(random);
		input.removeCard(random);
		dhand.add(a);
	}

	public void printallcards() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dealer's cards are ");
		for (int i = 0; i < this.dhand.hand.size(); i++) {
			if (i == this.dhand.hand.size() - 1) {
				sb.append(dhand.hand.get(i).getId());
			} else {
				sb.append(dhand.hand.get(i).getId() + " and ");
			}
		}
		System.out.println(sb);

	}

	public void initalscore() {
		dhand.score();
		System.out.println("Dealer's score is " + dhand.score);
	}

	public void updatescore() { // we need to consider the Ace case
		int aceCounter = 0;
		dhand.score = 0;
		for (int i = 0; i < this.dhand.hand.size(); i++) {
			if (dhand.hand.get(i).getId() == "Ace") {
				aceCounter += 1;
			}
			dhand.score += dhand.hand.get(i).getValue();
		}
		if (aceCounter > 0) { // Ace Condition
			for (int j = 0; j < aceCounter; j++) {
				if (dhand.score > 11) {
					dhand.score += 1;
				} else {
					dhand.score += 11;
				}
			}
		}
		System.out.println("Dealer's score is " + dhand.score);
	}

	public void dealerReset() {
		dhand = new Hand();
	}
}
