package myPack;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;
import java.awt.event.*;
import java.text.*;

public class UPI extends JFrame implements ActionListener {
    JLabel l1, l2, l3,l4,blabel,lmess;
    JTextField upiNo, amo, mess;
    JButton send, back;
    ImageIcon back_img;
    Connection con;
    Statement st;
    static UPI d;
    int acc_num,to_ac_num,pin,cpin;
    long balamo,total=0;

    JFrame frame = new JFrame();
    public UPI(int num,int pi,long am) 
    {
        
        acc_num=num;
        pin=pi;
        balamo=am;

        //image
        back_img = new ImageIcon("rupee_light.jpg");
        Image bci = back_img.getImage();
        Image temp_img = bci.getScaledInstance(500,500,Image.SCALE_SMOOTH);
        back_img = new ImageIcon(temp_img);
        blabel = new JLabel(" ",back_img,JLabel.CENTER);
        blabel.setBounds(0,0,500,350);
        
        back = new JButton("Back");
        back.addActionListener(this);
        lmess=new JLabel("Message");
        mess= new JTextField();
        
        
        send = new JButton("SEND");
        send.addActionListener(this);
        mess = new JTextField(120);
        l1 = new JLabel("UPI TRANSFER ");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));
        l2 = new JLabel("Enter UPI id : ");
        l3 = new JLabel("Enter Amount : ");
        l4 =new JLabel("Enter message for the sender");
        
        upiNo = new JTextField(120);
        amo = new JTextField(120);
        
        back.setBounds(10, 2, 90, 22);
        l1.setBounds(100, 20, 320, 50);
        l2.setBounds(20, 80, 100, 20);
        l3.setBounds(20, 120, 100, 20);
        lmess.setBounds(20,160,100,20);
        upiNo.setBounds(160, 80, 120, 20);
        amo.setBounds(160, 120, 120, 20);
        mess.setBounds(160,160,120,20);
        send.setBounds(140, 210, 120, 30);
        
        blabel.add(back);
        blabel.add(l1);
        blabel.add(l2);
        blabel.add(l3);
        blabel.add(l4);
        blabel.add(upiNo);
        blabel.add(amo);
        blabel.add(mess);
        blabel.add(send);
        blabel.add(lmess);
        blabel.add(mess);
        
        frame.add(blabel);
        frame.setSize(500, 350);
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
        else if(e.getSource()==send)
        {
            if (upiNo.getText().equals("") && amo.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid UPI id and amount");
            else if (upiNo.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid UPI id ");
            else if(amo.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Please enter valid amount");
            else {
                String id=upiNo.getText();
                long am=Long.parseLong(amo.getText());
                String mes=mess.getText();
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
                            q = "insert into upi values("+acc_num+",'"+id+"',"+am+",'"+paydate+"')";
                            st.executeUpdate(q);
                            st.executeUpdate(qu);
                            JOptionPane.showMessageDialog(this,"Transaction to "+id+" of \namount "+am+" is successful \nMassage :"+mes);
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


