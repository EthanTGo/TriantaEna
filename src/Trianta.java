import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Trianta {
	Deck deck;
	Players banker; //Marker to current Banker
	private boolean round_still_in_progress;
	ArrayList<Players> players_in_game = new ArrayList<Players>(); //List all the players except the Banker
	
	
	public Trianta() {
		this.deck = new Deck();
		//Initialize Players + Dealers	
	}
	public void createBanker() {
	  Scanner scan = new Scanner(System.in);
      System.out.println("Banker, please enter your name:");
      String banker_name = scan.nextLine();
      this.banker = new Players(banker_name, true);
	}
	
	public void createPlayers() { // initialize player and its balance
	    Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of Players");
		int num_players = scan.nextInt();
		System.out.println("Enter the initial balance for all Players");
		int balance = scan.nextInt();
		for(int i = 0; i < num_players; i++) {
		    int x = i;
		    System.out.println("Player " + (x+1));
			System.out.println("enter your name: ");
			String name = scan.next();
			this.players_in_game.add(new Players(name,balance));
		}
		banker.initalizeBalance(3 * balance);
		System.out.println("Banker's Starting balance is "+ banker.getBalance());
	}
	
	public void playGame() {
		while(this.players_in_game.size() > 0) {
			playRound();
		}
	}
	
	
	public void playRound() {
	    Deck d = new Deck();
	    banker.resetHand();
	    for(int i = 0; i < this.players_in_game.size(); i++) {
	    	players_in_game.get(i).resetHand();
	    }
		round_still_in_progress = true; 
		while (round_still_in_progress) { //for every round
		  Iterator iter = players_in_game.iterator();
		  while(iter.hasNext()) { //goes thru one round
		    //Deal one card to each Player
		    Players current = (Players) iter.next();
		    current.getPlayersInitCard(d);
		  }
		  banker.getBankerInitCard(d);
		  //Get Player initial bets
		  Iterator ite = players_in_game.iterator();
		  while(ite.hasNext()) { //place initial bets... if player chooses fold, there bet will be zero
            Players current = (Players) ite.next();
            current.firstBet();
          }
		  // each player will now receive two cards face up and one card face down
          Iterator it = players_in_game.iterator();
          while(it.hasNext()) {
            Players current = (Players) it.next();
            if(current.getCurrentBet() != 0) {
              current.newCards(d);
            }
          }
          //Time for hit/stand phase 
          Iterator i = players_in_game.iterator();
          while(i.hasNext()) { 
            Players current = (Players) i.next();
            if(current.getCurrentBet() != 0) { //
              Cards bankerCard = banker.getHand().cards_in_hand.get(0);
              System.out.println("The Banker's card is a " + bankerCard.getId());
              if(current.phaseTwo(d)) {
                //if true, player has lost
                int current_bet = current.getCurrentBet();
                banker.updatebalance(current_bet); // player's bet gets added to banker's balance
                current.changeCurrentBet(current_bet); //should decrement player's bet to zero
              }
            }
          }
          //add cards to banker
          System.out.println("\n");
          System.out.println("Cards will now be added to the Banker's hand!");
          banker.addToBankerHand(d);
          System.out.println("\n");
          
          //phase check for all remaining players 
          Iterator y = players_in_game.iterator();
          System.out.println("We will now compare the Banker's score to player's scores");
          while(y.hasNext()) { 
            Players current = (Players) y.next();
            if(current.getCurrentBet() != 0) { //
              System.out.println(current.getName() + "based on your score of " + current.getHand().score);
              System.out.println("debug: current score "+ current.getHand().score + " and banker score "+ banker.getHand().score);
              if(current.getHand().score > banker.getHand().score) { //Player wins
                System.out.println("Your score is higher than the bankers! You have won!");
                int Bvalue = -1 * current.getCurrentBet();
                int Cvalue = 2 * current.getCurrentBet();
                banker.updatebalance(Bvalue);
                current.updatebalance(Cvalue);
                System.out.println("You have won "+ current.getCurrentBet());
                current.changeCurrentBet(0);
                System.out.println("Your new balance is "+ current.getBalance());
                System.out.println("The Banker's new balance is " + banker.getBalance());
                System.out.println("\n");
              } else if(current.getHand().score < banker.getHand().score & banker.getHand().score > 31) {
            	  System.out.println("Your score is lower than 31! You have won!");
                  int Bvalue = -1 * current.getCurrentBet();
                  int Cvalue = 2 * current.getCurrentBet();
                  banker.updatebalance(Bvalue);
                  current.updatebalance(Cvalue);
                  System.out.println("You have won "+ current.getCurrentBet());
                  current.changeCurrentBet(0);
                  System.out.println("Your new balance is "+ current.getBalance());
                  System.out.println("The Banker's new balance is " + banker.getBalance());
                  System.out.println("\n");
              }
              else { //Banker wins
                System.out.println("Your score is lower than the bankers! You have lost!");
                int Bvalue = current.getCurrentBet();
                banker.updatebalance(Bvalue);
                System.out.println("You have lost "+ current.getCurrentBet());
                current.changeCurrentBet(0);
                System.out.println("Your balance is "+ current.getBalance());
                System.out.println("The Banker's new balance is " + banker.getBalance());
                System.out.println("\n");
              }
            }
          }
          //check if we need game should continue (ask player if they want to continue playing)
          Iterator next = players_in_game.iterator();
          while(next.hasNext()) {
            Players current = (Players) next.next();
            current.continuePlaying();
          }
          //remove players with no balance
          Iterator c = players_in_game.iterator();
          while(c.hasNext()) {
            Players current = (Players) c.next();
            if(current.getBalance() == 0) {
              c.remove();
            }
          }
          if(players_in_game.isEmpty()) {
            round_still_in_progress = false;
          }
          if(banker.getBalance() <= 0) {
            round_still_in_progress = false;
          }
         }
          
	}
	
	public void askPlayers() {
		for(int i = 0; i < this.players_in_game.size(); i++) {
			players_in_game.get(i).Play();
		}
	}
	
	
	
	
	
}
