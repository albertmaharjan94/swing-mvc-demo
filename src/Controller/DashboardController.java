/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.UserDao;
import Model.UserData;
import View.DashboardScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author digitech
 */
public class DashboardController {

    private final UserDao userDao = new UserDao();
    private final DashboardScreen dashboard;

    public DashboardController(DashboardScreen dashboard) {
        this.dashboard = dashboard;
        this.makeUserTable();

        dashboard.addUpdateUserListener(new AddUserUpdateListener());
        dashboard.addDeleteUserListener(new DeleteUserUpdateListener());
    }

    class DeleteUserUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = dashboard.getIdField().getText();

                UserData user = new UserData();
                user.setId(Integer.parseInt(id));

                boolean updateUser = userDao.delete(user);
                if (!updateUser) {
                    JOptionPane.showMessageDialog(dashboard, "Delete failed");
                } else {
                    // success
                    makeUserTable();
                    reset();
                    JOptionPane.showMessageDialog(dashboard, "Delete Successful");
                }
            } catch (Exception ex) {
                System.out.println("Error Delete user: " + ex.getMessage());
            }
        }
    }

    class AddUserUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = dashboard.getEmailField().getText();
                String username = dashboard.getUsernameField().getText();
                String id = dashboard.getIdField().getText();

                UserData user = new UserData();
                user.setEmail(email);
                user.setUsername(username);
                user.setId(Integer.parseInt(id));

                boolean updateUser = userDao.udpate(user);
                if (!updateUser) {
                    JOptionPane.showMessageDialog(dashboard, "Update failed");
                } else {
                    // success
                    makeUserTable();
                    reset();
                    JOptionPane.showMessageDialog(dashboard, "Update Successful");
                }
            } catch (Exception ex) {
                System.out.println("Error updated user: " + ex.getMessage());
            }
        }
    }

    void reset() {
        this.dashboard.getEmailField().setText("");
        this.dashboard.getUsernameField().setText("");
        this.dashboard.getIdField().setText("");
    }

    void makeUserTable() {
        System.out.println("Making table");
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Username");
        model.addColumn("Email");
        ArrayList<UserData> data = userDao.getAllUserData();
        for (UserData user : data) {
            model.addRow(new Object[]{user.getId(), user.getEmail(), user.getUsername()});
        }
//        Adding row to table
//        model.addRow(new Object[]{"Row1", "Value3", "Value4"});
        this.dashboard.getUserTable().setDefaultEditor(Object.class, null);

        this.dashboard.getUserTable().getSelectionModel().addListSelectionListener(new RowSelectionListener());
        this.dashboard.getUserTable().setModel(model);
    }

    class RowSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int row = dashboard.getUserTable().getSelectedRow();
                if (row >= 0) {
                    int id = Integer.parseInt(dashboard.getUserTable().getValueAt(row, 0).toString());
                    String username = dashboard.getUserTable().getValueAt(row, 1).toString();
                    String email = dashboard.getUserTable().getValueAt(row, 2).toString();
                    dashboard.getUsernameField().setText(username);
                    dashboard.getEmailField().setText(email);
                    dashboard.getIdField().setText("" + id);
                    System.out.println(username + " " + email + " " + id);
                    System.out.println("Row " + row + " is selected");
                }
            }
        }
    }

    public void open() {
        this.dashboard.setVisible(true);
    }

    public void close() {
        this.dashboard.dispose();
    }

}
