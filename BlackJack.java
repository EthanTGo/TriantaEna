package specific;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BlackJack {
	// attributes
	Deck deck;
	Dealer dealer; // I believe the dealer
	boolean cashout;
	boolean canDouble;
	boolean still_playing;
	Players player1;

	public BlackJack() {
		this.deck = new Deck();
		this.dealer = new Dealer();
	}
	
	///check

	public void playGame() {
		// startGame();
		// this.turn = 0;
		// after initializing players, we keep playing until all the players are out...
		cashout = true;
		while (this.player1.balance.getMoney() > 0 && cashout) {// until we have player is out of money or wants to cashout
			System.out.println("Your current balance is: $" + this.player1.getBalance());
			System.out.println("How much do you want to bet?");
			Boolean validBet = true;
			int bet = 0;
			////////////////////////////////////////////////////////////////
			while (validBet) {
				Scanner scan = new Scanner(System.in);
				bet = scan.nextInt();
				if (bet > 0 && bet <= this.player1.balance.getMoney()) {
					validBet = false;
				} else {
					System.out.println("invalid bet!");
				}
			}
			this.player1.balance.decreaseBalance(bet);
			System.out.println("Your balance after bet is " + this.player1.getBalance());

			/////////////////////////////////////////////////////////////////
			System.out.println(bet + " is a valid bet!");
			dealer.getdhand(deck); // sets dealers dhand
			dealer.printintialdhand(); //print intial hand

			// set Player hand1

			Hand initial_hand = new Hand(bet); // First hand with the amount
			initial_hand.getPlayerHand(deck);
			System.out.print("Player's Hands are ");
			initial_hand.printHand();
			initial_hand.printScore();
			this.player1.totalHand.add(initial_hand); //Adds the initial hand to the hand of the player

			if (initial_hand.score == 21) {
				this.player1.still_playing = false;
				System.out.println("BlackJack!");
				dealer.dstand(21, deck);
				checkWhoWin(this.player1.totalHand.get(0), dealer); // WORK ON THIS
			} else {
				this.player1.still_playing = true; // continue
			}
			// This is where the game actually beginss
			if (this.player1.still_playing) {
				int score_to_beat = 0;
				for (int i = 0; i < this.player1.totalHand.size(); i++) {
					this.player1.totalHand.get(i).valid = true;
					choose(this.player1.totalHand.get(i));
					if (score_to_beat < this.player1.totalHand.get(i).score) {
						score_to_beat = this.player1.totalHand.get(i).score;
					}
				}

				dealer.dstand(score_to_beat, deck); // dealer tries to beat that score or not if 17 or more already
				// after player turn is over it's dealer's turn
				for (int i = 0; i < this.player1.totalHand.size(); i++) {
					checkWhoWin(this.player1.totalHand.get(i), dealer);
				}
			}
			
			this.player1.totalHand.clear(); //resets the hand
				

			if (this.player1.balance.getMoney() == 0) {
				changeCashout();
			} else {
				System.out.println("Do you want to cash out? (Yes or No)");
				Scanner x = new Scanner(System.in);
				String ans = x.next();
				if (ans.equals("Yes")) {
					changeCashout();
				} else {
					reset(this.player1);
				}
			}
		}
	}

	private void changeCashout() { // Exiting the game()
		cashout = false;
	}
	
	private void choose(Hand check) {
		while (check.valid == true) {
			if (check.checkSplit()) {
				split_input(check);
			} else {
				normal_input(check);
			}
		}
	}
	

	private void split_input(Hand check) {
		Scanner scan = new Scanner(System.in);
		System.out.println(" What action do you want to do with hand:" + check.toString()
				+ "?: 0 for Hit, 1 for Stand, 2 for DoubleUp, 3 for Split:");
		int input = scan.nextInt();
		switch (input) {
		case 0:
			Hit(check);
			break;
		case 1:
			Stand(check);
			break;
		case 2:
			if (this.player1.getBalance() > check.bet_inplaced) {
				DoubleUp(check);
				break;
			} else {
				System.out.println("Not enough money");
			}
		case 3:
			if (this.player1.getBalance() > check.bet_inplaced) {
				Split(check);
				break;
			} else {
				System.out.println("Not enough money");
			}
		default:
			System.out.println("Enter a correct value");
		}
	}

	private void normal_input(Hand check) {
		Scanner scan = new Scanner(System.in);
		System.out.println(" What action do you want to do for hand 1 ?: 0 for Hit, 1 for Stand, 2 for DoubleUp");
		int input = scan.nextInt();
		switch (input) {
		case 0:
			Hit(check);
			break;
		case 1:
			Stand(check);
			break;
		case 2:
			canDouble = false;
			DoubleUp(check);
			if (canDouble) {
				break;
			} else {
				Stand(check);
				break;
			}
		default:
			System.out.println("Enter a correct value");
		}
	}

	// Copied from the TicTacToe game :)
	public void createPlayer() { // initalize player and its balance
		Scanner scan = new Scanner(System.in);
		System.out.println("enter your name: ");
		String name = scan.nextLine();
		this.player1 = new Players(name);
	}

	private void reset(Players p) { // a function that resets everything
		this.deck = new Deck();
		dealer.dealerReset();
	}

	// todo
	// Logic components/Actions
	public void Hit(Hand current_hand) {// 0 for hit
		// remove a card from deck
		// add a card to Player
		Cards hit = this.dealer.getCard(this.deck);
		current_hand.add(hit); // adds the card to the hand
		// score is automatically added in
		current_hand.printHand();
		current_hand.printScore();
		quickcheck(current_hand);
	}

	public void Stand(Hand a) { // 1 for stand
		// This means you're out of the round but you're score is still there
		a.valid = false;
	}

	public void DoubleUp(Hand check) { // 2 for Doubleup
		// the blackjack function will give me that hand
			check.bet_inplaced = check.bet_inplaced * 2;
			System.out.println(check.toString() + "'s new bet is " + check.bet_inplaced);
			Hit(check);
			if(check.score < 21) {
				Stand(check);
			}
//		int amount = check.amount_on_bet * 2; // (1000) = (500*2)
//		int currentAmount = check.balance.getMoney(); // (1000)
//		int afterInitBet = currentAmount - check.amount_on_bet; // (500)
//		if (amount > currentAmount) {
//			System.out.println("Invalid Call: Not enough Balance");
//			canDouble = true;
//		} else {
//			check.amount_on_bet = amount;
//			System.out.println(check.name + "'s new bet is " + check.amount_on_bet);
//			Hit(check);
//		}
	}

	public void Split(Hand check) {
		// Split -> the player has to split their hand into two, can we just make
		// another player???
		Cards temp = check.findDuplicate(); //cards
		check.remove(temp);
		Hand second = new Hand(check.bet_inplaced);
		second.add(temp);
		second.bet_inplaced = check.bet_inplaced;
		this.player1.balance.decreaseBalance(check.bet_inplaced);
		this.player1.totalHand.add(second);
		check.score();
		second.score(); //makes sure we reset the score for both hand
		Hit(check);
		Hit(second); //add the extra card
		System.out.println("Created two new hand from the split ");
		System.out.println("First hand is ");
		check.printHand();
		check.printScore();
		System.out.println("Second hand is ");
		second.printHand();
		second.printScore();
	}

	public void checkWhoWin(Hand hand, Dealer d) { // Win tester
		System.out.println("For hand:");
		hand.printHand();
		if (hand.score == 21 && d.getScore() == 21) { //case both player are blackjack but not the instant blackjack
			System.out.println("Draw! No money lost or gained!");
			this.player1.still_playing = false;
		} 
		else if (hand.score > 21) {
			System.out.println(this.player1.name + " has lost " + hand.getBet() + "!");
			System.out.println("Your Balance: " + this.player1.balance.getMoney());
			this.player1.still_playing = false;
		}
		else if (d.getScore() > 21) {
			this.player1.balance.addBalance(hand.bet_inplaced * 2);
			System.out.println(this.player1.name + " has won " + hand.bet_inplaced + "!");
			System.out.println("Your Balance: " + this.player1.balance.getMoney());
			this.player1.still_playing = false;
		} else if (d.getScore() > hand.score) {
			System.out.println(this.player1.name + " has lost " + hand.bet_inplaced + "!");
			System.out.println("Your Balance: " + this.player1.balance.getMoney());
			this.player1.still_playing = false;
		} else if (hand.score > d.getScore()) {
			this.player1.balance.addBalance(hand.bet_inplaced * 2);
			System.out.println(this.player1.name + " has won " + hand.bet_inplaced + "!");
			System.out.println("Your Balance: " + this.player1.balance.getMoney());
			this.player1.still_playing = false;
		} else if (hand.score == d.getScore()) { //Case where dealer and player have same value
			System.out.println("Draw! No money lost or gained!");
			this.player1.still_playing = false;
		}
	}

	public void quickcheck(Hand current_hand) {
		if (current_hand.score > 21) {
			this.player1.balance.decreaseBalance(current_hand.bet_inplaced);
			System.out.println(this.player1.name + " has lost " + current_hand.bet_inplaced + "!");
			System.out.println("Your Balance: " + this.player1.balance.getMoney());
			this.player1.still_playing = false;
		}
	}
}
