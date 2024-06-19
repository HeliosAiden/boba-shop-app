package controller.popup.order;

import dao.FoodCategoryDao;
import dao.FoodItemDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import model.FoodCategory;
import model.FoodItem;
import view.popup.order.FoodCategoryPane;
import view.popup.order.FoodItemPane;


public class FoodItemController {

    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    FoodItemDao foodItemDao = new FoodItemDao();
    JPanel panelFoodCategory, panelFoodItem;

    public interface Event {

        public abstract void onChange(FoodItem pnl);
    }

    public FoodItemController() {
    }

    public JPanel getPanelFoodCategory() {
        return panelFoodCategory;
    }

    public void setPanelFoodCategory(JPanel panelFoodCategory) {
        this.panelFoodCategory = panelFoodCategory;
    }

    public JPanel getPanelFoodItem() {
        return panelFoodItem;
    }

    public void setPanelFoodItem(JPanel panelFoodItem) {
        this.panelFoodItem = panelFoodItem;
    }

    public void renderCategory(Event evt) throws Exception {
        panelFoodCategory.removeAll();
        renderFoodItem(2, evt);// Tra sua
        for (FoodCategory foodCategory : foodCategoryDao.getAll()) {
            FoodCategoryPane pnl = new FoodCategoryPane(foodCategory);
            panelFoodCategory.add(pnl);
            pnl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    FoodCategory foodCategory = pnl.getFoodCategory();
                    try {
                        renderFoodItem(foodCategory.getId(), evt);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
        panelFoodCategory.updateUI();
    }

    public void renderFoodItem(int idCategory, Event event) throws Exception {
        panelFoodItem.removeAll();
        for (FoodItem foodItem : foodItemDao.getByIdCategory(idCategory)) {
            FoodItemPane pnl = new FoodItemPane(foodItem);
            panelFoodItem.add(pnl);
            pnl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    event.onChange(pnl.getFoodItem());
                }
            });
        }
        panelFoodItem.updateUI();
    }

}
