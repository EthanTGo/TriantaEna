import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Exec {
  public static boolean keepPlaying = true;
  //main method


    public static void main(String[] args) {
      while(keepPlaying){
        System.out.println("Welcome! Lets play Black Jack!");
        //initalize deck and dealer and game
        Trianta game = new Trianta();
        //initalize player and balance
        game.createPlayer();
        System.out.println("Player will start with a balance of $1000");
        game.player1.balance = new Balance(1000);
        System.out.println("Let's start Playing");
        game.playGame();
        //after initial game ends, ask if they want to continue?
        System.out.println("Do you want to start a new game: Yes or No");
        Scanner scan = new Scanner(System.in);
        String ans = scan.next();
        if(ans.equals("No")){
          System.exit(0);
        }
      }
    }

}
