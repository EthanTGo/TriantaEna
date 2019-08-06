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
	    Deck d = new Deck();
		round_still_in_progress = true; 
		while (round_still_in_progress) { // until we have player is out of money or wants to cashout
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
          //Time for bets round two
          Iterator i = players_in_game.iterator();
          while(i.hasNext()) { 
            Players current = (Players) i.next();
            if(current.getCurrentBet() != 0) { //
              Cards bankerCard = banker.getHand().cards_in_hand.get(0);
              System.out.println("The Banker's card is a " + bankerCard.getId());
              current.secondBets(d);
            }
          }
		}
          
	}
	
	public void askPlayers() {
		for(int i = 0; i < this.players_in_game.size(); i++) {
			players_in_game.get(i).Play();
		}
	}
	
	
	
	
	
}
