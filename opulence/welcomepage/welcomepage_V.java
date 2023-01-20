package opulence.welcomepage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class welcomepage_V {
    JFrame frame = new JFrame("Opulence");
    JList menu = new JList();
    JScrollPane fullList = new JScrollPane(menu);
    JButton payment = new JButton("Pay £ 0");
    JButton clearBox = new JButton("Clear");
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel) {
        public boolean isCellEditable(int row, int column) {
            if (column == 0)
                return false;// the 4th column is not editable
            return true;
        }
    };

    public void setFoodMenu(String[] meals) {
        menu.setBackground(new Color(0, 0, 0, 0));
        menu.setForeground(Color.decode("#D09E00"));
        menu.setFont(new Font("French Script MT", Font.ITALIC, 28));
        menu.setListData(meals);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menu.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void configureWelcomepage(Image img, String customerName, String tableNumber, String designation) {
        Color allColour = Color.decode("#D09E00");
        Font font2 = new Font("Vivaldi", Font.PLAIN, 22);
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
        JPanel comp1 = new JPanel(new GridLayout(1, 1));
        JPanel comp2 = new JPanel(new GridLayout(1, 1));
        JPanel comp3 = new JPanel(new GridLayout(1, 1));
        JPanel comp4 = new JPanel(new GridLayout(1, 3));
        JPanel welcomeCustomer = new JPanel(new GridLayout(1, 1));
        JLabel welCust = new JLabel("Welcome, " + designation + " " + customerName);
        welCust.setForeground(allColour);
        welCust.setFont(font2);
        welcomeCustomer.add(welCust);
        welcomeCustomer.setOpaque(false);
        welcomeCustomer.setBorder(new EmptyBorder(170, 80, 0, 0));
        welcomeCustomer.setMaximumSize(new Dimension(400, 250));

        JLabel listMenu = new JLabel("Menu", SwingConstants.CENTER);
        listMenu.setForeground(allColour);
        listMenu.setFont(new Font("Vivaldi", Font.BOLD, 35));
        listMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, allColour));

        fullList.setForeground(allColour);
        fullList.setOpaque(false);
        fullList.setBorder(null);
        fullList.setFont(new Font("Vivaldi", Font.BOLD, 35));

        table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        table.setGridColor(allColour);
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("");

        JScrollPane dishesBox = new JScrollPane(table);
        dishesBox.setPreferredSize(new Dimension(40, 0));

        JPanel blank = new JPanel();
        blank.setMaximumSize(new Dimension(0, 20));

        JPanel blank2 = new JPanel();
        blank2.setMaximumSize(new Dimension(0, 20));

        JPanel blank3 = new JPanel();
        blank3.setMaximumSize(new Dimension(10, 0));

        comp1.setOpaque(false);
        comp1.add(listMenu);
        comp1.setMaximumSize(new Dimension(400, 40));

        comp2.setOpaque(false);
        comp2.add(fullList);
        comp2.setMaximumSize(new Dimension(400, 248));
        comp2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, allColour));

        comp3.setOpaque(false);
        comp3.add(dishesBox);
        comp3.setMaximumSize(new Dimension(400, 80));
        comp3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, allColour));

        payment.setBackground(allColour);
        payment.setForeground(Color.black);
        payment.setFont(new Font("Old English Text MT", Font.BOLD, 15));
        payment.setBorderPainted(false);

        clearBox.setBackground(allColour);
        clearBox.setForeground(Color.black);
        clearBox.setFont(new Font("Old English Text MT", Font.BOLD, 15));
        clearBox.setBorderPainted(false);

        comp4.setOpaque(false);
        comp4.add(payment);
        blank3.setOpaque(false);
        comp4.add(blank3);
        comp4.add(clearBox);
        comp4.setMaximumSize(new Dimension(500, 40));
        comp4.setBorder(new EmptyBorder(0, 50, 0, 50));

        frame.getContentPane().add(welcomeCustomer);
        frame.getContentPane().add(comp1);
        frame.getContentPane().add(comp2);
        frame.getContentPane().add(blank);
        frame.getContentPane().add(comp3);
        frame.getContentPane().add(blank2);
        frame.getContentPane().add(comp4);
        frame.setSize(872, 775);
        frame.setVisible(true);
    }
}
