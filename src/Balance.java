
public class Balance {
  private int money;

  //Balance constructor/initializer
  public Balance(int num){
    this.money = num;
  }

  //getter
  public int getMoney(){
    return this.money;
  }
  
  //add or decrease balance
  public void addBalance(int amount) {
	  this.money += amount;
  }
  
  public void decreaseBalance(int amount) {
	  this.money -= amount;
  }

}
