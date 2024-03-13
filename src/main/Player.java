package main;


public class Player {
	String name;
	int bankIndex;
	boolean isOut = false;
	int playerNumber;
	
	Player(int newPlayerNumber, int newBankIndex){
		this.playerNumber = newPlayerNumber;
		this.bankIndex = newBankIndex;
	}
	
	public int getNumber() {
		return this.playerNumber;
	}
	
	public boolean hasLost() {
		return this.isOut;
	}
	
	public void gameOver() {
		this.isOut = true;
	}
	
	public int getBankRollIndex(){
		return this.bankIndex;
	}
	
	public void setBankRollIndex(int newIndex) {
		this.bankIndex = newIndex;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
}//End of class
 