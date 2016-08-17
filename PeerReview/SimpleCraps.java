package simpleCraps;

public class SimpleCraps {

   public static void main(String[] args) {
	   
	    int userWin = 0;
	    int userLoss = 0;

	    while (true) {
	        System.out.println("Wins: " + userWin + " / Losses: " + userLoss);
	        
      int dice;  // Initial roll
      dice = (int)(6.0*Math.random() + 1.0) +
             (int)(6.0*Math.random() + 1.0);

      if (dice == 2 || dice == 3 || dice == 12) {
         System.out.println("You lose for rolling a " + dice);
         userLoss++;
         break;
      }
      
      else if (dice == 7) {
         System.out.println("You win for rolling a " + dice);
         userWin++;
         break;
      }
      else { 
    	  
         int pointNumber = dice; // Rolled a number other than 2, 3, 7, or 12 so you have a Point Number
         System.out.println("Your Point Number is: " + pointNumber);
         
         while (true) {  // Roll additional times until you roll a 7 and lose or roll the Point Number and win the game
            dice = (int)(6.0*Math.random() + 1.0) +
                   (int)(6.0*Math.random() + 1.0);
            System.out.println("\nYour next roll is a " + dice);
            
            if (dice == pointNumber) {
               System.out.println("\nYou Win!! Congratulations!!");
               userWin++;
               break; // Win the game, Game Over
            }
            
            if (dice == 7) {
            	
               System.out.println("\nYou rolled a 7 and lost :( Better luck next time..");
               userLoss++;
               break; // Lose the game, Game Over
                
            }
            
            else System.out.println("You rolled a number other than the Point Number or a 7, continue rolling.."); // Statement for continuing the game
            
         }
         
      }
   }
	

	}
}

