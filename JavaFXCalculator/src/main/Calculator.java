package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Calculator extends Application{
	private GridPane grid; 
	private List<Button> numList, operatorList; 
	private TextArea answerBox; 
	private String answer = ""; 
	
	public Calculator() {
		this.grid = buildGrid(); 
		this.numList = buildNumList(); 
		this.operatorList = buildOpList(); 
		this.answerBox = buildAnswerBox(); 
	}
	
	public void setAnswer(String s) {
		this.answer = s; 
	}
	
	public String getAnswer() {
		return this.answer; 
	}
	
	public GridPane buildGrid() {
		GridPane grid = new GridPane(); 
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		
		ColumnConstraints c1 = new ColumnConstraints(); 
		c1.setPercentWidth(25);
		ColumnConstraints c2 = new ColumnConstraints(); 
		c2.setPercentWidth(25);
		ColumnConstraints c3 = new ColumnConstraints(); 
		c3.setPercentWidth(25);
		ColumnConstraints c4 = new ColumnConstraints(); 
		c4.setPercentWidth(24);
		
		RowConstraints r1 = new RowConstraints(); 
		r1.setPercentHeight(20);
		RowConstraints r2 = new RowConstraints(); 
		r2.setPercentHeight(20);
		RowConstraints r3 = new RowConstraints(); 
		r3.setPercentHeight(20);
		RowConstraints r4 = new RowConstraints(); 
		r4.setPercentHeight(20);
		RowConstraints r5 = new RowConstraints(); 
		r5.setPercentHeight(20);
		RowConstraints r6 = new RowConstraints(); 
		r6.setPercentHeight(20);
		
		grid.getColumnConstraints().addAll(c1, c2, c3, c4); 
		grid.getRowConstraints().addAll(r1, r2, r3, r4, r5, r6); 
		return grid; 
	}
	
	public List<Button> buildNumList() {
		List<Button> numList = new ArrayList<>(); 
		
		Button b1 = new Button("1"); 
		numList.add(b1); 
		Button b2 = new Button("2"); 
		numList.add(b2); 
		Button b3 = new Button("3"); 
		numList.add(b3); 
		Button b4 = new Button("4"); 
		numList.add(b4); 
		Button b5 = new Button("5"); 
		numList.add(b5); 
		Button b6 = new Button("6"); 
		numList.add(b6); 
		Button b7 = new Button("7"); 
		numList.add(b7); 
		Button b8 = new Button("8"); 
		numList.add(b8); 
		Button b9 = new Button("9"); 
		numList.add(b9);
		Button b0 = new Button("0"); 
		numList.add(b0); 
		return numList; 
	}
	
	public List<Button> buildOpList() {
		List<Button> opList = new ArrayList<>(); 
		
		Button add = new Button("+"); 
		opList.add(add); 
		Button sub = new Button("-"); 
		opList.add(sub); 
		Button mult = new Button("*"); 
		opList.add(mult);
		Button div = new Button("/"); 
		opList.add(div); 
		Button dec = new Button("."); 
		opList.add(dec);
		Button eq = new Button("="); 
		opList.add(eq); 
		Button log = new Button("ln"); 
		opList.add(log); 
		
		return opList; 
	}
	
	public TextArea buildAnswerBox() {
		TextArea answerBox = new TextArea(); 
		answerBox.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTBLUE, new CornerRadii(3), null)));
		answerBox.setMaxHeight(Double.MAX_VALUE);
		answerBox.setMaxWidth(Double.MAX_VALUE);
		answerBox.setStyle("-fx-font-size:28");
		
		return answerBox; 
	}
	
	public void addButtons() {
		int loc = 0; 
		
		for (int row = 2; row < 5; row++) {
			for (int col = 0; col < 3; col++) {
				numList.get(loc).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				numList.get(loc).setStyle("-fx-font-size:28");
				grid.add(numList.get(loc), col, row);
				loc++; 
			}
		}
		numList.get(9).setStyle("-fx-font-size:28");
		numList.get(9).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(numList.get(9), 0, 5);
	}
	
	public void addOperators() {
		
		for (Button b : operatorList) {
			b.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.ORANGE, new CornerRadii(3), null)));
			b.setStyle("-fx-font-size:28; -fx-border-color:black; -fx-border-width:1 1 1 1; -fx-border-radius:3");
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		grid.add(operatorList.get(4), 1, 5);
		grid.add(operatorList.get(5), 2, 5);
		
		for (int i = 0; i < 4; i++) {
			grid.add(operatorList.get(i), 3, i + 2);
		}
		
		grid.add(operatorList.get(6), 0, 1, 4, 1);
		
	}
	
	public void getDec(List<String> vals) {
		int loc = vals.indexOf("."); 
		String newDouble = vals.get(loc - 1) + "." + vals.get(loc + 1); 
		vals.set(loc, newDouble); 
		vals.remove(loc - 1); 
		vals.remove(loc); 

	}
	
	public void addNums(List<String> vals) {
		double op1, op2; 
		while (vals.contains(".")) {
			getDec(vals);
		}
			
		int loc = vals.indexOf("+"); 
	
		op1 = Double.parseDouble(vals.get(loc - 1)); 
		op2 = Double.parseDouble(vals.get(loc + 1)); 
		
		String result = String.valueOf(op1 + op2); 
		vals.set(loc + 1, result); 	
		vals.subList(0, loc + 1).clear(); 
	}
	
	public void subNums(List<String> vals) {
		double op1, op2; 
		while (vals.contains(".")) {
			getDec(vals);
		}
			
		int loc = vals.indexOf("-"); 
	
		op1 = Double.parseDouble(vals.get(loc - 1)); 
		op2 = Double.parseDouble(vals.get(loc + 1)); 
		
		String result = String.valueOf(op1 - op2); 
		vals.set(loc + 1, result); 	
		vals.subList(0, loc + 1).clear(); 
	}
	
	public void multNums(List<String> vals) {
		double op1, op2; 
		while (vals.contains(".")) {
			getDec(vals);
		}
			
		int loc = vals.indexOf("*"); 
	
		op1 = Double.parseDouble(vals.get(loc - 1)); 
		op2 = Double.parseDouble(vals.get(loc + 1)); 

		String result = String.valueOf(op1 * op2); 
		vals.set(loc + 1, result); 	
		vals.subList(0, loc + 1).clear(); 
	}
	
	public void divNums(List<String> vals) {
		double op1, op2; 
		while (vals.contains(".")) {
			getDec(vals);
		}
			
		int loc = vals.indexOf("/"); 
	
		op1 = Double.parseDouble(vals.get(loc - 1)); 
		op2 = Double.parseDouble(vals.get(loc + 1)); 
		
		String result = ""; 
		if (op2 == 0) {
			result = "no"; 
		}
		else {
			result = String.valueOf(op1 / op2); 
		}	
		vals.set(loc + 1, result); 	
		vals.subList(0, loc + 1).clear();  
	}
	
	public void logNums(List<String> vals) {
		double op1; 
		while (vals.contains(".")) {
			getDec(vals);
		}
			
		int loc = vals.indexOf("ln"); 
	
		op1 = Double.parseDouble(vals.get(loc + 1));
		String result = String.valueOf(Math.log(op1)); 
		vals.set(loc + 1, result); 	
		vals.subList(0, loc + 1).clear(); 
	}
	
	public void calculate() {
		List<String> splitList = new ArrayList<>(Arrays.asList(answer.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"))); 
		List<String> opArray = new ArrayList<>(Arrays.asList(answer.split("[\\d]")));

		if (!opArray.contains("ln")) {
			opArray.remove(0); 
		}
		
		opArray.removeAll(Collections.singleton("."));
		int opIndex = 0; 
		
		while (splitList.size() > 2) {
			if (opArray.get(opIndex).equals("+")) {
				addNums(splitList); 
			}
			else if (opArray.get(opIndex).equals("-")) {
				subNums(splitList); 
			}
			else if (opArray.get(opIndex).equals("*")) {
				multNums(splitList); 
			}
			else if (opArray.get(opIndex).equals("/")){
				divNums(splitList); 
			}
			else {
				logNums(splitList); 
			}
			opIndex++; 
		}
		answer = splitList.get(0); 
	}
	
	public void setButtonOps(List<Button> buttonList) {
		DropShadow shadow = new DropShadow(); 
		for (Button b : buttonList) {
			b.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					b.setEffect(shadow);
					
					if (answer.equals("")) {
						answerBox.setText(b.getText());
					}
					else {
						answerBox.appendText(b.getText());
					}
					
					answer += b.getText(); 
					if (answer.contains("=")) {
						calculate(); 
						answerBox.setText(answer);
						answer = ""; 
					}
				}
			});
			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		            b.setEffect(null);
		        }
			});
		}
	}
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Calculator"); 
		grid.add(answerBox, 0, 0, 4, 1);
		addButtons(); 
		addOperators(); 
		setButtonOps(numList); 
		setButtonOps(operatorList); 
		Scene scene = new Scene(grid, 300, 400); 
		stage.setScene(scene);
		stage.show(); 
	}

	public static void main(String[] args) {
		launch(args); 
	}

}
