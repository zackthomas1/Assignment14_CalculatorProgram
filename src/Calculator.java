import java.util.ArrayList;

public class Calculator {

	private int left_operand = 0;
	private int right_operand = 0;
	private int solution = 0;
	private ArrayList<Integer> input_digits_left_operand = new ArrayList<Integer>(); 
	private ArrayList<Integer> input_digits_right_operand = new ArrayList<Integer>();
	private Operator operator = Operator.Addition; 
	
	public Operand activeOperand = Operand.Left;
	
	
	Calculator()
	{
		System.out.println("Initializing Caculator object"); 
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
			System.out.println("Calculator::AddOperatorDigit -> adding " + input_value);
			this.input_digits_left_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
		else {
			this.input_digits_right_operand.add(input_value);
			System.out.println("Calculator::AddOperatorDigit -> adding " + input_value);
			this.input_digits_right_operand.forEach(digit -> System.out.print(digit + ", ")); System.out.println();
		}
	}
	
	private int ConvertDigitstoValue(ArrayList<Integer> input_digits_list) 
	{
		
		int converted_value = 0;
		for(int i = 0; i < input_digits_list.size(); ++i)
		{
			converted_value += input_digits_list.get(i) * (Math.pow(10, (input_digits_list.size() - (i + 1)))); 
			System.out.println("Calculator::ConvertDigitstoValue -> converted value: " + converted_value + " input value:" + input_digits_list.get(i));
		}
		
		System.out.println("Calculator::ConvertDigitstoValue -> output value: " + converted_value);
		return converted_value;
	}
	
	public int ConvertLeftOperand() 
	{
		System.out.println("Calculator::SetLeftOperandValue -> left operand: " + this.left_operand);
		return ConvertDigitstoValue(this.input_digits_left_operand); 
	}
	
	public int ConvertRightOperand()
	{
		System.out.println("Calculator::SetRightOperandValue -> right operand: " + this.right_operand);
		return ConvertDigitstoValue(this.input_digits_right_operand);
	}
	
	public void SelectOperator(Operator input_operator)
	{
		this.operator = input_operator; 
		this.activeOperand = Operand.Right;
		System.out.println("Calculator::SelectOperator -> ");
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
	
	public void Reset() 
	{
		System.out.println("Calculator::Reset -> resetingn instance variables");
		
		this.left_operand = 0; 
		this.right_operand = 0; 
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
		default: 
			break;
		}
		
		System.out.println("Calculator::SolveEquation-> solution: " + solution);
		this.solution = solution;
		return this.solution;

	}
	
}
