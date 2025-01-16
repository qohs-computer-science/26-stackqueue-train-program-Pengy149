// LinkedList is imported to be used as initial value for Queue
import java.util.LinkedList;

// Imported to be used for Inspection Track as the carts should be processed in the order given 
import java.util.Queue;

// Stack is imported to store the carts in a train 
import java.util.Stack;

public class Train {
    private String endCity;
    private Stack<String[]> carts;
    private Queue<String[]> cartsQueue;
    private String defaultEngine;
    public Train (String destinedCity) {
        endCity = destinedCity;
        carts = new Stack<String[]>();
        cartsQueue = new LinkedList<>();
        defaultEngine = "ENG00000";
    } // end constructor 

    public void outputCarts() {
        engine(defaultEngine);
    } // end outputCarts 

    public void addCart (String cartName, String contents, String originCity, String destinationCity, String weight, String milesTraveled) {
        String[] cart = {cartName, contents, originCity, destinationCity, weight, milesTraveled};
        carts.add(cart);
    } // end addCart 

    public void addCartQueue (String cartName, String contents, String originCity, String destinationCity, String weight, String milesTraveled) {
        String[] cart = {cartName, contents, originCity, destinationCity, weight, milesTraveled};
        cartsQueue.add(cart);
    } // end addCart 

    public void engine (String engineName) {
        String[] tempCart;
        System.out.println(engineName + " leaving for " + endCity + " with the following cars: ");
        while (!carts.isEmpty()) {
            tempCart = carts.pop();
            System.out.println(tempCart[0] + " containing " + tempCart[1] + " weighing " + tempCart[4] + "lbs.");
        } // end while loop 
        System.out.println("");
    } // end engine 

    public void status () {
        String[] tempCart;
        System.out.println("Status of Track D - Other Destinatons: ");
        while (!carts.isEmpty()) {
            tempCart = carts.pop();
            System.out.println(tempCart[0] + " containing " + tempCart[1] + " weighing " + tempCart[4] + "lbs.");
        } // end while loop 
        System.out.println("");
    } // end status
       
    public Stack<String[]> getCarts() {
        return carts;
    } // end getCarts 

    public Queue<String[]> getCartsQueue() {
        return cartsQueue;
    } // end getCarts 

} // end class 
