package ssa;

	import java.util.Scanner;

	public class RPNCalculator {
	   
	    public static void main(String[] args) {
	    	
	    	//Initiate the variables and scanner for input
	        double n1, n2;
	        String operation;
	        Scanner scannerObject = new Scanner(System.in);

	        //To enter the first number in the RPN equation
	        System.out.println("Please enter the first number: ");
	        n1 = scannerObject. nextDouble();

	        //To enter the second number in the RPN equation
	        System.out.println("Please enter the second number: ");
	        n2 = scannerObject. nextDouble();

	        //To enter the operator character
	        Scanner op = new Scanner(System.in);
	        System.out.println("Please enter your operation: ");
	        operation = op.next();
	        
	       	///Create equation string to print the equation out in RPN form
	        String equation = String.valueOf(n1) + " " + String.valueOf(n2) + " " + operation;
	        
	        //Cases for the different operations that can be input and what statements are printed out for each
	        switch (operation)  {
	        case "+":
	            System.out.println("The answer to your equation is " + (n1 + n2));
	            System.out.println("--------------------------------------------------");
	            System.out.println("The RPN equation is: " + equation);
	            break;

	        case "-":
	            System.out.println("The answer to your equation is " + (n1 - n2));
	            System.out.println("--------------------------------------------------");
	            System.out.println(equation);
	            break;

	        case "/":
	            System.out.println("The answer to your equation is " + (n1 / n2));
	            System.out.println("--------------------------------------------------");
	            System.out.println(equation);
	            break;

	        case "*":
	            System.out.println("The answer to your equation is " + (n1 * n2));
	            System.out.println("--------------------------------------------------");
	            System.out.println(equation);
	            break;
	        
	        
	        }
	    }
	}