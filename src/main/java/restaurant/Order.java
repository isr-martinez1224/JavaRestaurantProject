package restaurant;

/**
 * Order object allows us to place orders of whatever food menu item we want
 * 
 * This class calculates the total cost of the order of all items, and displays the item information as a review
 */

import javax.swing.*;

public class Order {
    
    
    private static int totalOrders;
    
    private int menuChoice;
    private double totalCost;
    
    
    
    public Order(){
        totalOrders++;
        totalCost = 0.0;
    }
    
    
    //INPUT VALIDATION for string to int
    public int InputValidation(String menu){
        int num = 0;
        String input = "";
        while(true){
            try {
                input = JOptionPane.showInputDialog(null, menu,"Menu",JOptionPane.PLAIN_MESSAGE);
                num = Integer.parseInt(input);
                if(0 <= num && num <= 3){
                    return num;
                }
                else{
                   JOptionPane.showMessageDialog(null, input + " is not a valid number!", "Error!", JOptionPane.ERROR_MESSAGE); 
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, input + " is not a valid number!", "Error!", JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
   
    //Method to place order for various items
    public void placeOrder(){
        ImageIcon thankYou = new ImageIcon("thank_you.png");
        
        
        String menu = "Here's what is on the menu:"
                + "\nWhat would you like to order? (Please enter a number)"
                + "\n\t0.Done/Submit Order"
                + "\n\t1.Pizza"
                + "\n\t2.Wings"
                + "\n\t3.Dessert";
        
        boolean done = true;
        while(done){
        
            menuChoice = InputValidation(menu);
        
            Pizza pizzaOrder        = new Pizza();
            Wings wingsOrder        = new Wings();
            Dessert dessertOrder    = new Dessert();
            
            switch (menuChoice) {
                case 0:
                    //calulate order and display thank you
                    totalCost();
                    pizzaOrder.printFullOrder();
                    wingsOrder.printFullOrder();
                    dessertOrder.printFullOrder();
                    
                    //set done to false and clear the objects for future orders
                    done = false;
                    pizzaOrder.clearPizzaOrder();
                    wingsOrder.clearWingOrder();
                    dessertOrder.clearDessertOrder();
                    
                    //Place a thank you message and show how many orders have been placed
                    JOptionPane.showMessageDialog(null, "Thank you for placing your order! :)", "Thank You!", JOptionPane.PLAIN_MESSAGE, thankYou);
                    JOptionPane.showMessageDialog(null, "Total orders made today: " + totalOrders, "Total Orders", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1: //add pizza to the order
                    pizzaOrder.PlaceOrders();
                    totalCost += pizzaOrder.getTotalPrice();
                    break;
                case 2: //add wings to the order
                    wingsOrder.PlaceOrders();
                    totalCost += wingsOrder.getTotalPrice();
                    break;
                case 3: //add dessert to the order
                    dessertOrder.PlaceOrders();
                    totalCost += dessertOrder.getTotalPrice();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Sorry, " + menuChoice + " is not a valid option!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Calculate the total cost of the order of all items
    public void totalCost(){
        JOptionPane.showMessageDialog(null, "The total cost of your order is: $" + totalCost, "Total Cost of Order", JOptionPane.INFORMATION_MESSAGE);
    }
}
