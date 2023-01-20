package opulence.homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import opulence.dbConnect.dbConnect;
import opulence.welcomepage.*;

public class homepage_C {
    private homepage_M hpM;
    private homepage_V hpV;
    private welcomepage_V wpV;

    public homepage_C(homepage_M homepageM, homepage_V homepageV) {
        this.hpM = homepageM;
        this.hpV = homepageV;
    }

    public void setHPMImage(String image) {
        hpM.setImage(image);
    }

    public Image getHPMImage() {
        return hpM.getImg();
    }

    public void configureHome() throws ClassNotFoundException, SQLException {
        hpV.configureHomepage(getHPMImage());
        hpV.order.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tableNumber = hpV.TNO.getText();
                String name = hpV.nameField.getText();
                String designation = hpV.desigField.getText();
                if (!"".equals(tableNumber) && !"".equals(name)) {
                    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
                    welcomepage_C w = (welcomepage_C) context.getBean("configurewelcome");
                    try {
                        if (designation.equals("Mr/Mrs/Dr/Prof")) {
                            designation = "";
                        }
                        hpV.frame.dispose();
                        w.configureWelcome(name, tableNumber, designation);
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
