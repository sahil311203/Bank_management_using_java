package myPack;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.text.*;
import java.awt.event.*;
import java.sql.*;

public class MRecharge extends JFrame implements ActionListener,ItemListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3 , l4;
    JTextField t1 , t2 ;
    JComboBox rcombo,Pcombo ;
    String rtype[] = {"Airtel ", "VI" , "JIO"};
    String Ptype1[] ={"249","180","450"};
    String Ptype2[] ={"230","150","430"};
    String Ptype3[] ={"280","199","530"};
    int acc_num,to_ac_num,pin,cpin;
    long sentamo,balamo;
    Connection con;
    Statement st;
    String rech,date;

    JFrame frame = new JFrame();
    public MRecharge(int num,int pi,long am)
    {

        acc_num=num;
        pin=pi;
        balamo=am;

        
        back = new JButton("Back");
        back.addActionListener(this);
        
        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        l1 = new JLabel("Mobile Recharge ");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));
        l2 = new JLabel("Select Card ");
        l3 = new JLabel("Enter Mobile No. ");
        l4 = new JLabel("Select Plan");
        
        rcombo = new JComboBox(rtype);
        rcombo.addItemListener(this);
        Pcombo=new JComboBox(Ptype1);
        
        t1 = new JTextField(120);
        t2 = new JTextField(120);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,100,20);
        l3.setBounds(20,120,120,20);
        l4.setBounds(20,150,100,20);
        rcombo.setBounds(160,80,120,20);
        t1.setBounds(160,120,120,20);
        Pcombo.setBounds(160,150,120,20);
        submit.setBounds(190,200,90,25);
        
        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(rcombo);
        frame.add(Pcombo);
        frame.add(l3);
        frame.add(t1);
        frame.add(l4);
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
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == back )
        {
            // setVisible(false);
            frame.dispose();
            homePage obj = new homePage(acc_num);
            // Format shortDate = DateFormat.getDateInstance(DateFormat.LONG);
            // date=new Date(shortDate);
            //System.out.println();    
        }
        else if(e.getSource()==submit)
        {
            //JOptionPane.showMessageDialog(this,"Recharge Successful");
            long total ;
            sentamo=Long.parseLong(Pcombo.getSelectedItem().toString());
            cpin = Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Your pin :"));
            rech = rcombo.getSelectedItem().toString();
            if(pin==cpin)
            {
                long mob=Long.parseLong(t1.getText());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String rcdate = formatter.format(date);
                // System.out.print("Current date: "+rcdate);
                if(sentamo<=balamo)
                {
                    try
                    {
                        String q = " ";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                        String qu = " ";
                        st=con.createStatement();
                            total = balamo - sentamo;
                            qu = "update regTab set lamo = "+total+" where acc_num = "+acc_num;
                            q = "insert into recharge values("+acc_num+",'"+rcombo.getSelectedItem()+"',"+mob+",'"+rcdate+"',"+sentamo+")";
                            System.out.println(q);
                         st.executeUpdate(q);
                         st.executeUpdate(qu);
                        JOptionPane.showMessageDialog(this,"Your Recharge for: "+rech+"\nof amount: "+sentamo+"\n Is Successful ");


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
    public void itemStateChanged(ItemEvent i)
        {
            if (i.getSource()==rcombo)
            {
                if (rcombo.getSelectedItem().equals("Airtel"))
                {
                    Pcombo.removeAllItems();
                    for (int k=0;k<Ptype1.length;k++)
                        Pcombo.addItem(Ptype1[k]);

                }
                if (rcombo.getSelectedItem().equals("VI"))
                {
                    Pcombo.removeAllItems();
                    for (int k=0;k<Ptype2.length;k++)
                        Pcombo.addItem(Ptype2[k]);

                }
                if (rcombo.getSelectedItem().equals("JIO"))
                {
                    Pcombo.removeAllItems();
                    for (int k=0;k<Ptype3.length;k++)
                        Pcombo.addItem(Ptype3[k]);

                }
            }
        }

}
