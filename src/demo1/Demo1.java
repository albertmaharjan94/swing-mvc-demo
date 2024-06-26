/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo1;

import Controller.SignupController;
import View.SignupScreen;

/**
 *
 * @author digitech
 */
public class Demo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SignupScreen userView = new SignupScreen();
        SignupController userController = new SignupController(userView);
        userController.open();
    }
    
}
