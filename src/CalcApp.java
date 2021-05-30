/*
 * Zachary n Thomas 
 * CS/IS 139
 * Assignment 12
 */



import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CalcApp extends Application {
	
	// Create instance of Calculator object
	Calculator calculator = new Calculator();
	

	// Main Call 
	// ---------
	public static void main(String[] args)
	{
		System.out.println("CalcApp::main-> Opening Calc App");
		launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		int scene_width = 400; 
		int scene_height = 600;
		
		// create pane 
		GridPane inputsPane = new GridPane(); 	
		GridPane masterPane = new GridPane(); 
		
		// create Display
		// ----------------
		TextField display = new TextField("0");
		display.setEditable(false);
		display.setId("Display");
		masterPane.add(display, 0, 0,3,1); // column=0 row=0
		
		// create Number buttons
		// ---------------------
		//7-9 row 03
		Button btn07 = new Button("7"); 
		inputsPane.add(btn07, 0, 1); // column=0 row=1
		btn07.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn07 pressed");
			NumericalButtonPressed(7, display);
		});
		
		Button btn08 = new Button("8"); 
		inputsPane.add(btn08, 1, 1); // column=1 row=1
		btn08.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn08 pressed");
			NumericalButtonPressed(8, display);
		});
		
		Button btn09 = new Button("9"); 
		inputsPane.add(btn09, 2, 1); // column=2 row=1
		btn09.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn09 pressed");
			NumericalButtonPressed(9, display);
		});
		
		//4-6 row 02
		Button btn04 = new Button("4"); 
		inputsPane.add(btn04, 0, 2); // column=0 row=2
		btn04.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn04 pressed");
			NumericalButtonPressed(4, display);
		});
		
		Button btn05 = new Button("5"); 
		inputsPane.add(btn05, 1, 2); // column=1 row=2
		btn05.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn05 pressed");
			NumericalButtonPressed(5, display);
		});
		
		Button btn06 = new Button("6"); 
		inputsPane.add(btn06, 2, 2); // column=2 row=2
		btn06.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn06 pressed");
			NumericalButtonPressed(6, display);
		});
		
		//1-3 row 01
		Button btn01 = new Button("1"); 
		inputsPane.add(btn01, 0, 3); // column=0 row=3
		btn01.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn01 pressed");
			NumericalButtonPressed(1, display);
		});
		
		Button btn02 = new Button("2"); 
		inputsPane.add(btn02, 1, 3); // column=1 row=3
		btn02.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn02 pressed");
			NumericalButtonPressed(2, display);
		});
		
		Button btn03 = new Button("3"); 
		inputsPane.add(btn03, 2, 3); // column=2 row=3
		btn03.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn03 pressed");
			NumericalButtonPressed(3, display);
		});
		
		// =, 0, '.'
		// -----------------
		Button btn00 = new Button("0"); 
		inputsPane.add(btn00, 0, 4); // column = 0 row = 4
		btn00.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn00 pressed");
			NumericalButtonPressed(0, display);
		});
		
		Button btnDecimal = new Button("."); 
		inputsPane.add(btnDecimal, 1, 4); // column=1 row = 4
		btnDecimal.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnDecimal pressed");
		});
		
		Button btnEqual = new Button("="); 
		inputsPane.add(btnEqual, 2, 4); // column=2 row=4
		btnEqual.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			EqualButtonPressed(display);
		});
		
		// system memory buttons
		// -----------------
		Button btnClear = new Button("C"); 
		btnClear.setId("Operator");
		inputsPane.add(btnClear, 0, 0); // column=0 row=0
		btnClear.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnClear pressed");
			ClearButtonPressed(display);
		});	
		
		Button btnDelete = new Button("<-x"); 
		btnDelete.setId("Operator");
		inputsPane.add(btnDelete, 1, 0); // column=1 row=0
		btnDelete.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnDelete pressed");
			DeleteButtonPressed(display);
		});	
		
		// operator button
		// -----------------
		
		Button btnSign = new Button("-/+"); 
		btnSign.setId("Operator");
		inputsPane.add(btnSign, 2, 0); // column=2 row=0
		btnSign.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnSign pressed");
			SignButtonPressed(display);
		});	
		
		Button btnMode = new Button("%"); 
		btnMode.setId("Operator");
		inputsPane.add(btnMode, 3, 0); // column=3 row=0
		btnMode.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnMode pressed");
			OperatorButtonPressed(Operator.Modulus);
		});	
		
		Button btnDivide = new Button("/"); 
		btnDivide.setId("Operator");
		inputsPane.add(btnDivide, 3, 1); // column=3 row=2
		btnDivide.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnDivide pressed");
			OperatorButtonPressed(Operator.Division);
		});
		
		Button btnMultiply = new Button("X"); 
		btnMultiply.setId("Operator");
		inputsPane.add(btnMultiply, 3, 2); // column=3 row=2
		btnMultiply.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnMultiply pressed");
			OperatorButtonPressed(Operator.Multiplication);
		});
		
		Button btnSubtract = new Button("-"); 
		btnSubtract.setId("Operator");
		inputsPane.add(btnSubtract, 3, 3); // column=1 row=2
		btnSubtract.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnSubtract pressed");
			OperatorButtonPressed(Operator.Subtraction);
		});
		
		Button btnAdd = new Button("+"); 
		btnAdd.setId("Operator");
		inputsPane.add(btnAdd, 3, 4); // column=0 row=2
		btnAdd.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnAd pressed");
			OperatorButtonPressed(Operator.Addition);
		});
		
		masterPane.add(inputsPane, 0, 2); // column=0 row=2
		// create scene
		Scene scene = new Scene(masterPane, scene_width, scene_height);
		scene.setOnKeyPressed(e -> {KeyboardInput(e, display);});
		scene.getStylesheets().add("Style.css");
		
		// create stage
		primaryStage.setTitle("Calc App");
		//primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {System.out.println(e);});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	// methods
	// -------------------
	public void KeyboardInput(KeyEvent event, TextField display)
	{
		System.out.println("CalcApp::handle(KeyEvent event) -> event.getCode: " + event.getCode());
		// Key events
		// -----------------
		switch(event.getCode())
		{
		case NUMPAD0: 		
		case DIGIT0:
			NumericalButtonPressed(0, display);
			break;
		case NUMPAD1: 
		case DIGIT1:
			NumericalButtonPressed(1, display);
			break;
		case NUMPAD2: 
		case DIGIT2:
			NumericalButtonPressed(2, display);
			break;
		case NUMPAD3: 
		case DIGIT3:
			NumericalButtonPressed(3, display);
			break;
		case NUMPAD4: 
		case DIGIT4:
			NumericalButtonPressed(4, display);
			break;
		case NUMPAD5: 
		case DIGIT5:
			NumericalButtonPressed(5, display);
			break;
		case NUMPAD6: 
		case DIGIT6:
			NumericalButtonPressed(6, display);
			break;
		case NUMPAD7: 
		case DIGIT7:
			NumericalButtonPressed(7, display);
			break;
		case NUMPAD8: 
		case DIGIT8:
			NumericalButtonPressed(8, display);
			break;
		case NUMPAD9: 
		case DIGIT9:
			NumericalButtonPressed(9, display);
			break;
		case ENTER:
		case EQUALS: 
			EqualButtonPressed(display);
			break;
		case PLUS:
		case ADD:
			OperatorButtonPressed(Operator.Addition);
			break;
		case MINUS:
		case SUBTRACT: 
			OperatorButtonPressed(Operator.Subtraction);
			break; 
		case MULTIPLY:
			OperatorButtonPressed(Operator.Multiplication);
			break;
		case QUOTE:
		case DIVIDE: 
			OperatorButtonPressed(Operator.Division);
			break; 

		case BACK_SPACE: 
			DeleteButtonPressed(display);
			break;
		case DELETE: 
			ClearButtonPressed(display);
			break;
		default: 
			break;
			
		}
	}
	
	public void UpdateDisplay(TextField display) 
	{
		if (calculator.activeOperand == Operand.Left)
			display.setText(Integer.toString(calculator.ConvertLeftOperand()));
		else
			display.setText(Integer.toString(calculator.ConvertRightOperand()));
	}
	
	public void NumericalButtonPressed(int input_value, TextField display)
	{
		System.out.println("CalcApp::NumericalButtonPressed -> calling Calculator::AddOperatorDigit method");
		calculator.AddOperatorDigit(input_value);
		UpdateDisplay(display);
	}
	
	public void OperatorButtonPressed(Operator input_operator) {
		System.out.println("CalcApp::OperatorButtonPressed -> calling Calculator::SelectOperator method");
		calculator.SelectOperator(input_operator);
	}
	
	public void DeleteButtonPressed(TextField display)
	{
		System.out.println("CalcApp::DeleteButtonPressed -> calling Calculator::DeleteDigit");
		calculator.DeleteDigit();
		UpdateDisplay(display);
	}
	
	public void ClearButtonPressed(TextField display) 
	{
		System.out.println("CalcApp::ClearButtonPressed ->  calling Calculator::Clear"); 
		calculator.Clear();
		display.setText("0");
		
	}
	
	public void SignButtonPressed(TextField display)
	{
		System.out.println("CalcApp::SignButtonPressed ->  calling Calculator::SetOperandSign"); 
		calculator.SetOperandSign();
		UpdateDisplay(display);
	}
	
	public void EqualButtonPressed(TextField display)
	{
		System.out.println("CalcApp::EqualButtonPressed -> calling Calculator::SolveEquation method");
		int solution = calculator.SolveEquation(); 
		display.setText(Integer.toString(solution));
	}
	

}