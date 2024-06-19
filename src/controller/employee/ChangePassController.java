package controller.employee;

import controller.popup.ErrorCallback;
import controller.popup.SuccessCallback;
import dao.EmployeeDao;
import javax.swing.JFrame;
// import main.SessionManager;
import model.Employee;
import view.employee.ChangePassView;
import dao.EmployeeDao;


public class ChangePassController {

    EmployeeDao employeeDao = new EmployeeDao();
    JFrame previousView;

    public ChangePassController() {

    }

    public void show(ChangePassView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnConfirm().addActionListener(evt -> {
            try {
                changePassword(view);
                sc.onSuccess();
            } catch (Exception e) {
                ec.onError(e);
            }
        });
    }

    public void changePassword(ChangePassView view) throws Exception {
        String oldPass = view.getTxtOldPass().getText(),
                newPass = view.getTxtNewPass().getText(),
                confirmPass = view.getTxtConfirmPass().getText();
        Employee employee = EmployeeDao.getEmployee();
        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (!oldPass.equals(employee.getPassword())) {
            throw new Exception("Mật khẩu cũ không đúng ");
        }
        if (!newPass.equals(confirmPass)) {
            throw new Exception("Xác nhận mật khẩu sai!");
        }

        employee.setPassword(newPass);
        employeeDao.update(employee);
        view.dispose();
    }

}
