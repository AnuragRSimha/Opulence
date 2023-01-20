package opulence.welcomepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import opulence.dbConnect.dbConnect;
import opulence.orderpage.orderpage_C;

public class welcomepage_C {
    private welcomepage_M wpM;
    private welcomepage_V wpV;
    float price = 0;

    public welcomepage_C(welcomepage_M welcomepageM, welcomepage_V welcomepageV) {
        this.wpM = welcomepageM;
        this.wpV = welcomepageV;
    }

    public void setWPMImage(String image) {
        wpM.setImage(image);
    }

    public Image getWPMImage() {
        return wpM.getImg();
    }

    public void menuSetter(Statement stmt) throws SQLException {
        ResultSet MenuList = stmt.executeQuery("select * from dishes order by mealName");
        ArrayList<String> fullMenu = new ArrayList<String>();
        while (MenuList.next()) {
            fullMenu.add(MenuList.getString(1) + " ..... (£ " + MenuList.getString(2) + ")");
        }
        String[] t = new String[fullMenu.size()];
        int y = 0;
        for (String i : fullMenu) {
            t[y] = i;
            y += 1;
        }
        wpV.setFoodMenu(t);
    }

    public void calculatePrice(Statement stmt) throws SQLException {
        HashMap<String, String> theOrders = new HashMap<>();
        String col0;
        String col1;
        price = 0;
        for (int i = 0; i < wpV.table.getRowCount(); i++) {
            col0 = (String) wpV.tableModel.getValueAt(i, 0);
            col1 = (String) wpV.tableModel.getValueAt(i, 1);
            if (col1.equals("")) {
                col1 = "1";
            }
            if (col0.contains("[")) {
                col0 = col0.substring(0, col0.indexOf("[")).trim();
            } else {
                col0 = col0.trim();
            }
            theOrders.put(col0, col1);
        }
        for (String x : theOrders.keySet()) {
            ResultSet query = stmt.executeQuery("SELECT * FROM dishes WHERE mealName = '" + x + "';");
            if (query.next()) {
                price += Float.parseFloat(query.getString(2)) * (Integer.parseInt(theOrders.get(x)));
            }
        }
        price = (float) (Math.round(price * Math.pow(10, 2)) / Math.pow(10, 2));
        wpV.payment.setText("Pay £ " + price);
    }

    public void clearCell() {
        int m = wpV.table.getSelectedRow();
        int n = wpV.table.getSelectedColumn();
        if (n != 0) {
            wpV.tableModel.setValueAt("", m, n);
        }
    }

    public void fillQtyCell() {
        int n = wpV.table.getSelectedColumn();
        for (int i = 0; i < wpV.table.getRowCount(); i++) {
            if (wpV.table.getValueAt(i, 1) == "" && n != 1) {
                wpV.table.setValueAt("1", i, 1);
            }
        }
    }

    public boolean isDishOrdered(String dish) {
        for (int i = 0; i < wpV.table.getRowCount(); i++) {
            if (wpV.table.getValueAt(i, 0).equals(dish)) {
                return true;
            }
        }
        return false;
    }

    public void configureWelcome(String custName, String tableNumber, String designation)
            throws SQLException, ClassNotFoundException {
        wpV.configureWelcomepage(getWPMImage(), custName, tableNumber, designation);

        Connection con = dbConnect.connect2db();
        Statement stmt = con.createStatement();
        menuSetter(stmt);
        wpV.menu.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String dish = (String) wpV.menu.getSelectedValue();
                    dish = dish.substring(0, dish.indexOf('.')).trim();
                    String pieceInPlate = new String();
                    try (ResultSet plate = stmt
                            .executeQuery("SELECT onePlate from dishes WHERE mealName = '" + dish + "';")) {
                        if (plate.next()) {
                            pieceInPlate = plate.getString(1);
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if (pieceInPlate == null) {
                        if (!isDishOrdered(dish)) {
                            wpV.tableModel.addRow(new Object[] { dish, "1", "Specifications" });
                        }
                    } else {
                        String display = dish + " [" + pieceInPlate + " Pc(s).]";
                        if (!isDishOrdered(display)) {
                            wpV.tableModel.addRow(new Object[] { display, "1", "Specifications" });
                        }

                    }
                }
            }

        });
        wpV.tableModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    calculatePrice(stmt);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        wpV.table.addMouseListener((MouseListener) new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                clearCell();
                fillQtyCell();
                if (e.getClickCount() == 2 && wpV.table.getSelectedRow() != -1) {
                    wpV.tableModel.removeRow(wpV.table.getSelectedRow());
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        wpV.clearBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                wpV.tableModel.setRowCount(0);

            }
        });
        wpV.payment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (price > 0) {
                    wpV.frame.dispose();
                    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
                    orderpage_C o = (orderpage_C) context.getBean("configureOrder");
                    o.configureOrder(custName, tableNumber, designation, price, wpV.table);
                }
            }
        });
        wpV.table.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //
            }

            @Override
            public void keyPressed(KeyEvent e) {
                clearCell();
                fillQtyCell();

            }

            @Override
            public void keyReleased(KeyEvent e) {
                clearCell();
                fillQtyCell();
            }
        });
        wpV.table.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                wpV.table.setToolTipText((String) wpV.table.getValueAt(wpV.table.getSelectedRow(), 0));
            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
            }
        });
    }
}
