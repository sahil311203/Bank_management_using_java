package myPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class accBal extends JFrame implements ActionListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3 , l4 , l5 ,l6;
    JTextField t1 , t2 ;
    String num,bal;
    public accBal()
    {
        setSize(500,350);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBackground(Color.BLACK);
        setLayout(null);

        back = new JButton("Back");
        back.addActionListener(this);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        l1 = new JLabel("ADD ACCOUNT BALANCE ");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));
        l2 = new JLabel("Account Number ");
        l3 = new JLabel("Enter Amount ");
        l4 = new JLabel("Show Balance");
        l5 = new JLabel("");
        l6 = new JLabel("");

        t1 = new JTextField(120);
        t2 = new JTextField(120);

        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,100,20);
        l3.setBounds(20,120,100,20);
        t1.setBounds(160,80,120,20);
        t2.setBounds(160,120,120,20);
        submit.setBounds(190,150,90,15);
        l4.setBounds(20,200,100,50);
        l5.setBounds(100,260,100,50);
        l6.setBounds(250,260,100,50);

        add(back);
        add(l1);
        add(l2);
        add(l3);
        add(t1);
        add(t2);
        add(submit);
        add(l4);
        add(l5);
        add(l6);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back )
        {
            setVisible(false);
        }
        else if(e.getSource()==submit)
        {
            num = t1.getText();
            bal = t2.getText();
            
            l5.setText(num);
            l6.setText(bal);
            repaint();
        }
        
    }

}