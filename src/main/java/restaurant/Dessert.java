package restaurant;

/**
 * 
 * Dessert Class
 * 
 * dessertType = brownie, cookie, cinnamon rolls
 * 
 *      Brownie         =  15
 *      Cookie          =  15
 *      Cinnamon Rolls  =  10
 * 
 * 
 */

import javax.swing.*;
import java.util.ArrayList;

public class Dessert {
    private String dessertSelection;
    private double price;
    private double totalOrderPrice;
    private int numOfDesserts;
    private static ArrayList<String> orderList = new ArrayList<>();
    
    //Default Contructor with values at 0
    public Dessert(){
        dessertSelection = "";
    }
    
    //Dessert Menu
    public String dessertMenu(){
        String menu =   "------------------------Dessert---------------------\n"
                +       "1) Super Chocolate Brownie      :  $15\n"
                +       "2) Super Chocolate Chip Cookie  :  $15\n"
                +       "3) 6 Mini Cinnamon Rolls        :  $10\n"
                +       "-----------------------------------\n";
        return menu;
    }
    
    //Setters, methods working similar to setters
    //Ask user for dessert type
    public void setDessert(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, dessertMenu() + "\nWhich dessert would you like?(Please enter the item number)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            //check if input is a number, set invalid to false if it is
            invalid = InputValidation(input);
            if(invalid == false){
                //if input is a number, check if it's a correct selection
                if(!input.equals("1") && !input.equals("2") && !input.equals("3")){
                    invalid = true;
                    JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if (input.equals("1")) {
            dessertSelection = "Super Chocolate Brownie";
        }
        else if(input.equals("2")){
            dessertSelection = "Super Chocolate Chip Cookie";
        }
        else if(input.equals("3")){
            dessertSelection = "6 Mini Cinnamon Rolls";
        }
    }
    
    //Clear object to allow new orders to be inserted
    public void clearDessertOrder(){
        dessertSelection = "";
        price = 0.0;
        orderList.clear();
    }
    
    public void printFullOrder(){
        String order = "";
        for(int i = 0; i < orderList.size(); i++){
            order += orderList.get(i);
        }
        JOptionPane.showMessageDialog(null, order, "Dessert Order Review", JOptionPane.PLAIN_MESSAGE);
    }
    
    public double getTotalPrice(){
        return totalOrderPrice;
    }
    
    /**********************************************************************/
    
    //Methods
    public void PlaceOrders(){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, "How many desserts would you like to order? (Enter a number greater than 0)", "Item Amount", JOptionPane.PLAIN_MESSAGE);
            invalid = InputValidation(input);
        }
        numOfDesserts = Integer.parseInt(input);
        OrdersRemaining(numOfDesserts);
    }
    
    //Recursion to determine how many desserts remain + call methods for order inputs
    private void OrdersRemaining(int remaining){
        if(remaining == 0){
            return;
        }
        else{         
            setDessert(remaining);
            calcCost();
            getDescription();
            OrdersRemaining(remaining - 1);
        }
    }
    
    //Get price for single dessert, and add total of current order
    public void calcCost(){     
        switch(dessertSelection){
            case "Super Chocolate Brownie":
                price = 15.0;
                totalOrderPrice += 15.0;
                break;
            case "Super Chocolate Chip Cookie":
                price = 15.0;
                totalOrderPrice += 15.0;
                break;
            case "6 Mini Cinnamon Rolls":
                price = 10.0;
                totalOrderPrice += 10.0;
                break;
            default:
                JOptionPane.showMessageDialog(null, "An error occurred!","Error!",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }     
    }
    
    //Get a description of the current desser, add to array list of current dessert order
    public void getDescription(){
        String descrip = "\nDessert Name: " + this.dessertSelection  
                + ". Cost: $" + this.price;
        orderList.add(descrip);
        JOptionPane.showMessageDialog(null, descrip, "Dessert Description", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //INPUT VALIDATION for string to inr
    public boolean InputValidation(String input){
        int num = 0;
            try {
                num = Integer.parseInt(input);
                if(num > 0){
                    return false;
                }
                else{
                   JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE); 
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }       
        return true;
    }
}
