import java.util.Scanner;

public class Infix_to_Postfix_Converter {

	/*works for single digit integers and if you type the infix in without spaces it looks better
	 * (but works as well with spaces)
	 */

	public static Queue<Character> sortExpression(String m){
		//Convert Infix to Postfix
		m.replaceAll("\\s+",""); //get rid of whitespaces

		Stack<Character> operatorStack = new Stack<Character>(); //Stack for operators
		Queue<Character> expressionQueue = new Queue<Character>(); //Queue for the whole expression

		for(int i = 0; i < m.length(); i++){
			if(m.charAt(i) == '+' || m.charAt(i) == '-' || m.charAt(i) == '*' || m.charAt(i) == '/'){
				boolean v = true;
				while(v){
					if (operatorStack.isEmpty()){
						operatorStack.push(m.charAt(i));
						v = false;
					}
					else{
						if((isBigger(m.charAt(i), operatorStack.getLast()))){
							operatorStack.push(m.charAt(i));
							v = false;
						}
						else{
							expressionQueue.push(operatorStack.pop());
						}
					}
				}
			}
			else{ //if it's not an operator it has to be a number
				expressionQueue.push(m.charAt(i));
			}
		}
		while(!operatorStack.isEmpty()){
			expressionQueue.push(operatorStack.pop());
		}
		return expressionQueue;
	}

	public static boolean isBigger(char a, char b){
		//compares the given operators in order to push/pop them in the right order
		if((a == '*' || a == '/') && (b == '+' || b == '-')){
			return true;
		}
		else{
			return false;
		}
	}

	public static Stack<Integer> calculate(String m){
		//calculates the result of the given postfix expression
	  m.replaceAll("\\s+","");
	  Stack<Integer> numbersStack = new Stack<Integer>();
	  int a;
	  int b;

	  for(int i = 0; i < m.length(); i++){
		  if(m.charAt(i) == '+'){
			  a = numbersStack.pop();
			  b = numbersStack.pop();
			  numbersStack.push(b + a);
	      }
	      else if(m.charAt(i) == '-'){
	    	  a = numbersStack.pop();
			  b = numbersStack.pop();
			  numbersStack.push(b - a);
	      }
	      else if(m.charAt(i) == '*'){
	    	  a = numbersStack.pop();
			  	b = numbersStack.pop();
			  numbersStack.push(b * a);
	      }
	      else if(m.charAt(i) == '/'){
	    	  a = numbersStack.pop();
			  	b = numbersStack.pop();
			  numbersStack.push(b / a);
	      }
	      else{
	    	  numbersStack.push((int)(m.charAt(i)-'0'));
	      }
	  }
	  return numbersStack;
	}


	public static void main(String[] args) {
		
		System.out.println("Please enter a mathematical expression as infix: ");
		Scanner exp = new Scanner (System.in);
		String expression = exp.nextLine();

		System.out.print("In postfix notation it looks like this: ");
		sortExpression(expression).printQueue();
		System.out.println();

		String result = sortExpression(expression).returnQueue().replaceAll("\\s+","");
		System.out.print("And the result is ");
		calculate(result).printStack();


	}

}
