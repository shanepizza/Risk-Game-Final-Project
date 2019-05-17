// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.time.*;


public class Risk_Server extends Application {
	// Text area for displaying contents
	private TextArea ta = new TextArea();
	
		/*
		Territory space1 = new Territory("Me", 6, "Space1");
		Territory space2 = new Territory("Them", 1, "Space2");
		Territory space3 = new Territory("Them", 1, "Space3");
		Territory space4 = new Territory("Them", 1, "Space4");
		Territory space5 = new Territory("Them", 1, "Space5");
		Territory[] vertices = {space1, space2, space3, space4, space5};
		int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {2, 4}, {4, 3}, {3, 0} };
		*/

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ta.setWrapText(true);
	// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 400, 200);
		primaryStage.setTitle("Risk Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		new Thread(() -> firstServer()).start();
	}//End Start method
	 	
//First server method
	public void firstServer(){
		try{
		//create the server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			ta.appendText("Server Started at: " + LocalDateTime.now() + "\n\n");
		//connect to client
			Socket socket = serverSocket.accept();
			ta.appendText("Client connected at: " + LocalDateTime.now() + "\n\n");
		//data input and output streams
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			
			
			//continously check for data from client
				while (true) {
					int firstplace = inputFromClient.readInt();
					int secondPlace = inputFromClient.readInt();
					System.out.print("First spot is: " + firstplace + "\n");
					System.out.print("Second spot is: " + secondPlace + "\n");
					//System.out.print(Risk_Server.Territory[firstplace].getOwnership());//*********************************! Error Here.
				//Words go to server screen
					ta.appendText("Recieved Data From Client\n");
				}//end while
		}//end try
		catch(IOException ex){
			ex.printStackTrace();
		}//end catch
	}//end firstServer method 
	public static void main(String[] args) {
		launch(args);
	}//end Main
}//End class
