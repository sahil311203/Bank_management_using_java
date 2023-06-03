package myPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.sql.*;
 
public class ToSelfTrans extends JFrame implements ActionListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3 ;
    JTextField t1 , t2 ;
    //String num,bal;
    int acc_num,to_ac_num,pin,cpin;
    long sentamo,balamo;
    Connection con;
    Statement st;

    JFrame frame = new JFrame();
    public ToSelfTrans(int num,int pi,long am)
    {
        acc_num=num;
        pin=pi;
        balamo=am;
        
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        l1 = new JLabel("Self Transfer ");
        l1.setFont(new Font("Verdana", Font.BOLD, 30));
        l2 = new JLabel("Enter Amount ");
        l2.setFont(new Font("Verdana",Font.PLAIN,17));
        l3 = new JLabel("Enter PIN ");;
        l3.setFont(new Font("Verdana",Font.PLAIN,17));
        
        t1 = new JTextField(120);
        t2 = new JTextField(120);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(120,20,320,80);
        l2.setBounds(20,80,130,40);
        l3.setBounds(20,120,130,40);
        t1.setBounds(160,90,150,20);
        t2.setBounds(160,130,150,20);
        submit.setBounds(140,180,160,40);
        
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
        setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back)
        {
            // setVisible(false);
            frame.dispose();
            homePage obj = new homePage(acc_num);
        }
        else if(e.getSource()==submit)
        {
            long total ;
            sentamo=Long.parseLong(t1.getText());
            cpin = Integer.parseInt(t2.getText());
            if(pin==cpin)
            {
                try
                {
                    // String q = "select acc_num,lamo from regTab ";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                    String qu = " ";
                    st=con.createStatement();
                        total = sentamo + balamo;
                        qu = "update regTab set lamo = "+total+" where acc_num = "+acc_num;
                        System.out.println(total);
                    // st.executeUpdate(q);
                    // System.out.println("q executed");
                    st.executeUpdate(qu);
                    System.out.println("qu executed");
                    JOptionPane.showMessageDialog(this,"Amount Transferred");

                }
                catch(Exception se){
                    System.out.println(se);
                }
            }
            else 
            {
                JOptionPane.showMessageDialog(this,"Wrong PIN !!!!! Transfer Failed");
            }
        }
        
    }

}