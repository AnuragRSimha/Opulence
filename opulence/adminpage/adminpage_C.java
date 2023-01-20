package opulence.adminpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import opulence.dbConnect.dbConnect;

public class adminpage_C {
    private adminpage_M apM;
    private adminpage_V apV;

    public adminpage_C(adminpage_M adminpageM, adminpage_V adminpageV) {
        this.apM = adminpageM;
        this.apV = adminpageV;
    }

    public void setHPMImage(String image) {
        apM.setImage(image);
    }

    public Image getHPMImage() {
        return apM.getImg();
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
        apV.setFoodMenu(t);
    }

    public void configureadmin() throws ClassNotFoundException, SQLException {
        apV.configureadminpage(getHPMImage());
        Connection con = dbConnect.connect2db();
        Statement stmt = con.createStatement();
        menuSetter(stmt);
        apV.truncate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt.executeUpdate("TRUNCATE TABLE dishes;");
                    menuSetter(stmt);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        apV.amend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String o_Mta = apV.MTA.getText();
                String o_Mtr = apV.MTR.getText();
                String o_Mp = apV.MP.getText();
                String o_plate = apV.onePlate.getText();
                if ("".equals(o_Mta) && "".equals(o_Mtr) && "".equals(o_Mp) && "".equals(o_plate)) {
                    apV.RefuseUpdate(false);
                } else {
                    StringBuilder n_Mta = new StringBuilder();
                    StringBuilder n_Mtr = new StringBuilder();
                    StringBuilder n_Mp = new StringBuilder();
                    StringBuilder n_plate = new StringBuilder();
                    for (int i = 0; i < o_Mta.length(); i++) {
                        if (i == 0) {
                            n_Mta.append(Character.toUpperCase(o_Mta.charAt(0)));
                        } else if (o_Mta.charAt(i - 1) == ' ') {
                            n_Mta.append(Character.toUpperCase(o_Mta.charAt(i)));
                        } else {
                            n_Mta.append(Character.toLowerCase(o_Mta.charAt(i)));
                        }
                    }
                    for (int i = 0; i < o_Mtr.length(); i++) {
                        if (i == 0) {
                            n_Mtr.append(Character.toUpperCase(o_Mtr.charAt(0)));
                        } else if (o_Mtr.charAt(i - 1) == ' ') {
                            n_Mtr.append(Character.toUpperCase(o_Mtr.charAt(i)));
                        } else {
                            n_Mtr.append(Character.toLowerCase(o_Mtr.charAt(i)));
                        }
                    }
                    for (int i = 0; i < o_Mp.length(); i++) {
                        if (i == 0) {
                            n_Mp.append(Character.toUpperCase(o_Mp.charAt(0)));
                        } else if (o_Mp.charAt(i - 1) == ' ') {
                            n_Mp.append(Character.toUpperCase(o_Mp.charAt(i)));
                        } else {
                            n_Mp.append(Character.toLowerCase(o_Mp.charAt(i)));
                        }
                    }
                    for (int i = 0; i < o_plate.length(); i++) {
                        if (i == 0) {
                            n_plate.append(Character.toUpperCase(o_plate.charAt(0)));
                        } else if (o_plate.charAt(i - 1) == ' ') {
                            n_plate.append(Character.toUpperCase(o_plate.charAt(i)));
                        } else {
                            n_plate.append(Character.toLowerCase(o_plate.charAt(i)));
                        }
                    }
                    String Mta = n_Mta.toString();
                    String Mtr = n_Mtr.toString();
                    String Mp = n_Mp.toString();
                    String plate = n_plate.toString();
                    try {
                        if (!"".equals(Mta) && !"".equals(Mp) && !"".equals(plate) && !"0".equals(Mp)) {
                            if (!plate.equals("Na")) {
                                stmt.executeUpdate(
                                        "INSERT INTO dishes VALUES ('" + Mta + "','" + Mp + "','" + plate + "');");
                            } else if (plate.equals("Na")) {
                                stmt.executeUpdate(
                                        "INSERT INTO dishes VALUES ('" + Mta + "','" + Mp + "',NULL);");
                            }
                            menuSetter(stmt);
                        }
                        if (!"".equals(Mtr)) {
                            stmt.executeUpdate("DELETE FROM dishes WHERE mealName = '" + Mtr + "';");
                            menuSetter(stmt);
                        }
                        apV.TriggerChange(Mta, Mtr, Mp, plate);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        System.out.println(e1);
                        apV.DenyUpdate(Mta);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
