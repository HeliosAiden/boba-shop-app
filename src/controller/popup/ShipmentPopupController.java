package controller.popup;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.ShipmentDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFrame;
import model.Shipment;
import utils.ShipmentStatus;
import view.popup.SelectCustomerPopupView;
import view.popup.ShipmentPopupView;


public class ShipmentPopupController {

    ShipmentDao shipmentDao = new ShipmentDao();
    CustomerDao customerDao = new CustomerDao();
    JFrame previousView;

    public void add(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
//        previousView = view;
        try {
            Shipment shipment = shipmentDao.get(idOrder);
            if (shipment != null) {
                edit(view, idOrder, sc, ec);
                return;
            }
            shipment = new Shipment();
            shipment.setShipperName(EmployeeDao.getEmployee().getName());
            shipment.setShipperPhoneNumber(EmployeeDao.getEmployee().getPhoneNumber());
            shipment.setShipCost(0);
            shipment.setIdOrder(idOrder);
            shipment.setCustomer(customerDao.getAll().get(0));
            shipment.setStatus(ShipmentStatus.PENDING);
            shipmentDao.save(shipment);
            edit(view, idOrder, sc, ec);
        } catch (SQLException e) {
            ec.onError(e);
            view.dispose();
        }

    }

    public void edit(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            if (view != previousView) {

            }
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        for (ShipmentStatus value : ShipmentStatus.values()) {
            view.getCboStatus().addItem(value.getName());
        }
        try {
            Shipment shipment = shipmentDao.get(idOrder);
            if (shipment.getCustomer() != null) {
                view.getLbCustomerName().setText(shipment.getCustomer().getName());
            } else {
                view.getLbCustomerName().setText("<Chưa chọn>");
            }
            view.getTxtShipperName().setText(shipment.getShipperName());
            view.getTxtShipperPhoneNumber().setText(shipment.getShipperPhoneNumber());
            view.getCboStatus().setSelectedItem(shipment.getStatus().getName());
            view.getSpnShipCost().setValue(shipment.getShipCost());

            view.getBtnSelectCustomer().addActionListener(evt -> {
                SelectCustomerPopupController selectCustomerPopupController = new SelectCustomerPopupController();
                selectCustomerPopupController.select(new SelectCustomerPopupView(), (customer) -> {
                    shipment.setCustomer(customer);
                    view.getLbCustomerName().setText(customer.getName());
                });
            });
            view.getBtnOK().addActionListener(evt -> {
                try {
                    editShipment(view, shipment);
                    view.dispose();
                    view.showMessage("Tạo / sửa đơn ship thành công!");
                    sc.onSuccess();
                } catch (SQLException e) {
                    ec.onError(e);
                }
            });
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }

    }

    public void editShipment(ShipmentPopupView view, Shipment shipment) throws SQLException {
        shipment.setShipperName(view.getTxtShipperName().getText());
        shipment.setShipperPhoneNumber(view.getTxtShipperPhoneNumber().getText());
        shipment.setStatus(ShipmentStatus.getByName(view.getCboStatus().getSelectedItem().toString()));
        shipment.setShipCost((int) view.getSpnShipCost().getValue());
        if (shipment.getStatus() == ShipmentStatus.COMPLETED || shipment.getStatus() == ShipmentStatus.CANCELLED) {
            shipment.setEndDate(new Timestamp(System.currentTimeMillis()));
        } else {
            shipment.setEndDate(null);
        }
        shipmentDao.update(shipment);
    }
}
