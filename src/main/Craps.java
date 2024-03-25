/**
 * Program Name:	Craps.java
 * Purpose:			Simulates the game of Craps
 * @author			Aaron Hylands, 0740727
 * Date:			April 1, 2024 (DUE)
 */

package main;

import java.util.*;

public class Craps {
	
	/* I originally declared these variables here when first 
	 *  making the program and decided to keep them here instead
	 *  of moving them into CrapsHelper. Additionally I decided
	 *  to use global variables instead 
	 */
	
	public static Die firstDie = new Die(6);
	public static Die secondDie = new Die(6);
	public static int rollTotal;
	
	public static Player winner;
	public static Player player1, player2, player3, player4, player5, player6;
	public static ArrayList<Player> playerArray = new ArrayList<Player>();
	public static ArrayList<Integer> bankRollArray = new ArrayList<Integer>();
	public static ArrayList<Integer> betAmountArray = new ArrayList<Integer>();
	public static int numberOfPlayers = 0;
	public static int pointRoll;
	public static int pointGoal;
	public static int comeOut;
	public static int actionAmount;
	public static int actionCoverage;
	public static String gameStage;
	public static int shooterID = 0;
	public static boolean didShooterWin;
	public static boolean didShooterCrap;
	public static boolean shootingForPoint;
	public static boolean gameIsDone;
	
	
	
	//Main method, these comments describe what the program is doing 
	// when in console mode and don't fully describe the CrapsUI
	// implemented functionality
	public static void main(String[]args) {
		
		//load UI
		//		 \  /  \  /
		//Disable \/this\/ via // to play the game in the console
		CrapsUI.initGameWindow();
		
		//Print the welcome message
		CrapsHelper.printWelcomeMessage();

		// Setup players:
		// Get the number of players
		// Instantiate the player objects into playerArray in
		//   accordance with the number of players
		// For each player object set a name and set a bankIndex
		//   to interface with each array with the correct index
		CrapsHelper.configurePlayerArray();
		
		// For each player, setup the starting amount of money
		//  inside of bankRollArray with the players bankIndex
		CrapsHelper.configureBankRollArray();
		
		if(CrapsUI.gameWindow.isVisible()) {
			CrapsUI.showBankDisplay();
		}
		// Printing bank roll totals and players for debug reasons
		CrapsHelper.printPlayerBankBalances();
		
		// Query if the players would like to see a brief description
		//  of the rules
		CrapsHelper.queryRules();
		
		// Start the main game loop
		do {
			
			// Print who the shooter is and get their bet
			actionAmount = CrapsHelper.getShooterBet();
			
			// Query other players to meet the action amount
			CrapsHelper.getOpponentBet();
			
			//Roll the dice
			CrapsHelper.rollComeOut();
			
			// Figure out the outcome of the come out roll
			CrapsHelper.comeOutResult();
			
			// Adjust bank balances according to the come out 
			//  or continue to point roll stage 
			CrapsHelper.adjustBankBalances();
			
			// Check to see if any of the players are out after the round of betting
			CrapsHelper.checkForBust();
			
			//Check for a winner after bank adjustment
			gameIsDone = CrapsHelper.checkForWinner();
			
			// Only run this part of the game if come out did not fail or succeed
			while (shootingForPoint) {
				// Roll again 
				CrapsHelper.rollForPoint();
				
				//check outcome
				CrapsHelper.pointRollResult();
				
				//adjust bankrollArray accordingly depending on the roll
				CrapsHelper.adjustBankBalances();
				
				// Check to see if any of the players are out after the round of betting
				CrapsHelper.checkForBust();
				
				//Check for a winner after bank adjustment
				gameIsDone = CrapsHelper.checkForWinner();
				
				
			}
			// Print totals before queryPass if the game has not ended
			if (!gameIsDone) {
				CrapsHelper.printMessageln("\nAfter this pass, here are the bankroll balances for everyone:");
				CrapsHelper.printPlayerBankBalances();
			}
			
			// Get next shooter if game is not done and the current
			//  shooter decides to pass
			if (!gameIsDone) {
				if (CrapsHelper.queryPass()) {
					CrapsHelper.getNextShooter();
				}
			}
			
		} while (!gameIsDone); // Game loop end
		
		// Launch celebration message for the winner
		CrapsHelper.launchCelebration();
		
		
	}//End of main method
} //End of class
 