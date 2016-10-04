package simpleCraps;

public class simpleCraps3 {

		   public static void main(String[] args) {
			   
		      int dice;  // Initial roll
		      		      
		      int loss = 0;
		      int win = 0;
		      
		      boolean gameover = false;
		      int point = 0;
		         
		         
		     for (int i = 0;  i<135; i = win + loss) {
		    	  
		    	  dice = (int)(6.0*Math.random() + 1.0) +
				             (int)(6.0*Math.random() + 1.0);
				      
		      if (dice == 2 || dice == 3 || dice == 12) {
		    	 loss++; 
		    	 gameover = true;
		         System.out.println("You lose for rolling a " + dice);
		      }
		      
		      else if (dice == 7) {
		    	  win++;
		    	  gameover = true;
		          System.out.println("You win for rolling a " + dice);
		         
		      }
		      else { 
		    	  
		         int pointNumber = dice; // Rolled a number other than 2, 3, 7, or 12 so you have a Point Number
		         System.out.println("Your Point Number is: " + pointNumber);
		         gameover = false;
		         
		         while (gameover == false) {  // Roll additional times until you roll a 7 and lose or roll the Point Number and win the game
		            dice = (int)(6.0*Math.random() + 1.0) +
		                   (int)(6.0*Math.random() + 1.0);
		            System.out.println("\nYour next roll is a " + dice);
		         

		            if (dice == pointNumber && dice != 7) {
		               win++;
		               gameover = true;
		               System.out.println("\nYou Win!! Congratulations!!");
		                // Win the game, Game Over
		            }
		            
		            if (dice == 7) {
		               loss++;
		               gameover = true;
		               System.out.println("\nYou rolled a 7 and lost :( Better luck next time..");
		                // Lose the game, Game Over
		             
		            }
		            
		            if (dice != 7 && dice != pointNumber) {
		            	System.out.println("You rolled a number other than 7 or the Point Number, please continue rolling.."); // Statement for continuing the game
		            	point = 0;
		            	break;
		            }
		            
		         }
		         
		         
		         
		      }
		   }
		      
		      System.out.println("Wins: " + win + "\n");
		      System.out.println("Losses: " + loss + "\n");
		   }   
	      
	}

