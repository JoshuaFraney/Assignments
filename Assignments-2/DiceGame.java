package assignment2;

public class DiceGame {

	public void run() {
	int dice1 = 0;
	int totalDice = 0;
	long seed = (new java.util.Date()).getTime();
	java.util.Random rnd = new java.util.Random(seed);
	
	    while(dice1 !=1){
	    	totalDice = totalDice + dice1;
	    	dice1 = rnd.nextInt(6)+1;
	    	System.out.println(dice1);
	    	
		}
	    System.out.println("Total: " + totalDice);
	}
		public static void main(String[] args) {
			DiceGame roll= new DiceGame();
			roll.run();

		}
	}
