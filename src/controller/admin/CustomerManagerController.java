package controller.admin;

import controller.ManagerController;
import controller.popup.CustomerPopupController;
import dao.CustomerDao;
import dao.ShipmentDao;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import model.Customer;
import view.popup.CustomerPopupView;


public class CustomerManagerController extends ManagerController {

    CustomerDao customerDao = new CustomerDao();
    ShipmentDao shipmentDao = new ShipmentDao();
    CustomerPopupController popupController = new CustomerPopupController();
    CustomerPopupController customerPopupController = new CustomerPopupController();

    public CustomerManagerController() {
        super();
    }

    @Override
    public void actionAdd() {
//        popupController.add(this, new CustomerPopupView());
        customerPopupController.add(new CustomerPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa khách hàng", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                int id = selectedIds[i];
                shipmentDao.deleteByIdCustomer(id);
                customerDao.deleteById(id);
                updateData();
            }
        } catch (HeadlessException | SQLException e) {
            view.showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn khách hàng cần edit");
            } else {
                Customer c = customerDao.get(selectedId);
                if (c == null) {
                    throw new Exception("Khách hàng bạn chọn không hợp lệ");
                }
//                popupController.edit(this, new CustomerPopupView(), c);
                popupController.edit(new CustomerPopupView(), c, this::updateData, view::showError);
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateData() {
        try {
            ArrayList<Customer> customers = customerDao.getAll();
            view.setTableData(customers);
        } catch (SQLException e) {
            view.showError(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionSearch() {
        try {
            ArrayList<Customer> customers = customerDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(customers);
        } catch (SQLException e) {
            view.showError(e);
        }
    }

}
