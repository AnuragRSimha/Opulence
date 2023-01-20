package opulence.orderpage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class orderpage_V {
    public void configureOrderpage(Image img, String customerName, String tableNumber, String designation, float Price,
            JTable table) {
        // System.out.println(customerName + " " + tableNumber + " " + designation + " "
        // + Price);
        JFrame frame = new JFrame("Opulence");
        Color allColour = Color.decode("#D09E00");
        Font font2 = new Font("Old English Text MT", Font.PLAIN, 22);
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

        JPanel bill = new JPanel(new GridLayout(1, 1));
        JPanel comp2 = new JPanel(new GridLayout(1, 1));
        JPanel comp3 = new JPanel(new GridLayout(1, 1));
        JPanel comp4 = new JPanel(new GridLayout(1, 1));
        JPanel comp5 = new JPanel(new GridLayout(1, 1));

        JLabel welCust = new JLabel("Table No. " + tableNumber);
        welCust.setForeground(allColour);
        welCust.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        bill.add(welCust);
        bill.setOpaque(false);
        bill.setBorder(new EmptyBorder(200, 150, 0, 0));
        bill.setMaximumSize(new Dimension(500, 250));

        JLabel theCustomerName = new JLabel("Order by: " + designation + " " + customerName);
        theCustomerName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        theCustomerName.setOpaque(false);
        theCustomerName.setForeground(allColour);

        comp2.add(theCustomerName);
        comp2.setBorder(new EmptyBorder(3, 0, 0, 110));
        comp2.setMaximumSize(new Dimension(670, 60));
        comp2.setOpaque(false);

        JLabel totalPrice = new JLabel("Amount paid: £ " + Price);
        totalPrice.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        totalPrice.setOpaque(false);
        totalPrice.setForeground(allColour);

        comp3.add(totalPrice);
        comp3.setBorder(new EmptyBorder(0, 0, 0, 110));
        comp3.setMaximumSize(new Dimension(670, 20));
        comp3.setOpaque(false);

        JLabel theOrder = new JLabel("Details:");
        theOrder.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        theOrder.setOpaque(false);
        theOrder.setForeground(allColour);

        comp4.add(theOrder);
        comp4.setBorder(new EmptyBorder(0, 0, 0, 110));
        comp4.setMaximumSize(new Dimension(670, 65));
        comp4.setOpaque(false);

        table.setFont(new Font("Times New Roman", Font.BOLD, 20));
        table.setRowHeight(50);
        table.setOpaque(false);
        table.setBorder(null);
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);

        JScrollPane dishesBox = new JScrollPane(table);
        dishesBox.setOpaque(false);
        dishesBox.getViewport().setOpaque(false);
        dishesBox.setBorder(BorderFactory.createEmptyBorder());

        comp5.add(dishesBox);
        comp5.setBorder(new EmptyBorder(0, 110, 0, 110));
        comp5.setMaximumSize(new Dimension(1270, 255));
        comp5.setOpaque(false);

        frame.getContentPane().add(bill);
        frame.getContentPane().add(comp2);
        frame.getContentPane().add(comp3);
        frame.getContentPane().add(comp4);
        frame.getContentPane().add(comp5);
        frame.setSize(872, 775);
        frame.setVisible(true);
    }
}
