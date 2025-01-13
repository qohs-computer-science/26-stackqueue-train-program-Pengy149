/*
 * Name: Kevin Zheng
 * Date: 01/13/2025
 * Class Period: 3
 * File Name: MyProgram.java
 * Program Description: The purpose of this program is to first 
 */

// 
import java.util.Scanner;

// 
import java.io.File;

// 
import java.util.Stack;

// 
import java.util.Queue;

// 
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
