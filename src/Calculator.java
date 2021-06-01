/*
 * Zachary n Thomas 
 * CS/IS 139
 * Assignment 12
 */


import java.util.ArrayList;

public class Calculator {

	private double left_operand = 0;
	private double right_operand = 0;
	
	public int left_sign = 1;
	public int right_sign = 1;
	
	private double solution = 0;
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
	
	public void AddOperandDigit(int input_value)
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
	
	private double DigitstoValue(ArrayList<Integer> input_digits, int sign) 
	{
		ArrayList<Integer> integer_values = new ArrayList<Integer>();
		ArrayList<Integer> fractional_values = new ArrayList<Integer>();
		
		// sort input digits into integers and fractionals. ie values before and values after decimal point
		Boolean fractional = false;
		for(int i = 0; i < input_digits.size(); ++i)
		{
			// if decimal point
			if (input_digits.get(i) == -1)
			{
				fractional = true; 
				continue;
			}
			
			if (fractional == false)
			{
				integer_values.add(input_digits.get(i));
			}else {
				fractional_values.add(input_digits.get(i));
			}
			
		}

		// convert integer values. ie value before decimal
		double converted_integer_value = 0;
		for(int i = 0; i < integer_values.size(); ++i)
		{

			converted_integer_value += integer_values.get(i) * (Math.pow(10, (integer_values.size() - (i + 1)))); 
			// System.out.println("Calculator::ConvertDigitstoValue -> converted value: " + converted_integer_value + "input integer value:" + integer_values.get(i));
		}
		
		// convert fractional values. ie value after decimal 
		double converted_fractional_value = 0;
		for(int i = fractional_values.size() - 1; i >= 0; --i)
		{
			converted_fractional_value += fractional_values.get(i) * (Math.pow(10, (-1 * (i + 1)))); 
			// System.out.println("Calculator::ConvertDigitstoValue -> converted fractional value: " + converted_fractional_value + " input fractional value:" + fractional_values.get(i));
		}
		
		System.out.println("Calculator::DigitstoValue -> integer value: " + converted_integer_value);
		System.out.println("Calculator::DigitstoValue -> fractional value: " + converted_fractional_value);
		
		double converted_value = converted_integer_value + converted_fractional_value;
		converted_value *= sign;
		System.out.println("Calculator::DigitstoValue -> output value: " + converted_value);
		return converted_value;
	}
	
	private ArrayList<Integer> ValuetoDigit(double operandValue) 
	{
		ArrayList<Integer> outputArray = new ArrayList<Integer>();
		
		String strValue = String.valueOf(operandValue);
		
		for(int c = 0; c < strValue.length(); c++)
		{
			// if decimal point
			if(strValue.charAt(c) == '.')
				outputArray.add(-1); 
			else
				outputArray.add(Integer.parseInt(Character.toString(strValue.charAt(c))));
		}
	
		System.out.print("Calculator::ValuetoDigit -> digit: "); outputArray.forEach(d -> System.out.print(d + ", ")); System.out.println();
		return outputArray;
	}
	
	public double ConvertLeftOperand() 
	{
		double convertedValue = DigitstoValue(this.input_digits_left_operand, this.left_sign); 
		System.out.println("Calculator::ConvertLeftOperand -> left operand: " + convertedValue);
		return convertedValue; 
	}
	
	public double ConvertRightOperand()
	{
		double convertedValue = DigitstoValue(this.input_digits_right_operand, this.right_sign);
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
	
	public double Addition(double left_operand, double right_operand)
	{
		System.out.println("Calculator::Addition-> equation: " + left_operand + " + " + right_operand + " = " + (left_operand + right_operand));
		return left_operand + right_operand;
	}
	
	public double Subtraction(double left_operand, double right_operand)
	{
		System.out.println("Calculator::Addition-> equation: " + left_operand + " - " + right_operand + " = " + (left_operand - right_operand));
		return left_operand - right_operand;
	}
	
	public double Multiplication(double left_operand, double right_operand)
	{
		System.out.println("Calculator::Multiplication-> equation: " + left_operand + " * " + right_operand + " = " + (left_operand * right_operand));
		return left_operand * right_operand;
	}
	
	public double Division(double left_operand, double right_operand)
	{
		System.out.println("Calculator::Division-> equation: " + left_operand + " / " + right_operand + " = " + (left_operand / right_operand));
		return left_operand / right_operand;
	}
	
	public double Modulus(double left_operand, double right_operand) {
		System.out.println("Calculator::Modulus-> equation: " + left_operand + " % " + right_operand + " = " + (left_operand / right_operand));
		return left_operand % right_operand;
	}
	
	public double SolveEquation()
	{
		
		this.left_operand = ConvertLeftOperand();
		this.right_operand = ConvertRightOperand(); 
			
		double solution = 0;
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
