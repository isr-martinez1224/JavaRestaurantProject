package restaurant;

/**
 * Israel Martinez
 * 
 * CS 3300
 * 
 * Object-Oriented Programming Project
 * 
 * Project: Pizza N' Wings Restaurant
 */


import javax.swing.*;

//Main starts the program by asking if user wants to place an order
public class Restaurant {

    public static void main(String[] args) {
        //Image dimensions will effect the size of the dialog box 100x100
        ImageIcon pizzaPic = new ImageIcon("pizza_n_wings.png");
        
        //Ask user if they want to place an order
        boolean keepOrdering = true;
        String wantToOrder = "";
        
        while(keepOrdering){
            
            wantToOrder = JOptionPane.showInputDialog(null, "Welcome to Pizza n' Wings! \nWould you like to place an order? (y/n)","Welcome!",JOptionPane.PLAIN_MESSAGE);
            
            //If yes, place order(transfer to order class), n for no , else display error and try again
            if(wantToOrder.equals("y") || wantToOrder.equals("Y")){
                keepOrdering = true;
                Order order = new Order();
                order.placeOrder();
            }
            else if(wantToOrder.equals("n") || wantToOrder.equals("N")){
                keepOrdering = false;
                JOptionPane.showMessageDialog(null, "Thank you for visting, please come back soon! :)", "Goodbye!", JOptionPane.PLAIN_MESSAGE,pizzaPic);
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Option! Please enter a (y or n)","Invalid Input!",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
