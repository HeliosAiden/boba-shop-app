package controller;

import controller.admin.CustomerManagerController;
import controller.admin.OrderManagerController;
import controller.admin.ShipmentManagerController;
import controller.employee.InformationController;
import dao.EmployeeDao;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Employee;
import utils.IconManager;
import view.EmployeeDashboardView;
import view.LoginView;
import view.admin.AboutView;
import view.admin.CustomerManagerView;
import view.admin.HomeView;
import view.admin.ManagerPaneView;
import view.admin.MenuItem;
import view.admin.OrderManagerView;
import view.admin.ShipmentManagerView;
import view.employee.InformationView;


public class EmployeeDashboardController {

    private EmployeeDashboardView view;
    ManagerController orderManagerController = new OrderManagerController(),
            shipmentManagerController = new ShipmentManagerController(),
            customerManagerController = new CustomerManagerController();
    InformationController informationController = new InformationController();
    ManagerPaneView orderManagerView = new OrderManagerView(),
            shipmentManagerView = new ShipmentManagerView(),
            customerManagerView = new CustomerManagerView();
    HomeView homeView = new HomeView();
    AboutView aboutView = new AboutView();
    InformationView informationView = new InformationView();

    SideBarController sideBarController = new SideBarController();
    JPanel[] cards = {homeView, orderManagerView, customerManagerView,
        shipmentManagerView, aboutView, informationView};

    public EmployeeDashboardController(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee employee = EmployeeDao.getEmployee();
        if (employee != null) {
            view.getLbName().setText(employee.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    public EmployeeDashboardView getView() {
        return view;
    }

    public void setView(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuKH = new MenuItem("QLKH", im.getIcon("technical_support_25px.png"), "Quản lý khách hàng");
        MenuItem menuQLDDH = new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Quản lý đơn đặt hàng");
        MenuItem menuQLGH = new MenuItem("QLGH", im.getIcon("truck_25px.png"), "Quản lý giao hàng");
        MenuItem menuTL = new MenuItem("TL", im.getIcon("settings_25px.png"), "Thiết lập");
        menuTL.addSubMenu(new MenuItem("TTCN", im.getIcon("about_25px.png"), "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuKH, menuQLDDH, menuQLGH, menuTL);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    private void addEvent() {
        view.getBtnLogout().addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                EmployeeDao.setStartTime(null);
                EmployeeDao.setEndTime(new Timestamp(System.currentTimeMillis()));
            } catch (Exception ex) {
                view.showError(ex);
            }
            view.dispose();
            new LoginController(new LoginView());
        });
    }

    public void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "QLDDH"://Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLKH"://Quản lý khách hàng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
            case "QLGH"://Quản lý giao hàng
                view.setPanel(shipmentManagerView);
                shipmentManagerController.setView(shipmentManagerView);
                shipmentManagerController.updateData();
                break;
            case "TT":
                view.setPanel(aboutView);
                break;
            case "TTCN": // Thống tin cá nhân
                view.setPanel(informationView);
                informationController.setView(informationView);
                break;
            default:
                view.setPanel(homeView);
        }
    }
}
