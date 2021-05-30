/*
 * Zachary n Thomas 
 * CS/IS 139
 * Assignment 12
 */


import java.util.ArrayList;

public class Calculator {

	private int left_operand = 0;
	private int right_operand = 0;
	
	public int left_sign = 1;
	public int right_sign = 1;
	
	private int solution = 0;
	private ArrayList<Integer> input_digits_left_operand = new ArrayList<Integer>(); 
	private ArrayList<Integer> input_digits_right_operand = new ArrayList<Integer>();
	private Operator operator = Operator.Addition; 
	
	public Operand activeOperand = Operand.Left;
	
	
	Calculator()
	{
		System.out.println("Initializing Calculator object"); 
	}
	
	public void IntegerArrayListToString(ArrayList<Integer> input_arraylist)
	{
		input_arraylist.forEach(digit ->System.out.print(digit));
		System.out.println("");	
	}
	
	public void AddOperatorDigit(int input_value)
	{
		if (this.activeOperand == Operand.Left) {
			this.input_digits_left_operand.add(input_value);
			System.out.print("Calculator::AddOperatorDigit -> adding " + input_value + "--> ");
			this.input_digits_left_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
		else {
			this.input_digits_right_operand.add(input_value);
			System.out.print("Calculator::AddOperatorDigit -> adding " + input_value + "--> ");
			this.input_digits_right_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
	}
	
	public void SetOperandSign()
	{
		if (this.activeOperand == Operand.Left)
		{
			this.left_sign *= -1;
			System.out.println("Calculator::SetOperandSign -> left sign: " + this.left_sign);
		}
		else {
			this.right_sign *= -1;
			System.out.println("Calculator::SetOperandSign -> left sign: " + this.right_sign);
		}
	}
	
	private int DigitstoValue(ArrayList<Integer> input_digits_list, int sign) 
	{
		
		int converted_value = 0;
		for(int i = 0; i < input_digits_list.size(); ++i)
		{
			converted_value += input_digits_list.get(i) * (Math.pow(10, (input_digits_list.size() - (i + 1)))); 
			// System.out.println("Calculator::ConvertDigitstoValue -> converted value: " + converted_value + " input value:" + input_digits_list.get(i));
		}
		
		converted_value *= sign;
		System.out.println("Calculator::ConvertDigitstoValue -> output value: " + converted_value);
		return converted_value;
	}
	
	private ArrayList<Integer> ValuetoDigit(int operandValue) 
	{
		ArrayList<Integer> outputArray = new ArrayList<Integer>();
		
		String strValue = String.valueOf(operandValue);
		
		for(int c = 0; c < strValue.length(); c++)
		{
			outputArray.add(Integer.parseInt(Character.toString(strValue.charAt(c))));
		}
	
		System.out.print("Calculator::ValuetoDigit -> digit: "); outputArray.forEach(d -> System.out.print(d + ", ")); System.out.println();
		return outputArray;
	}
	
	public int ConvertLeftOperand() 
	{
		int convertedValue = DigitstoValue(this.input_digits_left_operand, this.left_sign); 
		System.out.println("Calculator::ConvertLeftOperand -> left operand: " + convertedValue);
		return convertedValue; 
	}
	
	public int ConvertRightOperand()
	{
		int convertedValue = DigitstoValue(this.input_digits_right_operand, this.right_sign);
		System.out.println("Calculator::ConvertRightOperand -> right operand: " + convertedValue);
		return convertedValue;
	}
	
	public void SelectOperator(Operator input_operator)
	{
		this.operator = input_operator; 
		this.activeOperand = Operand.Right;
		System.out.println("Calculator::SelectOperator -> " + input_operator);
	}

	
	public void DeleteDigit()
	{
		if(this.activeOperand == Operand.Left)
		{
			if (this.input_digits_left_operand.size() > 0) 
			{
				this.input_digits_left_operand.remove(this.input_digits_left_operand.size() - 1);
			}
			System.out.print("Calculator::DeleteDigit -> input_digits_left_operand: "); this.input_digits_left_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
		else 
		{
			if (this.input_digits_right_operand.size() > 0) 
			{
				this.input_digits_right_operand.remove(this.input_digits_right_operand.size() - 1);
			}
			System.out.print("Calculator::DeleteDigit -> input_digits_right_operand: "); this.input_digits_right_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
	}
	
	public void Clear() 
	{
		System.out.println("Calculator::Clear -> resetting instance variables");
		
		this.left_operand = 0; 
		this.right_operand = 0; 
		this.left_sign = 1; 
		this.right_sign = 1;	
		this.input_digits_left_operand.clear();
		this.input_digits_right_operand.clear();
		
		this.activeOperand = Operand.Left;
	}
	
	public int Addition(int left_operand, int right_operand)
	{
		System.out.println("Calculator::Addition-> equation: " + left_operand + " + " + right_operand + " = " + (left_operand + right_operand));
		return left_operand + right_operand;
	}
	
	public int Subtraction(int left_operand, int right_operand)
	{
		System.out.println("Calculator::Addition-> equation: " + left_operand + " - " + right_operand + " = " + (left_operand - right_operand));
		return left_operand - right_operand;
	}
	
	public int Multiplication(int left_operand, int right_operand)
	{
		System.out.println("Calculator::Multiplication-> equation: " + left_operand + " * " + right_operand + " = " + (left_operand * right_operand));
		return left_operand * right_operand;
	}
	
	public int Division(int left_operand, int right_operand)
	{
		System.out.println("Calculator::Division-> equation: " + left_operand + " / " + right_operand + " = " + (left_operand / right_operand));
		return left_operand / right_operand;
	}
	
	public int Modulus(int left_operand, int right_operand) {
		System.out.println("Calculator::Modulus-> equation: " + left_operand + " % " + right_operand + " = " + (left_operand / right_operand));
		return left_operand % right_operand;
	}
	
	public int SolveEquation()
	{
		
		this.left_operand = ConvertLeftOperand();
		this.right_operand = ConvertRightOperand(); 
			
		int solution = 0;
		switch(this.operator)
		{
		case Addition: 
			solution = Addition(this.left_operand, this.right_operand);
			break;
		case Subtraction: 
			solution = Subtraction(this.left_operand, this.right_operand); 
			break;
		case Multiplication: 
			solution = Multiplication(this.left_operand, this.right_operand); 
			break; 		
		case Division: 
			solution = Division(this.left_operand, this.right_operand);
			break;
		case Modulus:
			solution = Modulus(this.left_operand, this.right_operand);
			break;
		default: 
			break;
		}
		
		// Set for next round of input
		this.solution = solution;
		System.out.println("Calculator::SolveEquation-> solution: " + solution);
		
		ArrayList<Integer> temp_input_digits_left_operand;
		int temp_left_sign;
		if(solution < 0)
		{
			temp_input_digits_left_operand = ValuetoDigit(-1 * solution);
			temp_left_sign = -1;	
		}
		else
		{
			temp_left_sign = 1;
			temp_input_digits_left_operand = ValuetoDigit(solution);	
		}
		
		Clear(); 
		this.left_sign = temp_left_sign; 
		this.input_digits_left_operand = temp_input_digits_left_operand;
		
		return this.solution;
	}
	
	
	
}
