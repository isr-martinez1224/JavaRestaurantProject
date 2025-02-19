package restaurant;

/***
 * Wing Class
 * 
 * Size = 6,10,15 wings
 * wingType = traditional or boneless
 * sauce = plain, bbq, lemon pepper, buffalo
 * price:           Traditional     Boneless
 *      6 wings  =  10              8
 *      10 wings =  14              12
 *      15 wings =  20              18
 * 
 */

import javax.swing.*;
import java.util.ArrayList;

public class Wings {
    private int size;
    private String wingType;
    private String sauce;
    private double price;
    private double totalOrderPrice;
    private int numOfPlates;
    private static ArrayList<String> orderList = new ArrayList<>();
    
    //Constructor set items to empty
    public Wings(){
        size = 0;
        wingType = "";
        sauce = "";
    }
    
    //Setters, methods working similar to setters
    //Ask user for wing plate size
    public void setSize(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, wingMenu() + "\nWhat size of plate?(Please enter a number)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            //check if input is a number, set invalid to false if it is
            invalid = InputValidation(input);
            if(invalid == false){
                //if input is a number, check if it's a correct selection
                if(!input.equals("6") && !input.equals("10") && !input.equals("15")){
                    invalid = true;
                    JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        size = Integer.parseInt(input);
    }
    
    //Ask user for wing type
    public void setWingType(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, wingMenu() + "\nWhat type of wings?(Please spell the type exactly)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            if(input.equals("Traditional") || input.equals("Boneless")){
                invalid = false;
            }
            else{
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        wingType = input;
    }
    
    //Ask user for wing sauce
    public void setSauce(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, wingMenu() + "\nWhat type of sauce?(Please spell the sauce like the menu)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            if(input.equals("Plain") || input.equals("BBQ") || input.equals("Lemon Pepper") || input.equals("Buffalo")){
                invalid = false;
            }
            else{
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        sauce = input;
    }
 
    //Clear object to allow new orders to be inserted
    public void clearWingOrder(){
        size = 0;
        wingType = "";
        sauce = "";
        price = 0.0;
        orderList.clear();
    }
    
    //Wing Menu
    public String wingMenu(){
        String menu =   "--------------Wings----------------\n"
                +       "           Traditional     Boneless\n"
                +       "6 wings :  $10             $8\n"
                +       "10 wings:  $14             $12\n"
                +       "15 wings:  $20             $18\n"
                +       "-----------------------------------\n"
                +       "Sauces  : Plain, BBQ, Lemon Pepper, Buffalo\n";
        return menu;
    }
    
    //Display full wing order to user
    public void printFullOrder(){
        String order = "";
        for(int i = 0; i < orderList.size(); i++){
            order += orderList.get(i);
        }
        JOptionPane.showMessageDialog(null, order, "Wing Order Review", JOptionPane.PLAIN_MESSAGE);
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
            input = JOptionPane.showInputDialog(null, "How many plates of wings would you like to order? (Enter a number greater than 0)", "Item Amount", JOptionPane.PLAIN_MESSAGE);
            invalid = InputValidation(input);
        }
        numOfPlates = Integer.parseInt(input);
        OrdersRemaining(numOfPlates);
    }
    
    //Recursion to determine how many wing plates remain + call methods for order inputs
    private void OrdersRemaining(int remaining){
        if(remaining == 0){
            return;
        }
        else{         
            setWingType(remaining);
            setSize(remaining);
            setSauce(remaining);
            calcCost();
            getDescription();
            OrdersRemaining(remaining - 1);
        }
    }
    
    //Get price for single plate, and add total of current order
    public void calcCost(){     
        switch(size){
            case 6:
                if(wingType.equalsIgnoreCase("Traditional")){
                    price = 10.0;
                    totalOrderPrice += 10.0;
                }
                else if(wingType.equalsIgnoreCase("Boneless")){
                    price = 8.0;
                    totalOrderPrice += 8.0;
                }
                break;
            case 10:
                if(wingType.equalsIgnoreCase("Traditional")){
                    price = 14.0;
                    totalOrderPrice += 14.0;
                }
                else if(wingType.equalsIgnoreCase("Boneless")){
                    price = 10.0;
                    totalOrderPrice += 10.0;
                }
                break;
            case 15:
                if(wingType.equalsIgnoreCase("Traditional")){
                    price = 20.0;
                    totalOrderPrice += 20.0;
                }
                else if(wingType.equalsIgnoreCase("Boneless")){
                    price = 18.0;
                    totalOrderPrice += 18.0;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "An error occurred!","Error!",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }     
    }
    
    //Get a description of the current pizza, add to array list of current wing order
    public void getDescription(){
        String descrip = "\nWing Type: " + this.wingType 
                + ", Number of Wings: " + this.size 
                + ", Wing Sauce: " + this.sauce  
                + ". Cost: $" + this.price;
        orderList.add(descrip);
        JOptionPane.showMessageDialog(null, descrip, "Plate Description", JOptionPane.INFORMATION_MESSAGE);
    }
      
    //INPUT VALIDATION for string to int
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