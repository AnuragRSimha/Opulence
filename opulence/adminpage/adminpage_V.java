package opulence.adminpage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class adminpage_V {
    JTextField MTA = new JTextField();
    JTextField MP = new JTextField();
    JTextField MTR = new JTextField();
    JTextField onePlate = new JTextField();
    JButton amend = new JButton("Amend");
    JButton truncate = new JButton("Erase Menu");
    JLabel status = new JLabel("", SwingConstants.CENTER);
    JList menu = new JList();

    public void TriggerChange(String added, String removed, String Price, String plateVal) throws InterruptedException {
        status.setForeground(Color.decode("#D09E00"));
        status.setFont(new Font("Candara Light", Font.PLAIN, 19));
        if (!added.equals("") && !Price.equals("") && !removed.equals("") && !plateVal.equals("")
                && !Price.equals("0")) {
            status.setText("Added " + added + " and removed " + removed + ".");
        } else if (!added.equals("") && !Price.equals("") && !plateVal.equals("") && !Price.equals("0")) {
            status.setText("Added " + added + ".");
        } else if (!removed.equals("")) {
            status.setText("Removed " + removed + ".");
        }
        Timer timer = new Timer(3000, e -> status.setText(""));
        timer.start();
    }

    public void RefuseUpdate(boolean b) {
        status.setForeground(Color.decode("#D09E00"));
        status.setFont(new Font("Candara Light", Font.PLAIN, 19));
        if (b == false) {
            status.setText("Denied Update. Empty fields aren't accepted.");
            Timer timer = new Timer(3000, e -> status.setText(""));
            timer.start();
        }
    }

    public void DenyUpdate(String added) {
        status.setForeground(Color.decode("#D09E00"));
        status.setFont(new Font("Candara Light", Font.PLAIN, 22));
        status.setText(added + " already exists on the menu.");
        Timer timer = new Timer(3000, e -> status.setText(""));
        timer.start();
    }

    public void setFoodMenu(String[] meals) {
        menu.setBackground(new Color(0, 0, 0, 0));
        menu.setForeground(Color.decode("#D09E00"));
        menu.setFont(new Font("French Script MT", Font.ITALIC, 28));
        menu.setListData(meals);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menu.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void configureadminpage(Image img) {
        JFrame frame = new JFrame("Opulence");
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
        JPanel comp2 = new JPanel(new GridLayout(1, 3));
        JPanel comp3 = new JPanel(new GridLayout(1, 1));
        JPanel comp4 = new JPanel(new GridLayout(1, 1));

        JPanel addMealPanel = new JPanel(new GridLayout(1, 2));
        JLabel mealToAdd = new JLabel("Meal to add:");
        mealToAdd.setForeground(allColour);
        mealToAdd.setFont(font);
        mealToAdd.setSize(20, 10);
        MTA.setFont(font);
        MTA.setForeground(allColour);
        MTA.setBorder(textBorder);
        MTA.setOpaque(false);
        addMealPanel.add(mealToAdd, 0);
        addMealPanel.add(MTA, 1);
        addMealPanel.setOpaque(false);

        JPanel pricePanel = new JPanel(new GridLayout(1, 2));
        JLabel mealprice = new JLabel("Price:");
        mealprice.setForeground(allColour);
        mealprice.setFont(font);
        mealprice.setSize(20, 10);
        MP.setFont(font);
        MP.setForeground(allColour);
        MP.setBorder(textBorder);
        MP.setOpaque(false);
        pricePanel.add(mealprice, 0);
        pricePanel.add(MP, 1);
        pricePanel.setOpaque(false);

        JPanel onePlatePanel = new JPanel(new GridLayout(1, 2));
        JLabel inOnePlate = new JLabel("One Plate (Pcs.) =");
        inOnePlate.setForeground(allColour);
        inOnePlate.setFont(font);
        inOnePlate.setSize(20, 10);
        onePlate.setFont(font);
        onePlate.setForeground(allColour);
        onePlate.setBorder(textBorder);
        onePlate.setOpaque(false);
        onePlatePanel.add(inOnePlate, 0);
        onePlatePanel.add(onePlate, 1);
        onePlatePanel.setOpaque(false);

        JPanel removeMealPanel = new JPanel(new GridLayout(1, 2));
        JLabel mealToRemove = new JLabel("Meal to remove:");
        mealToRemove.setForeground(allColour);
        mealToRemove.setFont(font);
        mealToRemove.setSize(20, 10);
        MTR.setFont(font);
        MTR.setForeground(allColour);
        MTR.setBorder(textBorder);
        MTR.setOpaque(false);
        removeMealPanel.add(mealToRemove);
        removeMealPanel.add(MTR);
        removeMealPanel.setOpaque(false);

        amend.setBackground(allColour);
        amend.setForeground(Color.black);
        amend.setFont(font2);
        amend.setBorderPainted(false);

        truncate.setBackground(allColour);
        truncate.setForeground(Color.black);
        truncate.setFont(new Font("Old English Text MT", Font.BOLD, 15));
        truncate.setBorderPainted(false);

        comp1.setOpaque(false);
        comp1.add(addMealPanel);
        comp1.add(pricePanel);
        comp1.add(onePlatePanel);
        comp1.add(removeMealPanel);
        comp1.add(Box.createRigidArea(new Dimension(0, 0)));
        comp1.add(status);
        comp1.setBorder(new EmptyBorder(160, 10, 10, 10));
        comp1.setMaximumSize(new Dimension(400, 370));

        comp2.setOpaque(false);
        comp2.add(amend);
        comp2.add(Box.createRigidArea(new Dimension(0, 0)));
        comp2.add(truncate);
        comp2.setMaximumSize(new Dimension(350, 50));

        JLabel listMenu = new JLabel("Menu", SwingConstants.CENTER);
        listMenu.setForeground(allColour);
        listMenu.setFont(new Font("Vivaldi", Font.BOLD, 35));
        listMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, allColour));

        comp4.setOpaque(false);
        comp4.add(listMenu);
        comp4.setBorder(new EmptyBorder(30, 0, 0, 0));
        comp4.setMaximumSize(new Dimension(400, 64));

        JScrollPane theMenu = new JScrollPane(menu);
        theMenu.setOpaque(false);
        theMenu.setBorder(null);
        comp3.setOpaque(false);
        comp3.add(theMenu);
        comp3.setBorder(new EmptyBorder(11, 0, 0, 0));
        comp3.setMaximumSize(new Dimension(400, 192));

        frame.getContentPane().add(comp1);
        frame.getContentPane().add(comp2);
        frame.getContentPane().add(comp4);
        frame.getContentPane().add(comp3);
        frame.setSize(872, 775);
        frame.setVisible(true);
    }
}
