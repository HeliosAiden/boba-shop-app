package controller;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import model.Employee;
import view.LoginView;


public class LoginController {

    private LoginView view;
    EmployeeDao employeeDao = new EmployeeDao();

    public LoginController(LoginView view) {
        this.view = view;
        view.setVisible(true);
        addEvent();
    }

    public final void addEvent() {
        //Sự kiện login
        view.getTxtPassword().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getloginBtn().doClick();
                }
            }
        });
        view.getloginBtn().addActionListener((ActionEvent evt) -> {
            login();
        });
        view.getLblForgotPassword().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.showMessage("Chưa hỗ trợ!");
            }
        });
        view.getLblRegister().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.showMessage("Chưa hỗ trợ!");
            }
        });
    }

    public void login() {
        String username = view.getTxtUsername().getText();
        String password = new String(view.getTxtPassword().getPassword());
        try {
            Employee employee = employeeDao.findByUsername(username);
            if (employee == null) {
                view.showError("Không tồn tại tài khoản!");
                return;
            }
            if (!employee.checkPassword(password)) {
                view.showError("Mật khẩu sai");
                return;
            }
            // SessionManager.create(employee);//Khởi tạo session

            switch (employee.getPermission()) {
                case MANAGER -> //Admin controller
                    // AdminDashboardController adminController = new AdminDashboardController(new AdminDashboardView());
                    // adminController.getView().setPanel(new HomeView());
                    view.dispose();// Tắt form đăng nhập
                case STAFF -> //EmployeeDashboardController employeeController = new EmployeeDashboardController(new EmployeeDashboardView());
                    // employeeController.getView().setPanel(new HomeView());
                    view.dispose();// Tắt form đăng nhập
                case INACTIVE -> {
                    view.showError("Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ admin để biết thêm chi tiết");
                    view.dispose();
                }
                default -> {
                    view.showError("Vui lòng liên hệ admin để biết thêm chi tiết");
                    view.dispose();
                }
            }
            //Seller Controller
                    } catch (SQLException e) {
            view.showError(e);
        }
    }
    
}
