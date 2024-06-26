/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.UserDao;
import Model.LoginRequest;
import Model.UserData;
import View.DashboardScreen;
import View.LoginScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author digitech
 */
public class LoginController {
    
    private final UserDao userDao = new UserDao();
    private final LoginScreen userView;

    public LoginController( LoginScreen userView) {
        this.userView = userView;
        userView.addLoginUserListener(new AddUserListener());
    }
    
    public void open(){
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
    
    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                LoginRequest user = new LoginRequest( email, password);
                UserData loginUser = userDao.login(user);
                if(loginUser == null){
                    JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                }else{
                    // success
                    JOptionPane.showMessageDialog(userView, "Login Successful");
                    DashboardScreen dashboardView = new DashboardScreen();
                    DashboardController dashBoardController = new DashboardController(dashboardView);
                    close();
                    dashBoardController.open();
                }
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }
        }
    }
}
