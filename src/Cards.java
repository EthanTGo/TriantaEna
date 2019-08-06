
public class Cards {
  private int value;
  public int Avalue; // 1 or 11
  public String id;


  public Cards (int i){ //initializer of cards
    // (A) 1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, (J) 11, (Q) 12, (K) 13,
        if(i== 1) { //Ace Value 1 or 11
         // If the hand consists of one Ace, the player can choose to count it as a 1 or an 11. If the hand
         // consists of more than one Ace, only one Ace can count as 1
          value = 1;
          id = "Ace";
        } else if(i < 10){
          value = i;
          if(i == 2){
            id = "Two";
          }else if (i == 3){
            id = "Three";
          } else if (i==4){
            id = "Four";
          } else if (i ==5){
            id = "Five";
          } else if (i == 6){
            id = "Six";
          } else if (i == 7){
            id = "Seven";
          } else if (i == 8){
            id = "Eight";
          } else if (i == 9){
            id = "Nine";
          }
        } else {
          value = 10;
          if (i == 10){
            id = "Ten";
          }
          else if (i == 11){
            id = "Jack";
          }
          else if (i == 12) {
            id = "Queen";
          }
          else if (i == 13){
            id = "King";
          }
        }
  }

  public int getValue() {
      return this.value;
  }

  public String getId() {
    return id;
  }

  
  public boolean equals(Cards other) {
	  if(this.id.equals(other.id)) {
		  return true;
	  }
	  return false;
  }
}
