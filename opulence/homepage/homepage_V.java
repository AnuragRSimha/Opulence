package opulence.homepage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class homepage_V {
    JButton order = new JButton("Order");
    JTextField TNO = new JTextField();
    JTextField nameField = new JTextField();
    JTextField desigField = new JTextField("Mr/Mrs/Dr/Prof");
    JFrame frame = new JFrame("Opulence");

    public void configureHomepage(Image img) {
        Color allColour = Color.decode("#D09E00");
        Font font = new Font("Century Gothic", Font.PLAIN, 18);
        Font font2 = new Font("Old English Text MT", Font.BOLD, 18);
        Border textBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, allColour);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this);
            }
        });
        frame.pack();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JPanel comp1 = new JPanel(new GridLayout(6, 1));
        JPanel comp2 = new JPanel(new GridLayout(1, 1));

        JPanel tNo = new JPanel(new GridLayout(1, 2));
        JLabel tableNo = new JLabel("Table number:");
        tableNo.setForeground(allColour);
        tableNo.setFont(font);
        tableNo.setSize(20, 10);
        TNO.setFont(font);
        TNO.setForeground(allColour);
        TNO.setBorder(textBorder);
        TNO.setOpaque(false);
        tNo.add(tableNo, 0);
        tNo.add(TNO, 1);
        tNo.setOpaque(false);

        JPanel namePanel = new JPanel(new GridLayout(1, 2));
        JLabel name = new JLabel("Name:");
        name.setForeground(allColour);
        name.setFont(font);
        name.setSize(20, 10);
        nameField.setFont(font);
        nameField.setForeground(allColour);
        nameField.setBorder(textBorder);
        nameField.setOpaque(false);
        namePanel.add(name);
        namePanel.add(nameField);
        namePanel.setOpaque(false);

        JPanel desigPanel = new JPanel(new GridLayout(1, 2));
        JLabel designation = new JLabel("Designation (Opt):");
        designation.setForeground(allColour);
        designation.setFont(font);
        designation.setSize(20, 10);
        desigField.setFont(font);
        desigField.setForeground(allColour);
        desigField.setBorder(textBorder);
        desigField.setOpaque(false);
        desigPanel.add(designation);
        desigPanel.add(desigField);
        desigPanel.setOpaque(false);

        order.setBackground(allColour);
        order.setForeground(Color.black);
        order.setFont(font2);
        order.setBorderPainted(false);

        comp1.setOpaque(false);
        comp1.add(tNo);
        comp1.add(Box.createRigidArea(new Dimension(0, 0)));
        comp1.add(namePanel);
        comp1.add(Box.createRigidArea(new Dimension(0, 0)));
        comp1.add(desigPanel);
        comp1.setBorder(new EmptyBorder(170, 10, 10, 10));
        comp1.setMaximumSize(new Dimension(400, 330));

        comp2.add(order);
        comp2.setMaximumSize(new Dimension(100, 50));

        frame.getContentPane().add(comp1);
        frame.getContentPane().add(comp2);
        frame.setSize(870, 775);
        frame.setVisible(true);
    }
}
