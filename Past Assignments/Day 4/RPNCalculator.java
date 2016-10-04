package ssa;

import java.util.Stack;

public class RPNCalculator {

	//Defining the operators as constants
	public static final char PLUS = '+';
	public static final char MINUS = '-';
	public static final char TIMES = '*';
	public static final char DIVIDE = '/';
	public static final char EXPONENT = '^';
	
	public static void main(String[] args) {
		
		//Main function to enter the equation and set characters as Integers
		char[] chs = new char[] {'1','2','^','3','+'};
		Stack<Integer> stk = new Stack<Integer>();
		for(char ch : chs) {
			if(charIsNum(ch)) {
				stk.push(Character.getNumericValue(ch));
		
} else {
	//defining the integer variables
	int p2 = (int)stk.pop();
	int p1 = (int)stk.pop();
	switch(ch){
	
	//defining the different operators that can be input in the equation
	case PLUS:
	stk.push(p1 + p2);
	break;
	
	case MINUS:
	stk.push(p1 - p2);
	break;
	
	case TIMES:
	stk.push(p1 * p2);
	break;
	
	case DIVIDE:
	stk.push(p1 / p2);
	break;
	
	case EXPONENT:
	stk.push((int)Math.pow(p1, p2));
	
	//Print out the equation in RPN form and a line break
	System.out.printf("The RPN style equation is: ");
	System.out.println(chs);
	System.out.println("_____________________________________");
	System.out.println("                                         ");
	}
}
}
		
//Print out the solution to the problem
System.out.printf("The solution is %d\n", (int)stk.pop());

}
	
//Method to define the integer characters that can be input in the equation
static boolean charIsNum(char ch) {
	switch(ch) {
	case '0':
	case '1':
	case '2':
	case '3':
	case '4':
	case '5':
	case '6':
	case '7':
	case '8':
	case '9':
			return true;
		default:
			return false;
	}
}

	}


