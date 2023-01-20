package opulence.orderpage;

import java.awt.*;

import javax.swing.JTable;

public class orderpage_C {
    private orderpage_M opM;
    private orderpage_V opV;

    public orderpage_C(orderpage_M orderpageM, orderpage_V orderpageV) {
        this.opM = orderpageM;
        this.opV = orderpageV;
    }

    public void setOPMImage(String image) {
        opM.setImage(image);
    }

    public Image getOPMImage() {
        return opM.getImg();
    }

    public void configureOrder(String custName, String tableNumber, String designation, float price, JTable table) {
        opV.configureOrderpage(getOPMImage(), custName, tableNumber, designation, price, table);
    }
}
