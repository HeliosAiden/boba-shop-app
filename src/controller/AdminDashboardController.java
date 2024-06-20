package controller;

import controller.admin.CustomerManagerController;
import controller.admin.EmployeeManagerController;
import controller.admin.FoodCategoryManagerController;
import controller.admin.FoodItemManagerController;
import controller.admin.OrderManagerController;
import controller.admin.ShipmentManagerController;
import controller.admin.TableManagerController;
import controller.employee.InformationController;
import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Employee;
import utils.IconManager;
import view.AdminDashboardView;
import view.LoginView;
import view.admin.AboutView;
import view.admin.CustomerManagerView;
import view.admin.EmployeeManagerView;
import view.admin.FoodCategoryManagerView;
import view.admin.FoodItemManagerView;
import view.admin.HomeView;
import view.admin.ManagerPaneView;
import view.admin.MenuItem;
import view.admin.OrderManagerView;
import view.admin.ShipmentManagerView;
import view.admin.TableManagerView;
import view.employee.InformationView;

// TODO: init AdminDashboardController
public class AdminDashboardController {

    private AdminDashboardView view;
    ManagerController employeeManagerController = new EmployeeManagerController(), // Controller
            tableManagerController = new TableManagerController(),
            foodCategoryManagerController = new FoodCategoryManagerController(),
            foodItemManagerController = new FoodItemManagerController(),
            orderManagerController = new OrderManagerController(),
            shipmentManagerController = new ShipmentManagerController(),
            customerManagerController = new CustomerManagerController();
    InformationController informationController = new InformationController();

    HomeView homeView = new HomeView();
    ManagerPaneView employeeManagerView = new EmployeeManagerView(), // View
            tableManagerView = new TableManagerView(),
            foodCategoryManagerView = new FoodCategoryManagerView(),
            foodItemManagerView = new FoodItemManagerView(),
            orderManagerView = new OrderManagerView(),
            shipmentManagerView = new ShipmentManagerView(),
            customerManagerView = new CustomerManagerView();
    AboutView aboutView = new AboutView();
    InformationView informationView = new InformationView();
    JPanel[] cards = {
        homeView, employeeManagerView, tableManagerView, customerManagerView,
        foodCategoryManagerView, orderManagerView, foodItemManagerView, shipmentManagerView,
        aboutView, informationView
    };

    SideBarController sideBarController = new SideBarController();

    public AdminDashboardController(AdminDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee session = EmployeeDao.getEmployee();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    public AdminDashboardView getView() {
        return view;
    }

    public void setView(AdminDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuQLNV = new MenuItem("QLNV", im.getIcon("user_groups_25px.png"), "Quản lý nhân viên");
        MenuItem menuQLHH = new MenuItem("QLHH", im.getIcon("cardboard_box_25px.png"), "Quản lý hàng hóa");
        MenuItem menuQLDH = new MenuItem("QLDH", im.getIcon("shopping_cart_25px.png"), "Quản lý đặt hàng");
        MenuItem menuTL = new MenuItem("TL", im.getIcon("settings_25px.png"), "Thiết lập");
        menuQLHH.addSubMenu(new MenuItem("QLLM", im.getIcon("food_category_25px.png"), "Quản lý loại món"));
        menuQLHH.addSubMenu(new MenuItem("QLMA", im.getIcon("food_25px.png"), "Quản lý món ăn"));
        menuQLDH.addSubMenu(new MenuItem("QLB", im.getIcon("table_25px.png"), "Quản lý bàn"));
        menuQLDH.addSubMenu(new MenuItem("QLKH", im.getIcon("technical_support_25px.png"), "Quản lý khách hàng"));
        menuQLDH.addSubMenu(new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Quản lý đơn đặt hàng"));
        menuQLDH.addSubMenu(new MenuItem("QLGH", im.getIcon("truck_25px.png"), "Quản lý giao hàng"));
        menuTL.addSubMenu(new MenuItem("TTCN", im.getIcon("about_25px.png"), "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuQLNV, menuQLHH, menuQLDH, menuTL);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    // Tạo sự kiện
    private void addEvent() {
        view.getBtnLogout().addActionListener((ActionEvent evt) -> {
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
            LoginController loginController = new LoginController(new LoginView());
        });
    }

    private void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "QLNV" -> {
                //Nhân viên
                view.setPanel(employeeManagerView);
                employeeManagerController.setView(employeeManagerView);
                employeeManagerController.updateData();
            }
            case "QLDDH" -> {
                //Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
            }
            case "QLB" -> {
                //Quản lý bàn
                view.setPanel(tableManagerView);
                tableManagerController.setView(tableManagerView);
                tableManagerController.updateData();
            }
            case "QLKH" -> {
                //Quản lý khách hàng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
            }
            case "QLLM" -> {
                //Quản lý loại món
                view.setPanel(foodCategoryManagerView);
                foodCategoryManagerController.setView(foodCategoryManagerView);
                foodCategoryManagerController.updateData();
            }
            case "QLMA" -> {
                //Quản lý món ăn
                view.setPanel(foodItemManagerView);
                foodItemManagerController.setView(foodItemManagerView);
                foodItemManagerController.updateData();
            }
            case "QLGH" -> {
                //Quản lý giao hàng
                view.setPanel(shipmentManagerView);
                shipmentManagerController.setView(shipmentManagerView);
                shipmentManagerController.updateData();
            }
            case "QLHH", "QLDH", "TL" -> {
            }
            case "TT" -> view.setPanel(aboutView);
            case "TTCN" -> {
                // Thống tin cá nhân
                view.setPanel(informationView);
                informationController.setView(informationView);
            }
            default -> view.setPanel(homeView);
        }
            }
}
