package myPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;

public class DTH extends JFrame implements ActionListener
{

    JButton submit ,back ;
    JLabel l1 ,l2 , l3 , l4;
    JTextField t1 ;
    JComboBox dCom,pCom ;
    int acc_num,to_ac_num,pin,cpin;
    long payamo,balamo,Did;
    Connection con;
    Statement st;
    String dType[] = {"Tata play ", "Airtel" , "Videocon D2h"};
    String pType[] ={"299","399","499"};
    String Dtbill;

    JFrame frame = new JFrame();
    public DTH(int num,int pi,long am)
    {
        acc_num=num;
        pin=pi;
        balamo=am;

        
        back = new JButton("Back");
        back.addActionListener(this);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        
        l1 = new JLabel("DTH Recharge ");
        l1.setFont(new Font("Verdana", Font.BOLD, 25));
        l2 = new JLabel("Select Service");
        l2.setFont(new Font("Verdana", Font.BOLD, 14));
        l3 = new JLabel("Select plan");
        l3.setFont(new Font("Verdana", Font.BOLD, 14));
        l4 = new JLabel("DTH ID");
        l4.setFont(new Font("Verdana", Font.BOLD, 14));


        dCom=new JComboBox(dType);
        pCom=new JComboBox(pType);
        
        t1 = new JTextField(120);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,100,20);
        l3.setBounds(20,120,100,20);
        l4.setBounds(30,150,100,20);
        dCom.setBounds(160,80,120,20);
        pCom.setBounds(160,120,120,20);
        t1.setBounds(160,150,120,20);
        submit.setBounds(190,180,90,25);
        
        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(dCom);
        frame.add(l3);
        frame.add(pCom);
        frame.add(l4);
        frame.add(t1);
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
                long total ;
                Dtbill = dCom.getSelectedItem().toString();
                payamo=Long.parseLong(pCom.getSelectedItem().toString());
                Did = Long.parseLong(t1.getText());
                String p=JOptionPane.showInputDialog(this,"Confirm pin");
                cpin = Integer.parseInt(p);
                long mob=Long.parseLong(t1.getText());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String paydate = formatter.format(date);
                if(pin==cpin)
                {
                    if(payamo<=balamo)
                    {
                        try
                        {
                            String q = " ";
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                            String qu = " ";
                            st=con.createStatement();
                                total = balamo - payamo;
                                qu = "update regtab set lamo = "+total+" where acc_num = "+acc_num;
                                q = "insert into dbill values("+acc_num+",'"+Dtbill+"',"+Did+",'"+paydate+"',"+payamo+")";
                                System.out.println(total);
                            st.executeUpdate(q);
                            System.out.println("q executed");
                            st.executeUpdate(qu);
                            System.out.println("qu executed");
                            JOptionPane.showMessageDialog(this,"Your DTH Bill of : "+Dtbill+"\nof amount: "+payamo+"\n Is Successful ");
                            
                            
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
