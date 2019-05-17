// Java Program to create a Risk_Client 
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.paint.Color; 
import javafx.event.EventHandler; 
import javafx.scene.canvas.*; 
import javafx.scene.web.*; 
import javafx.scene.Group; 
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import javafx.*;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent; 
import javafx.scene.shape.Line;
import java.beans.*;
import java.util.*;
import javax.xml.crypto.*;
import java.awt.event.*;

//import java.awt.*;

//Still need to clean up imports *****************************************************!

  
public class Risk_Client2 extends Application { 
	static boolean isSelected = false;
	static ArrayList<Circle> land = new ArrayList<Circle>();
	static int firstSelect;
	static int secondSelect;
	
// launch the application 
	public void start(Stage stage) throws Exception { 
  
		try { 
  		// set title for the stage 
			stage.setTitle("Player 2"); 
  
		// create a Pane 
			Pane root = new Pane();
			
		//Exit button
			Button exit = new Button("Exit");
			exit.setOnAction(e -> {
				System.out.print("Exit application. ");
				System.exit(0);
			});//end Exit Lamba function
		//Position Exit button.
			root.getChildren().add(exit);
			exit.setLayoutX(250);
			exit.setLayoutY(250);
			
		//create socket
			Socket clientSocket = new Socket("localhost", 8000);
			DataInputStream fromServer = new DataInputStream(clientSocket.getInputStream()); 
			DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		
		//Create Commit button.
			Button finish = new Button("Commit");
			finish.setOnAction(e -> {
			//revert all to black
				for(int j = 0; j < Risk_Client.land.size(); j++){
					Risk_Client.land.get(j).setFill(Color.BLACK);
				}//end for loop
			//write to server
				new Thread(() -> {
					try{
						String turn = fromServer.readUTF();
							System.out.println("Player " + turn);
							if (turn.equals("2")) {
								toServer.writeInt(firstSelect);
								toServer.writeInt(secondSelect);
							}
					}//end try
					catch (Exception v) { 
						System.out.println(v.getMessage()); 
					} //end catch
					}).start();
			});//end Lambda function
		//postion Commit button
			finish.setLayoutX(300);
			finish.setLayoutY(250);
			root.getChildren().add(finish);
		
		//create cancel button
			Button cancel = new Button("Cancel");
			ButtonHandler bHandle1Ish = new ButtonHandler();
			cancel.setOnAction(bHandle1Ish);
			cancel.setLayoutX(300);
			cancel.setLayoutY(215);
			root.getChildren().add(cancel);

		//Lines
			Line line1 = new Line(50, 50, 100, 110);
			Line line2 = new Line(100, 110, 260, 80);
			Line line3 = new Line(260, 80, 255, 180);
			Line line4 = new Line(255, 180, 115, 225);
			Line line5 = new Line(115, 225, 260, 80);
			Line line6 = new Line(115, 225,100, 110);
			root.getChildren().add(line1);
			root.getChildren().add(line2);
			root.getChildren().add(line3);
			root.getChildren().add(line4);
			root.getChildren().add(line5);
			root.getChildren().add(line6);

		//Make circles
		//Circle 0
			MyCircle a = new MyCircle(50, 50, 20, 0);
			CircleHandler handler3 = new CircleHandler(a);
			a.setOnMouseClicked(handler3);
			root.getChildren().add(a);
			land.add(a);
		//Circle 1
			MyCircle b = new MyCircle(115, 225, 20, 1);
			CircleHandler handler4 = new CircleHandler(b);
			b.setOnMouseClicked(handler4);
			root.getChildren().add(b);
			land.add(b);
		//Circle 2
			MyCircle c = new MyCircle(255, 180, 20, 2); 
			CircleHandler handler = new CircleHandler(c);
			c.setOnMouseClicked(handler);	
			root.getChildren().add(c);
			land.add(c);
		//Circle 3
			MyCircle d = new MyCircle(100, 110, 20, 3);
			CircleHandler handler2 = new CircleHandler(d);
			d.setOnMouseClicked(handler2);
			root.getChildren().add(d);
			land.add(d);
		//Circle 4
			MyCircle f = new MyCircle(260, 80, 20, 4);
			CircleHandler handler5 = new CircleHandler(f);
			f.setOnMouseClicked(handler5);
			root.getChildren().add(f);
			land.add(f);
			
		//Set stuff up
			Scene scene = new Scene(root, 400, 300);  
			stage.setScene(scene); 
			stage.show(); 
			
		} //end try
		catch (Exception e) { 
			System.out.println(e.getMessage()); 
		} //end catch
	}//end start method 

	public static void main(String args[]) { 
	// launch the application 
		launch(args); 
	} //End main Method
}//end Risk_Client class 


class CircleHandler implements EventHandler<MouseEvent>{
	MyCircle c;
	
	
	public CircleHandler(MyCircle c) {
		this.c = c;
	}//end constructor

	
	@Override
	public void handle(MouseEvent e){
		if(Risk_Client.isSelected == true){
		//change color for user
			c.setFill(Color.GRAY);
		//revert is selected to false to allow for next selection.
			Risk_Client.isSelected = false;
		//take second number to export to server.
			Risk_Client.secondSelect = c.getNum();
		}else{
		//Make isSelected true to denote that first selection has been made.
			Risk_Client.isSelected = true;
		//change color for user
			c.setFill(Color.BLUE);
		//get number to send to server.
			Risk_Client.firstSelect = c.getNum();
		}//end else
	}//end handle method
} //end CircleHandler Class

//Create Circle class with fourth argument for territory number.
class MyCircle extends Circle{
	int num;
	int x;
	int y;
	int r;
	
//constructor
	public MyCircle(int x, int y, int r, int num){
		num = num;
		x = x;
		y = y;
		r = r;
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(r);
		this.setNum(num);
	}//end constructor
	
//getters and setters.
	public int getNum(){
		return num;
	}//end getNum method
	public  void setNum(int v){
		num = v;
	}//end setNum
}//end MyCircle Class

class ButtonHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent f){
		Risk_Client.isSelected = false;
		for(int j = 0; j < Risk_Client.land.size(); j++){
			Risk_Client.land.get(j).setFill(Color.BLACK);
		}
		Risk_Client.firstSelect = -1;
		Risk_Client.secondSelect = -1;
	}
}
