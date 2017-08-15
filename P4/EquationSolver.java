import java.util.*;
import io.*;

/*WAS NOT ABLE TO FINISH AND IMPLEMENT CORRECTLY - PRODUCES VERY ODD RESULTS*/

public class EquationSolver
{
	/*This method kick-starts the parsing process by called parseInfixToPostfix with
	input equation. Returns the postfix answer*/
	public double solve(String equation)
	{
		double answer = evaluatePostfix(parseInfixToPostfix(equation));
		System.out.println(answer);
		return answer;
	}

	/*This method parses the equation given and, for each element, determines what kind of element
	it is (operand or an operator) and pushed them to the appropriate Abstract Data
	Type. Returns a postFixQueue*/
	public DSAQueue parseInfixToPostfix(String equation)
	{
		StringTokenizer st = new StringTokenizer(equation, "-*/+()", true);
		DSAStack opStack = new DSAStack();
		DSAQueue postFixQueue = new DSAQueue(100);
		String strtoken;
		char term;
		int count = 0;

		while(st.hasMoreTokens())
		{
			strtoken = st.nextToken();
			term = strtoken.charAt(0);

			if(term == '(')
			{
				Character opBrkt = new Character('(');
				opStack.push(opBrkt);
			}
			else if(term == ')')
			{
				Character clBrkt = (Character)opStack.peek();
				while(clBrkt.charValue() != '(')
				{
					postFixQueue.enqueue((Character)opStack.pop());
					clBrkt = (Character)opStack.peek();
				}
				opStack.pop();
			}
			else if (term == '+' || term == '-' || term == '*' || term == '/')
			{
				while(!opStack.isEmpty())
				{
					Character oprtr = (Character)opStack.peek();
					if((oprtr.charValue() != '(') && (precedenceOf(oprtr.charValue()) >= precedenceOf(term)))
					{
						postFixQueue.enqueue((Character)opStack.pop());
					}
				}
				Character chTerm = new Character(term);
				opStack.push(chTerm);
			}
			else
			{
				Double db = new Double(term);
				postFixQueue.enqueue(db);
			}
		}

		while (!opStack.isEmpty())
		{
			postFixQueue.enqueue((Character)opStack.pop());
		}

		return postFixQueue;
	}

	/*This method evaluates the postfix that resides in the
	postFixQueue that it is passed. Returns evaluated postfix*/
	public double evaluatePostfix(DSAQueue postFixQueue)
	{
		DSAStack operandStack = new DSAStack();
		Object item;
		Double resultWrap = null;

		while(!postFixQueue.isEmpty())
		{
			item = postFixQueue.dequeue();
			if(item instanceof Double)
			{
				operandStack.push(item);
			}
			else if(item instanceof Character)
			{
				double a = (Double)operandStack.pop();
				double b = (Double)operandStack.pop();
				resultWrap = new Double(executeOperation((Character)item, a, b));
				operandStack.push(resultWrap);
			}
		}
		return resultWrap;
	}


	/*This method sets the precedence of the operator in numerical form
	and returns it the called method. Returns precedence*/
	private int precedenceOf(char theOperator)
	{
		int precNum = 0;

		switch(theOperator)
		{
			case '+': case '-':
				precNum = 1;

			case '*': case '/':
				precNum = 2;
		}

		return precNum;
	}

	/*This method is tasked with basic arithmetic operations--which ones
	they are depends on the imported operator. Returns resultant*/
	private double executeOperation(char op, double op1, double op2)
	{
		double result = 0.0;

		switch(op)
		{
			case '-':
				result = op1 - op2;

			case '+':
				result = op1 + op2;

			case '*':
				result = op1 * op2;

			case '/':
				result = op1 / op2;
		}

		return result;
	}
}
