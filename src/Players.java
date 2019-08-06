import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Players {
	private String name = "";
	private Balance balance = new Balance(0);
	private Hand hand;
	private boolean current_banker = false;
	private boolean still_playing = true;
	private int current_bet = 0;

	public Players(String name) {
		this.name = name;
	}
	
	public Players(String name, boolean is_banker) {
		this(name);
		this.current_banker = is_banker;
	}
	
	public Players(String name, int balance) {
		this(name);
		this.balance.addBalance(balance);
	};

//Player should be able to: Hit, Stand, Split, Double up, Bet
	public int Play() {
		boolean valid_number = false;
		int input = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand");
		while (!valid_number) {
			System.out.println("Enter a valid number");
			input = scan.nextInt();
			if (input >= 0 & input < 2) {
				valid_number = true;
			}
		}
		return input;
	}

	public void updatebalance(int i) {
		if (i > 0) {
			this.balance.addBalance(i);
		} else {
			this.balance.decreaseBalance(i);
		}
	}

	public Boolean checkPlayerBalance() {
		if (balance.getMoney() <= 0) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}
	
	public boolean getCurrent_Banker() {
		return this.current_banker;
	}
	
	public void setCurrent_Banker(boolean change) {
		this.current_banker = change;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public boolean isStill_playing() {
		return still_playing;
	}

	public void setStill_playing(boolean still_playing) {
		this.still_playing = still_playing;
	}

	public void printScore(Hand score, int num) {
		System.out.println("Player's score for hand " + num + " is " + score.score);
	}

	public void initalizeBalance(int i) {
		balance = new Balance(i);
	}

	public int getBalance() {
		return balance.getMoney();
	}
	public void getPlayersInitCard(Deck input) { // use to get dealer's inital hand (add 2 cards)
      Random rand = new Random();
      int random = rand.nextInt(input.sizeDeck());
      Cards a = input.getCard(random);
      input.removeCard(random);
      hand = new Hand();
      hand.add(a);
      System.out.println(name + " your initial card is a "+ a.getId() + "! (This card is only known to you)"); 
  }
	public void getBankerInitCard(Deck input) {
	  Random rand = new Random();
      int random = rand.nextInt(input.sizeDeck());
      Cards a = input.getCard(random);
      input.removeCard(random);
      hand = new Hand();
      hand.add(a);
      System.out.println("The banker's card is a "+ a.getId() + "! (Known to all players)");
	}
	public void firstBet() {
	  System.out.println(name + " based on your inital hand, do you want: 1. Bet 2. Fold");
	  Scanner scan = new Scanner(System.in);
	  int choice = scan.nextInt();
	  switch(choice) {
	    case 1:
	      System.out.println("You have selected to place a bet! How much would you like to bet?");
	      System.out.println("Note: your current balance is " + this.balance.getMoney());
	      int bet = scan.nextInt();
	      current_bet = bet;
	      this.balance.decreaseBalance(bet);
	      System.out.println("You have placed a bet of "+ current_bet);
	      System.out.println("Note: your balance now is " + this.balance.getMoney());
	      break;
	    case 2: // if a player has folded, then there bet will be zero and we can skip over them
	      break;
	  }
	}

}
