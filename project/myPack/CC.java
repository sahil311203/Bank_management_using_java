package myPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;


public class CC extends JFrame implements ActionListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3;
    Connection con;
    Statement st;
    JTextField t1 , t2 ;
    String num,bal;
    int acc_num,to_ac_num,pin,cpin;
    long total,balamo;

    JFrame frame = new JFrame();
    public CC(int num,int pi,long am)
    {
        
        acc_num = num;
        pin = pi;
        balamo=am;

        back = new JButton("Back");
        back.addActionListener(this);
        
        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        l1 = new JLabel("ADD ACCOUNT BALANCE ");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));
        l2 = new JLabel("Card Number ");
        l3 = new JLabel("Enter Amount ");
        
        t1 = new JTextField(120);
        t2 = new JTextField(120);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,100,20);
        l3.setBounds(20,120,100,20);
        t1.setBounds(160,80,120,20);
        t2.setBounds(160,120,120,20);
        submit.setBounds(190,150,90,15);
        
        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(t1);
        frame.add(t2);
        frame.add(submit);

        frame.setSize(500,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(21, 112, 208, 203));
        // frame.setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back )
        {
            // setVisible(false);            
            frame.dispose();
            homePage obj = new homePage(acc_num);
        }
        else if(e.getSource()==submit)
        {
            if (t1.getText().equals("") && t2.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid UPI id and amount");
            else if (t1.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid UPI id ");
            else if(t2.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid amount");
            else {
                long card_num=Long.parseLong(t1.getText());
                long am=Long.parseLong(t2.getText());
                cpin=Integer.parseInt(JOptionPane.showInputDialog(this, "Confirm pin"));
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String paydate = formatter.format(date);
                if(pin==cpin)
                {
                    if(am<=balamo)
                    {
                        try
                        {
                            String q=" ",qu=" ";
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                            st=con.createStatement();
                            total = balamo-am;
                            qu = "update regtab set lamo = "+total+" where acc_num = "+acc_num;
                            q = "insert into credit values("+acc_num+",'"+card_num+"',"+am+",'"+paydate+"')";
                            st.executeUpdate(q);
                            st.executeUpdate(qu);
                            JOptionPane.showMessageDialog(this,"Your transaction was sucessful");
                        }
                        catch(Exception se)
                        {
                            System.out.println(se);
                        }
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Not Suuficient Balance ");
                    }
                }
                else 
                {
                    JOptionPane.showMessageDialog(this,"Wrong PIN !!!!! Transfer Failed");
                }
            }
            
        }
        
    }

}