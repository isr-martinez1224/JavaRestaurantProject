package restaurant;

/**
 * 
 * Pizza Class
 * 
 * Size = Small, Medium, Large
 * crustType = regular, pan, stuffed
 * toppingsType = pepperoni, hawaiian, veggie, cheese
 * price:           Regular     Pan     Stuffed
 *      Small    =  6.50        8.50    10
 *      Medium   =  15          17      18.50
 *      Large    =  20          22      23.50
 * 
 * 
 */

import javax.swing.*;
import java.util.ArrayList;

public class Pizza {
    private String size;
    private String crustType;
    private String toppingsType;
    private double price; 
    private double totalOrderPrice;
    private int numOfPizzas;
    private static ArrayList<String> orderList = new ArrayList<>();
    
    //Default Contructor with values at 0
    public Pizza(){
        size = "";
        crustType = "";
        toppingsType = "";
    }
    
    //Pizza Menu
    public String pizzaMenu(){
        String menu =   "--------------Pizza----------------\n"
                +       "           Regular     Pan     Stuffed\n"
                +       "Small   :  $6.50       $8.50   $10\n"
                +       "Medium  :  $15         $17     $18.50\n"
                +       "Large   :  $20         $22     $23.50\n"
                +       "-----------------------------------\n"
                +       "Topping Type  : Pepperoni, Hawaiian, Veggie, Cheese\n";
        return menu;
    }
    
    //Setters, methods working similar to setters
    //Ask user for pizza size
    public void setSize(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, pizzaMenu() + "\nWhat size?(Please spell the size like the menu)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            if(input.equals("Small") || input.equals("Medium") || input.equals("Large")){
                invalid = false;
            }
            else{
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        size = input;
    }
    
    //Ask user for pizza crust
    public void setCrust(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, pizzaMenu() + "\nWhat type of crust?(Please spell the crust like the menu)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            if(input.equals("Regular") || input.equals("Pan") || input.equals("Stuffed")){
                invalid = false;
            }
            else{
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        crustType = input;
    }
    
    //Ask user for pizza topping
    public void setToppings(int remaining){
        String input = "";
        boolean invalid = true;
        while(invalid){
            input = JOptionPane.showInputDialog(null, pizzaMenu() + "\nWhat type of topping?(Please spell the topping like the menu)", "Input Order: " + remaining + " orders left", JOptionPane.PLAIN_MESSAGE);
            if(input.equals("Pepperoni") || input.equals("Hawaiian") || input.equals("Veggie") || input.equals("Cheese")){
                invalid = false;
            }
            else{
                JOptionPane.showMessageDialog(null, input + " is not a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        toppingsType = input;
    }
    
    //Clear object to allow new orders to be inserted
    public void clearPizzaOrder(){
        size = "";
        crustType = "";
        toppingsType = "";
        price = 0.0;
        orderList.clear();
    }
    
    public void printFullOrder(){
        String order = "";
        for(int i = 0; i < orderList.size(); i++){
            order += orderList.get(i);
        }
        JOptionPane.showMessageDialog(null, order, "Pizza Order Review", JOptionPane.PLAIN_MESSAGE);
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
            input = JOptionPane.showInputDialog(null, "How many pizzas would you like to order? (Enter a number greater than 0)", "Item Amount", JOptionPane.PLAIN_MESSAGE);
            invalid = InputValidation(input);
        }
        numOfPizzas = Integer.parseInt(input);
        OrdersRemaining(numOfPizzas);
    }
    
    //Recursion to determine how many pizzas remain + call methods for order inputs
    private void OrdersRemaining(int remaining){
        if(remaining == 0){
            return;
        }
        else{         
            setSize(remaining);
            setCrust(remaining);
            setToppings(remaining);
            calcCost();
            getDescription();
            OrdersRemaining(remaining - 1);
        }
    }
    
    //Get price for single pizza, and add total of current order
    public void calcCost(){     
        switch(size){
            case "Small":
                if(crustType.equalsIgnoreCase("Regular")){
                    price = 6.50;
                    totalOrderPrice += 6.50;
                }
                else if(crustType.equalsIgnoreCase("Pan")){
                    price = 8.50;
                    totalOrderPrice += 8.50;
                }
                else if(crustType.equalsIgnoreCase("Stuffed")){
                    price = 10.0;
                    totalOrderPrice += 10.0;
                }
                break;
            case "Medium":
                if(crustType.equalsIgnoreCase("Regular")){
                    price = 15.0;
                    totalOrderPrice += 15.0;
                }
                else if(crustType.equalsIgnoreCase("Pan")){
                    price = 17.0;
                    totalOrderPrice += 17.0;
                }
                else if(crustType.equalsIgnoreCase("Stuffed")){
                    price = 18.50;
                    totalOrderPrice += 18.50;
                }
                break;
            case "Large":
                if(crustType.equalsIgnoreCase("Regular")){
                    price = 20.0;
                    totalOrderPrice += 20.0;
                }
                else if(crustType.equalsIgnoreCase("Pan")){
                    price = 22.0;
                    totalOrderPrice += 22.0;
                }
                else if(crustType.equalsIgnoreCase("Stuffed")){
                    price = 23.50;
                    totalOrderPrice += 23.50;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "An error occurred!","Error!",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }     
    }
    
    //Get a description of the current pizza, add to array list of current pizza order
    public void getDescription(){
        String descrip = "\nSize: " + this.size 
                + ", Crust: " + this.crustType 
                + ", Toppings: " + this.toppingsType  
                + ". Cost: $" + this.price;
        orderList.add(descrip);
        JOptionPane.showMessageDialog(null, descrip, "Pizza Description", JOptionPane.INFORMATION_MESSAGE);
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
