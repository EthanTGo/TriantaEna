import java.util.ArrayList;

public class Deck {
  ArrayList<Cards> deck =  new ArrayList<Cards> ();

  public Deck () {
      // (A) 1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, (J) 11, (Q) 12, (K) 13,
        for(int i = 1; i <= 13; i ++){
          for(int j = 0; j < 4; j++){
            Cards temp =  new Cards(i);
            deck.add(temp);
          }
        }
      }


  public void removeCard(int i){
      deck.remove(i);
  }

  public int sizeDeck(){
    return deck.size();
  }

  public Cards getCard(int i){
    Cards a = deck.get(i);
    return a;
  }

}
