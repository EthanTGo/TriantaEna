import java.util.ArrayList;
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
	
	public void createPlayer() { // initialize player and its balance
		Scanner scan = new Scanner(System.in);
		System.out.println("Banker, please enter your name:");
		String banker_name = scan.nextLine();
		this.banker = new Players(banker_name, true);
		
		System.out.println("Enter number of Players");
		int num_players = scan.nextInt();
		System.out.println("Enter the initial balance for all Players");
		int balance = -1;
		while(balance < 0) {
			balance = scan.nextInt();
		}
		
		for(int i = 0; i < num_players; i++) {
			System.out.println("enter your name: " + "Player " + (i+1));
			String name = scan.nextLine();
			this.players_in_game.add(new Players(name,balance));
		}
		
		banker.initalizeBalance(3 * balance);

	}
	
	
	public void playGame() {
		round_still_in_progress = true; 
		while (round_still_in_progress) {// until we have player is out of money or wants to cashout
			
		}
	}
	
	public void askPlayers() {
		for(int i = 0; i < this.players_in_game.size(); i++) {
			players_in_game.get(i).Play();
		}
	}
	
	
	
	
	
}
