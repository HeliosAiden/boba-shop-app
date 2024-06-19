package controller.employee;

import controller.TimeCouterController;
import dao.EmployeeDao;

import javax.swing.JOptionPane;
import model.Employee;
import utils.ErrorPopup;
import view.employee.ChangePassView;
import view.employee.InformationView;
// import view.employee.HistoryLoginView;
// import view.employee.CalendarView;


public class InformationController {

    private Employee employee;
    private InformationView view;
    ChangePassController changePassController = new ChangePassController();
    // HistoryLoginController historyLoginController = new HistoryLoginController();
    // CalendarController calendarController = new CalendarController();

    public InformationController() {
        this.employee = EmployeeDao.getEmployee();
    }

    public InformationView getView() {
        return view;
    }

    public void setView(InformationView view) {
        if (this.view != view) {
            TimeCouterController.start((second) -> {
                view.getLbTimeWorking().setText(secondToHours(second));
            });
            // calendarController.show(view.getPanelCalendar(), new CalendarView());
            addEvent(view);
            this.view = view;
        }
    }

    public void addEvent(InformationView view) {
        view.getBtnChangePass().addActionListener(evt -> {
            changePassController.show(new ChangePassView(), () -> JOptionPane.showMessageDialog(view, "Đổi pass thành công"), ErrorPopup::show);
        });
        view.getBtnHistoryLogin().addActionListener(evt -> {
            // historyLoginController.show(new HistoryLoginView());
        });
    }

    private String secondToHours(int totalSecs) {
        int hours, minutes, seconds;
        hours = totalSecs / 3600;
        minutes = (totalSecs % 3600) / 60;
        seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
