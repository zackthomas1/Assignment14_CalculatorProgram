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
import javafx.scene.text.Text;


public class CalcApp extends Application {
	
	Calculator calculator = new Calculator();

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		int scene_width = 400; 
		int scene_height = 500;
		
		// create pane 
		GridPane pane = new GridPane(); 
	
		// 
		Text display = new Text("0.0");
		display.setId("Display");
		pane.add(display, 0, 0,3,1);
		
		// create Number buttons
		// ---------------------
		//7-9 row 03
		Button btn07 = new Button("7"); 
		pane.add(btn07, 0, 1); // column=0 row=2
		btn07.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn07 pressed");
			NumericalButtonPressed(7, display);
		});
		
		Button btn08 = new Button("8"); 
		pane.add(btn08, 1, 1); // column=1 row=2
		btn08.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn08 pressed");
			NumericalButtonPressed(8, display);
		});
		
		Button btn09 = new Button("9"); 
		pane.add(btn09, 2, 1); // column=3 row=2
		btn09.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn09 pressed");
			NumericalButtonPressed(9, display);
		});
		
		//4-6 row 02
		Button btn04 = new Button("4"); 
		pane.add(btn04, 0, 2); // column=0 row=1
		btn04.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn04 pressed");
			NumericalButtonPressed(4, display);
		});
		
		Button btn05 = new Button("5"); 
		pane.add(btn05, 1, 2); // column=1 row=1
		btn05.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn05 pressed");
			NumericalButtonPressed(5, display);
		});
		
		Button btn06 = new Button("6"); 
		pane.add(btn06, 2, 2); // column=3 row=1
		btn06.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn06 pressed");
			NumericalButtonPressed(6, display);
		});
		
		//1-3 row 01
		Button btn01 = new Button("1"); 
		pane.add(btn01, 0, 3); // column=0 row=0
		btn01.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn01 pressed");
			NumericalButtonPressed(1, display);
		});
		
		Button btn02 = new Button("2"); 
		pane.add(btn02, 1, 3); // column=1 row=0
		btn02.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn02 pressed");
			NumericalButtonPressed(2, display);
		});
		
		Button btn03 = new Button("3"); 
		pane.add(btn03, 2, 3); // column=3 row=0
		btn03.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn03 pressed");
			NumericalButtonPressed(3, display);
		});
		
		// =, 0, '.'
		Button btn00 = new Button("0"); 
		pane.add(btn00, 0, 4); // column=0 row=0
		btn00.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btn00 pressed");
			NumericalButtonPressed(0, display);
		});
		
		Button btnDecimal = new Button("."); 
		pane.add(btnDecimal, 1, 4); // column=0 row=0
		btnDecimal.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnDecimal pressed");
		});
		
		Button btnEqual = new Button("="); 
		pane.add(btnEqual, 2, 4); // column=0 row=0
		btnEqual.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			EqualButtonPressed(display);
		});
		
		//operator button
		Button btnDelete = new Button("<-x"); 
		btnDelete.setId("Operator");
		pane.add(btnDelete, 4, 0); // column=0 row=2
		btnDelete.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnDelete pressed");
			DeleteButtonPressed();
		});
		
		
		Button btnDivide = new Button("/"); 
		btnDivide.setId("Operator");
		pane.add(btnDivide, 4, 1); // column=3 row=2
		btnDivide.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			OperatorButtonPressed(Operator.Division);
		});
		
		Button btnMultiply = new Button("X"); 
		btnMultiply.setId("Operator");
		pane.add(btnMultiply, 4, 2); // column=3 row=2
		btnMultiply.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			OperatorButtonPressed(Operator.Multiplication);
		});
		
		Button btnSubtract = new Button("-"); 
		btnSubtract.setId("Operator");
		pane.add(btnSubtract, 4, 3); // column=1 row=2
		btnSubtract.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			OperatorButtonPressed(Operator.Subtraction);
		});
		
		Button btnAdd = new Button("+"); 
		btnAdd.setId("Operator");
		pane.add(btnAdd, 4, 4); // column=0 row=2
		btnAdd.setOnMouseClicked(e -> {
			System.out.println("CalcApp::start-> btnEqual pressed");
			OperatorButtonPressed(Operator.Addition);
		});
		
		
		// create scene
		Scene scene = new Scene(pane, scene_width, scene_height);
		scene.getStylesheets().add("Style.css");
		
		// create stage
		primaryStage.setTitle("Calc App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void NumericalButtonPressed(int input_value, Text display)
	{
		System.out.println("CalcApp::NumericalButtonPressed -> calling Calculator::AddOperatorDigit method");
		calculator.AddOperatorDigit(input_value);
		if (calculator.activeOperand == Operand.Left)
			display.setText(Integer.toString(calculator.ConvertLeftOperand()));
		else
			display.setText(Integer.toString(calculator.ConvertRightOperand()));
	}
	
	public void OperatorButtonPressed(Operator input_operator) {
		System.out.println("CalcApp::OperatorButtonPressed -> calling Calculator::SelectOperator method");
		calculator.SelectOperator(input_operator);
	}
	
	public void DeleteButtonPressed()
	{
		System.out.println("CalcApp::DeleteButtonPressed -> calling Calculator::DeleteDigit");
		calculator.DeleteDigit();
	}
	
	public void EqualButtonPressed(Text display)
	{
		System.out.println("CalcApp::EqualButtonPressed -> calling Calculator::SolveEquation method");
		int solution = calculator.SolveEquation(); 
		display.setText(Integer.toString(solution));
	}
	
	public static void main(String[] args)
	{
		System.out.println("CalcApp::main-> Opening Calc App");
		
		launch(args); 
	}

}