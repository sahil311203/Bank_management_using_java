package myPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
 
public class Elec extends JFrame implements ActionListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3 , l4 ;
    JTextField t1 ,t2 ;
    JComboBox elecombo ;
    static Elec d;
    int acc_num,to_ac_num,pin,cpin;
    long payamo,balamo,conid;
    Connection con;
    Statement st;
    String etype[] = {"Adani ", "Reliance"};
    String ebill;
    JFrame frame = new JFrame();

    public Elec(int num,int pi,long am)
    {
        acc_num=num;
        pin=pi;
        balamo=am;
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        submit = new JButton("PAY");
        submit.addActionListener(this);

        l1 = new JLabel("Electicity Bill PAYMENT ");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));
        l2 = new JLabel("Select Distributer ");
        l3 = new JLabel("Enter Consumer ID  ");
        l4 = new JLabel("Enter Amount  ");
        
        elecombo = new JComboBox(etype);
        
        t1 = new JTextField(120);
        t2 = new JTextField(120);
        t2.addActionListener(this);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,150,20);
        l3.setBounds(20,120,150,20);
        l4.setBounds(20,150,150,20);
        elecombo.setBounds(180,80,120,20);
        t1.setBounds(180,120,120,20);
        t2.setBounds(180,150,120,20);
        submit.setBounds(190,180,90,25);
        
        
        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(elecombo);
        frame.add(l3);
        frame.add(l4);
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
        else if(e.getSource()==submit || e.getSource()==t2)
        {   
            long total ;
            conid = Long.parseLong(t1.getText());
            payamo=Long.parseLong(t2.getText());
            String p=JOptionPane.showInputDialog(this,"Confirm pin");
            cpin = Integer.parseInt(p);
            ebill = elecombo.getSelectedItem().toString();
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
                        //con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                        String qu = " ";
                        st=con.createStatement();
                            total = balamo - payamo;
                            qu = "update regTab set lamo = "+total+" where acc_num = "+acc_num;
                            q = "insert into Elecbill values("+acc_num+",'"+ebill+"',"+conid+",'"+paydate+"',"+payamo+")";
                            //System.out.println(paydate);
                            //System.out.println(q);
                        st.executeUpdate(q);
                        st.executeUpdate(qu);
                        //System.out.println("qu executed");
                        JOptionPane.showMessageDialog(this,"Your Electricity Bill of amount "+payamo+" Is Successful ");
                        
                        
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
