package myPack;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class register extends JFrame implements ActionListener
{
    JPanel p ;
    JLabel uname , acctype , dob , phno , emailid ,labelback,lAmount;
    JLabel password , cpassword , pin , cpin ;
    JTextField tuname , tdob , tphno , temailid ,lamo;
    JPasswordField tpassword , tcpassword , tpin , tcpin ;
    JComboBox cacctype ;
    ImageIcon sp , hp,bkimg1,bkimg2;
    JButton shp ;
    JButton cb ; 
    JButton regi ;
    String Rname,Rat,Rdob,Rem,Rpass;
    long Rph,Ram;
    int Rpin;
    String upiid;
    Connection con;
    Statement st;
 
    String actype[] = {"Saving Account","Current Account" , "Fixed Deposite"};
    public register()
    {

        Font rf = new Font("Times New Roman",Font.BOLD,14);

        setTitle("Home-Page");
        setSize(400,480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);

        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.BLACK);
        p.setBounds(0,0,400,480);

        uname = new JLabel("Username ");
            uname.setFont(rf);
            uname.setForeground(Color.WHITE);
        acctype = new JLabel("Account Type ");
            acctype.setFont(rf);
            acctype.setForeground(Color.WHITE);
        dob = new JLabel("Date of Birth ");
            dob.setFont(rf);
            dob.setForeground(Color.WHITE);
        phno = new JLabel("Phone Number ");
            phno.setFont(rf);
            phno.setForeground(Color.WHITE);
        emailid = new JLabel("Email Id  ");
            emailid.setFont(rf);
            emailid.setForeground(Color.WHITE);
        password = new JLabel("Password ");
            password.setFont(rf);
            password.setForeground(Color.WHITE);
        cpassword = new JLabel("Confirm Password ");
            cpassword.setFont(rf);
            cpassword.setForeground(Color.WHITE);
        pin = new JLabel("PIN");
            pin.setFont(rf);
            pin.setForeground(Color.WHITE);
        cpin = new JLabel("Confirm PIN");
            cpin.setFont(rf);
            cpin.setForeground(Color.WHITE);
        lAmount = new JLabel("Initial Amount");
            lAmount.setFont(rf);
            lAmount.setForeground(Color.WHITE);
        labelback =new JLabel("");
        
        tuname = new JTextField(130);
        cacctype = new JComboBox(actype);
        tdob = new JTextField(130);
        tphno = new JTextField(130);
        temailid = new JTextField(130);
        lamo = new JTextField(130);
        tpassword = new JPasswordField(130);
        tpassword.setEchoChar('*');
        tcpassword = new JPasswordField(130);
        tpin = new JPasswordField(130);
        tcpin = new JPasswordField(130);

        
        regi = new JButton("REGISTER");
        regi.addActionListener(this);
        regi.setBounds(150,390,110,20);
//
        shp = new JButton();
        shp.setBounds(270,230,30,30);
        shp.addActionListener(this);

        hp = new ImageIcon("hide.jpg");
        Image img1 = hp.getImage();
        Image newImage1 = img1.getScaledInstance(shp.getWidth(), shp.getHeight(), Image.SCALE_SMOOTH);
        hp = new ImageIcon(newImage1);

        sp = new ImageIcon("show.jpg");
        Image img = sp.getImage();
        Image newImage = img.getScaledInstance(shp.getWidth(), shp.getHeight(), Image.SCALE_SMOOTH);
        sp = new ImageIcon(newImage);
        shp.setIcon(hp);
        // shp.setPressedIcon(hp);
        
        bkimg1 = new ImageIcon("rupee_light.jpg");
        Image img2=bkimg1.getImage();
        bkimg2 = new ImageIcon("rupee_dark.jpg");
        Image img3=bkimg2.getImage();


        cb = new JButton("CB");

        cb.setBounds(320,10,40,30);
        cb.addActionListener(this);
        
        uname.setBounds(20,80,100,20);
        acctype.setBounds(20,110,100,20);
        dob.setBounds(20,140,100,20);
        phno.setBounds(20,170,100,20);
        lAmount.setBounds(20,200,100,20);
        emailid.setBounds(20,230,100,20);
        password.setBounds(20,260,100,20);
        cpassword.setBounds(20,290,100,20);
        pin.setBounds(20,320,100,20);
        cpin.setBounds(20,350,100,20);

        tuname.setBounds(120,80,130,20);
        cacctype.setBounds(120,110,130,20);
        tdob.setBounds(120,140,130,20);
        tphno.setBounds(120,170,130,20);
        lamo.setBounds(120,200,130,20);
        temailid.setBounds(120,230,130,20);
        tpassword.setBounds(120,260,130,20);
        tcpassword.setBounds(120,290,130,20);
        tpin.setBounds(120,320,130,20);
        tcpin.setBounds(120,350,130,20);
        labelback.setBounds(0,0,400,450);
        labelback.setIcon(bkimg1);

        labelback.add(uname);
        labelback.add(acctype);
        labelback.add(dob);
        labelback.add(phno);
        labelback.add(emailid);
        labelback.add(tuname);
        labelback.add(cacctype);
        labelback.add(tdob);
        labelback.add(tphno);
        labelback.add(temailid);
        labelback.add(password);
        labelback.add(cpassword);
        labelback.add(pin);
        labelback.add(cpin);
        labelback.add(tpassword);
        labelback.add(tcpassword);
        labelback.add(tpin);
        labelback.add(tcpin);
        labelback.add(regi);
        labelback.add(shp);
        labelback.add(cb);
        labelback.add(lAmount);
        labelback.add(lamo);
        p.add(labelback);
        add(p);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cb)
        {

            if(labelback.getIcon()==bkimg1)
            {
            labelback.setIcon(bkimg2);
            uname.setForeground(Color.orange);
            acctype.setForeground(Color.orange);
            dob.setForeground(Color.orange);
            phno.setForeground(Color.orange);
            emailid.setForeground(Color.orange);
            password.setForeground(Color.orange);
            cpassword.setForeground(Color.orange);
            pin.setForeground(Color.orange);
            cpin.setForeground(Color.orange);
            }
            else 
            {
                labelback.setIcon(bkimg1);
            // p.setForeground(Color.BLACK);
            uname.setForeground(Color.WHITE);
            acctype.setForeground(Color.WHITE);
            dob.setForeground(Color.WHITE);
            phno.setForeground(Color.WHITE);
            emailid.setForeground(Color.WHITE);
            password.setForeground(Color.WHITE);
            cpassword.setForeground(Color.WHITE);
            pin.setForeground(Color.WHITE);
            cpin.setForeground(Color.WHITE);
            }
        }
            else if(e.getSource()==regi)
            {
                if(tpassword.getText().equals(tcpassword.getText()) && tpin.getText().equals(tcpin.getText()))
                {
                    Rname=tuname.getText();
                    Rat=cacctype.getSelectedItem().toString();
                    Rdob=tdob.getText();
                    Rem=temailid.getText();
                    Rpass=tpassword.getText();
                    Rph=Long.parseLong(tphno.getText());
                    Ram=Long.parseLong(lamo.getText());
                    Rpin=Integer.parseInt(tpin.getText());
                    upiid=tphno.getText()+"@ybl";
                   try
                   {
                        String q="insert into regtab (username,Acc_type, DOB,ph_num,email_id,pass,pin,lamo,UPI_ID) values('"+Rname+"','"+Rat+"','"+Rdob+"',"+Rph+",'"+Rem+"','"+Rpass+"',"+Rpin+","+Ram+",'"+upiid+"')";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                        System.out.println(q);
                        st=con.createStatement();
                        st.executeUpdate(q);
                        st.close();
                   }
                   catch(Exception a)
                   {
                        System.out.println(a);
                   }
                    JOptionPane.showMessageDialog(this,"Account Registered");
                }
                
               
            }
            else if(e.getSource()==shp)
            {
            if(shp.getIcon()==sp)
            {
                tpassword.setEchoChar('*');
                shp.setIcon(hp);
            }
            else
            {
                tpassword.setEchoChar('\u0000');
                shp.setIcon(sp);
            }
        }
    }   
}
