/*
 * Name: Kevin Zheng
 * Date: 01/13/2025
 * Class Period: 3
 * File Name: MyProgram.java
 * Program Description: The purpose of this program is to first get the input from the data.txt file. Then, the program procceses through the file line by line and determines 
 * if they are a cart, engine, or if the program should end. If they are a cart, the lines associated with that cart are processed and depending on the cart's values are added to a specific track. 
 * The train could even leave the station if the train is going to be overweight. 
 * If they are an engine, the destination city line after that is processed and the train with that destination city leaves the station, outputting the engine and carts, and resetting the train/station. 
 * Eventually, the end of the data.txt file is reached and "END" appears, which means the program should end. The trains on the inspection track are fixed and returned to the track they were supposed to be in. 
 * Then, the remaining trains leaves the stations, outputting the engine and carts except for Track D (other destinations), which just outputs its current status.  
 */

// Scanner is imported in order to be able to get usable input from data.txt 
import java.util.Scanner;

// Allows data.txt to be imported into this file 
import java.io.File;

// Stack is needed for Train.java to store the carts 
import java.util.Stack;

// Queue is imported to be able to store the input from the file and process the input in the order they were given. Also used for Inspection Track as the carts should be processed in the order given 
import java.util.Queue;

// LinkedList is imported to be used as initial value for Queue
import java.util.LinkedList;


public class MyProgram {
	public static int val = 0;
	public static void main(String[] args) {

		//Declare variables 
		int limitTrackA = 100000, limitTrackB = 100000, limitTrackC = 100000;
		Train trackTrenton = new Train("Trenton");
		Train trackCharlotte = new Train("Charlotte");
		Train trackBaltimore = new Train("Baltimore");
		Train trackOther = new Train("Other Destinations");
		Train trackCheck = new Train("Inspection");
		Queue<String> totalInput = new LinkedList<>();
		String cartName, contents, originCity, destinationCity, weight, milesTraveled, userLine;
		int trentonWeight = 0, charlotteWeight = 0, baltimoreWeight = 0;
		Boolean loopContinue = true;


		Scanner x = new Scanner(System.in);
		try{
			File f = new File("HelloWorldProject/src/data.txt");
			x = new Scanner (f);
			while (loopContinue == true) {
				userLine = x.nextLine();
				totalInput.add(userLine);
				if(userLine.equals("END"))
					loopContinue = false;
			} // end while loop 
		} // end try 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		} // end catch 


		while (!totalInput.isEmpty()) {
			cartName = totalInput.remove();
			if(cartName.substring(0,3).equals("CAR")) {
				contents = totalInput.remove();
				originCity = totalInput.remove();
				destinationCity = totalInput.remove();
				weight = totalInput.remove();
				milesTraveled = totalInput.remove();
				int tempWeight = Integer.valueOf(weight);
				int tempMiles = Integer.valueOf(milesTraveled);

				if (tempMiles > 700) 
					trackCheck.addCartQueue(cartName, contents, originCity, destinationCity, weight, milesTraveled);
				else if (destinationCity.equals("Trenton")) {
					if (trentonWeight+tempWeight > limitTrackA) {
						trackTrenton.outputCarts();
						trentonWeight = 0;
					} // end if statement 
					trackTrenton.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
					trentonWeight += tempWeight;
				} // end else if statement 
				else if (destinationCity.equals("Charlotte")) {
					if (charlotteWeight+tempWeight > limitTrackB) {
						trackCharlotte.outputCarts();
						charlotteWeight = 0;
					} // end if statement 
					trackCharlotte.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
					charlotteWeight += tempWeight;
				} // end else if statement 
				else if (destinationCity.equals("Baltimore")) {
					if (baltimoreWeight+tempWeight > limitTrackC) {
						trackBaltimore.outputCarts();
						baltimoreWeight = 0;
					} // end if statement 
					trackBaltimore.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
					baltimoreWeight += tempWeight;
				} // end else if statement 
				else
					trackOther.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
			} // end if statement 

			else if (cartName.substring(0,3).equals("ENG")) {
				destinationCity = totalInput.remove();
				if (destinationCity.equals("Trenton")) {
					trackTrenton.engine(cartName);
					trentonWeight = 0;
				} // end if statement 
				else if (destinationCity.equals("Charlotte")) {
					trackCharlotte.engine(cartName);
					charlotteWeight = 0;
				} // end else if statement 
				else if (destinationCity.equals("Baltimore")) {
					trackBaltimore.engine(cartName);
					baltimoreWeight = 0;
				} // end else if statement 
			} // end else if statement 

			else if (cartName.substring(0,3).equals("END")) {
				while (!trackCheck.getCartsQueue().isEmpty()) {
					String[] tempCart = trackCheck.getCartsQueue().remove();
					cartName = tempCart[0];
					contents = tempCart[1];
					originCity = tempCart[2];
					destinationCity = tempCart[3];
					weight = tempCart[4];
					milesTraveled = tempCart[5];
					milesTraveled = "100";
					int tempWeight = Integer.valueOf(weight);

					if (destinationCity.equals("Trenton")) {
						if (trentonWeight+tempWeight > limitTrackA) {
							trackTrenton.outputCarts();
							trentonWeight = 0;
						} // end if statement 
						trackTrenton.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
						trentonWeight += tempWeight;
					} // end else if statement 
					else if (destinationCity.equals("Charlotte")) {
						if (charlotteWeight+tempWeight > limitTrackB) {
							trackCharlotte.outputCarts();
							charlotteWeight = 0;
						} // end if statement 
						trackCharlotte.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
						charlotteWeight += tempWeight;
					} // end else if statement 
					else if (destinationCity.equals("Baltimore")) {
						if (baltimoreWeight+tempWeight > limitTrackC) {
							trackBaltimore.outputCarts();
							baltimoreWeight = 0;
						} // end if statement 
						trackBaltimore.addCart(cartName, contents, originCity, destinationCity, weight, milesTraveled);
						baltimoreWeight += tempWeight;
					} // end else if statement 
				} // end while loop 

				trackTrenton.outputCarts();
				trackCharlotte.outputCarts();
				trackBaltimore.outputCarts();
				trackOther.status();
			} // end else if statement 
		} // end while loop 

	} // end main 
} // end class 
