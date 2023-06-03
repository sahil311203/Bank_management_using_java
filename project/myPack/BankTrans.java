package myPack;

import java.awt.*; 
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;
import java.text.*;

public class BankTrans extends JFrame implements ActionListener
{
    JButton submit ,back ;
    JLabel l1 ,l2 , l3  , l4;
    JTextField t1 , t2 ;
    static BankTrans d;
    int acc_num,to_ac_num,pin,cpin;
    String bank_na;
    long sentamo,balamo;
    JComboBox Bname;
    Connection con;
    Statement st;
    String bank_name[]={"State Bank Of India","Punjab national bank","Bank of India"};

    JFrame frame = new JFrame();
    public BankTrans(int num,int pi,long am)
    {
        acc_num=num;
        pin=pi;
        balamo=am;
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        submit = new JButton("SEND");
        submit.addActionListener(this);
        l1 = new JLabel("BANK TRANSFER ");
        l1.setFont(new Font("Verdana", Font.BOLD, 25));
        l2 = new JLabel("Account No. ");
        l2.setFont(new Font("Verdana", Font.BOLD, 14));
        l3 = new JLabel("Enter Amount ");
        l3.setFont(new Font("Verdana", Font.BOLD, 14));
        l4 = new JLabel("Select Bank ");
        l4.setFont(new Font("Verdana", Font.BOLD, 14));
        Bname=new JComboBox(bank_name);
        
        t1 = new JTextField(120);
        t2 = new JTextField(120);
        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
        l2.setBounds(20,80,100,20);
        l4.setBounds(20,120,100,20);
        l3.setBounds(20,160,100,20);
        t1.setBounds(160,80,200,20);
        Bname.setBounds(160,120,200,20);
        t2.setBounds(160,160,200,20);
        submit.setBounds(160,190,100,30);
        
        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(Bname);
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
            //setVisible(false);
            frame.dispose();
            homePage obj = new homePage(acc_num);
        }
        else if(e.getSource()==submit)
        {
            sentamo=Long.parseLong(t2.getText());
            String p=JOptionPane.showInputDialog(this,"Confirm pin");
            cpin=Integer.parseInt(p);
            if(pin==cpin)
            {
                if(sentamo<=balamo)
                {
                    to_ac_num=Integer.parseInt(t1.getText());
                    bank_na=Bname.getSelectedItem().toString();

                    try
                    {
                        long total ;
                        String q = "select acc_num,lamo from regTab";
                        String qu = " ";
                        String qa = " ";
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String paydate = formatter.format(date);
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");

                        st=con.createStatement();
                        ResultSet rs = st.executeQuery(q);
                        // System.out.println(rs.getString(1));
                        while(rs.next())
                        {
                            int acc_no = Integer.parseInt(rs.getString(1));
                            if(to_ac_num==acc_no)
                            {
                                long balamount = rs.getLong(2);
                                total = sentamo+balamount;
                                 qa="insert into banktan values("+acc_num+","+to_ac_num+",'"+bank_na+"',"+sentamo+",'"+paydate+"')";
                                // System.out.println(q);
                                // st.executeUpdate(qa);
                                q ="update regtab set lamo="+total+" where acc_num="+to_ac_num+" ";
                                st.executeUpdate(q);
                               // System.out.println(q);
                                // st.executeUpdate(q);
                                total=balamo-sentamo;
                                qu="update regtab set lamo="+total+" where acc_num="+acc_num+" ";
                                //System.out.println(qu);
                                // st.executeUpdate(qu);
                                break;
                            }
                            else 
                            {
                                q="";
                                qa="insert into banktan values("+acc_num+","+to_ac_num+",'"+bank_na+"',"+sentamo+",'"+paydate+"')";
                                // System.out.println(q);
                                //st.executeUpdate(q);
                                total=balamo-sentamo;
                                qu="update regtab set lamo="+total+" where acc_num="+acc_num+" ";
                                System.out.println(qu);
                                //st.executeUpdate(qu);
                            }
                            

                        }
                        
                        st.executeUpdate(qa);
                        st.executeUpdate(qu);
                        st.close();
                        JOptionPane.showMessageDialog(this,"Transfered succesfully");
                    }
                    catch(Exception a)
                    {
                        System.out.println(a);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"You don't have sufficient balance");
                }
            }
            else
                JOptionPane.showMessageDialog(this,"Wrong PIN !!!!! Transfer Failed");
        
        }
        
    }

} 