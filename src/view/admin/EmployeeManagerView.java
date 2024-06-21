package view.admin;

import javax.swing.DefaultComboBoxModel;
import model.Employee;


public class EmployeeManagerView extends ManagerPaneView<Employee> {

    String[] list = {"ID", "Name", "phoneNumber"};

    public EmployeeManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Tên tài khoản");
        tableModel.addColumn("mật khẩu");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Ngày vào làm");
        tableModel.addColumn("Chức vụ");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
