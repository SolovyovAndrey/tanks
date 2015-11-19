package tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChooseTank {
    public int i;

    public int createTankPanel() throws InterruptedException {
        i = 2;

        final JFrame fr = new JFrame("BATTLE FIELD, DAY 2");
        fr.setLocation(550, 100);
        fr.setMinimumSize(new Dimension(500, 500));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel newName = new JLabel("Select your tank");
        panel.add(newName, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        JLabel newZero = new JLabel(" ");
        panel.add(newZero, new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        JLabel lName = new JLabel("High armore  ");
        panel.add(lName, new GridBagConstraints(0,3,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));


        JButton btn1 = new JButton(new ImageIcon("src/main/resources/tankDA.png"));
        panel.add(btn1, new GridBagConstraints(1,3,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        JLabel lTank = new JLabel("  Tiger");
        panel.add(lTank, new GridBagConstraints(2,3,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        JButton btn2 = new JButton(new ImageIcon("src/main/resources/tankD.png"));
        panel.add(btn2, new GridBagConstraints(1,4,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        JButton btn3 = new JButton(new ImageIcon("src/main/resources/tankDS.png"));
        panel.add(btn3, new GridBagConstraints(1,5,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));

        fr.getContentPane().add(panel);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                i = 1 ;
                System.out.println("choice = " + i);
                fr.setVisible(false);

            }

        });


        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                i = 2 ;
                fr.setVisible(false);

            }

        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                i = 3;
                fr.setVisible(false);

            }

        });

        Thread.sleep(5000);
        fr.setVisible(false);
        return i;

    }

    public int youChoise (){
        return i;
    }
}
