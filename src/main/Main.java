package main;

import controller.LoginController;
import javax.swing.UnsupportedLookAndFeelException;
import model.Employee;
import view.LoginView;


public class Main{

    private static Employee employee;

    public static Employee getEmployee() {
        return employee;
    }

    public static void setEmployee(Employee employee) {
        Main.employee = employee;
    }
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Khởi tạo look and feel thành công!");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println("Khởi tạo look and feel thất bại!");
        }
        LoginController controller = new LoginController(new LoginView());
    }
}