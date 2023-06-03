package myPack;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
// import myPack.*;
/*public class animate implements ActionListener
{
    Color color0 = Color.red;
    Color color1 = Color.green;
    Color color2 = Color.blue;
    int Duration = 5000;
    long startTime;
}*/
public class homePage extends JFrame implements ActionListener
{
    ImageIcon  i1, i2 ,i3 , i4 , i5 , i6 , i7 , i8 , i9 ;
    ImageIcon bcimg;
    int tab,acc_num,pin;
    JLabel backLable;
    Image temp1 , temp2;
    JButton b1 , b2 , b3 ,b4 , b5 , b6 , b7,b8,logout ;
    JPanel p1 , p2 ;
    JLabel t1 , t2  ;
    JLabel l1 ;
    long balamo;
    Connection con;
    Statement st;

    JFrame frame = new JFrame();
    public homePage(int num)
    {

        acc_num=num;
        //background image
        bcimg = new ImageIcon("dash_back_1.jpg");
        Image bci = bcimg.getImage();
        Image temp_img = bci.getScaledInstance(500,500,Image.SCALE_SMOOTH);
        bcimg = new ImageIcon(temp_img);
        backLable = new JLabel(" ",bcimg,JLabel.CENTER);
        backLable.setBounds(0,0,500,500);
        //DashBoard 

        p1 = new JPanel();
        p2 = new JPanel();

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        
        //Panel 1 
        t1 = new JLabel("TRANSACTIONS");
        t1.setBounds(20,80,400,20);
        t1.setForeground(Color.WHITE);
        p1.setLayout(null);
        p1.setBackground(new Color(176, 122, 236, 125));

        b1 = new JButton(i1);
        i1 = new ImageIcon("bank.jpg");
        temp1 = i1.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(temp2);
        b1 = new JButton(i1);
        b1.addActionListener(this);
        b1.setToolTipText("Bank Transfer");
        
        b2 = new JButton(i1);
        i2 = new ImageIcon("savings.jpg");
        temp1 = i2.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i2 = new ImageIcon(temp2);
        b2 = new JButton(i2);
        b2.addActionListener(this);
        b2.setToolTipText("Self Transfer");

        b3 = new JButton(i3);
        i3 = new ImageIcon("chkbal.jpg");
        temp1 = i3.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i3 = new ImageIcon(temp2);
        b3 = new JButton(i3);
        b3.addActionListener(this);
        b3.setToolTipText("Check Balance");

        b4 = new JButton("Send to UPI");
        b4.addActionListener(this);
        b4.setToolTipText("UPI Transfer");
        
        b1.setBounds(10,10,190,40);
        b2.setBounds(220,10,190,40);
        b3.setBounds(10,60,190,40);
        b4.setBounds(220,60,190,40);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);

        p1.setBounds(20,110,420,110);
        
        //Panel 2 
        t2 = new JLabel("Recharge & Pay Bills ");
        t2.setBounds(20,220,400,50);
        t2.setForeground(Color.WHITE);
        p2.setLayout(null);
        p2.setBackground(new Color(176, 122, 236, 125));


        b5= new JButton(i5);
        i5 = new ImageIcon("mobile.jpg");
        temp1 = i5.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i5 = new ImageIcon(temp2);
        b5 = new JButton(i5);
        b5.addActionListener(this);
        b5.setToolTipText("Mobile Recharge");

        b6 = new JButton(i6);
        i6 = new ImageIcon("bulb.jpg");
        temp1 = i6.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i6 = new ImageIcon(temp2);
        b6 = new JButton(i6);
        b6.addActionListener(this);
        b6.setToolTipText("ElectiCity Bill");

        b7 = new JButton(i7);
        i7 = new ImageIcon("cc.jpg");
        temp1 = i7.getImage();
        temp2 = temp1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        i7 = new ImageIcon(temp2);
        b7 = new JButton(i7);
        b7.addActionListener(this);
        b7.setToolTipText("Credit Card");

        b8= new JButton("DTH bill");
        b8.addActionListener(this);
        b8.setToolTipText("DTH Bill");

        b5.setBounds(10,10,190,40);
        b6.setBounds(220,10,190,40);
        b7.setBounds(10,60,190,40);
        b8.setBounds(220,60,190,40);

        p2.add(b5);
        p2.add(b6);
        p2.add(b7);
        p2.add(b8);
        
        p2.setBounds(20,260,420,110);


        l1 = new JLabel("DASHBOARD ");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Verdana", Font.BOLD, 40));

        
        logout.setBounds(390,2,90,22);
        l1.setBounds(120,20,320,50);

        
        backLable.add(logout);
        backLable.add(l1);
        backLable.add(t1);
        backLable.add(p1);
        backLable.add(t2);
        backLable.add(p2);

        //add(t3);
        frame.add(backLable);


        frame.setTitle("Home-Page");
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(500,500));
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            String q="select pin,lamo from regtab where acc_num="+acc_num;
            //String q="select email,pass from log";
            Class.forName("com.mysql.cj.jdbc.Driver");
            // con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");

            st=con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next())
            {
                pin=rs.getInt(1);
                balamo=rs.getInt(2);
            }
 

        }
        catch(Exception a)
        {
            System.out.println(a);
        }

        if(e.getSource() == logout )
        {
             frame.setVisible(false);
             tab=0;
            
        }
        else if(e.getSource()==b1)
        {
            frame.dispose();
            BankTrans obj = new BankTrans(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(204, 107, 199, 203));
            // obj.setVisible(true);
        }
        else if(e.getSource()==b2)
        {
            frame.dispose();
            ToSelfTrans obj = new ToSelfTrans(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);
        }
        else if(e.getSource()==b3)
        {
            frame.dispose();
            CheckBal obj = new CheckBal(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);
        }

        else if(e.getSource()==b4)
        {
            frame.dispose();
            UPI obj=new UPI(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);
        }
        else if(e.getSource()==b5)
        {
            frame.dispose();
            MRecharge obj = new MRecharge(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            
        }
        else if(e.getSource()==b6)
        {
            frame.dispose();
            Elec obj = new Elec(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);frame.
        }
        else if(e.getSource()==b7)
        {
            frame.dispose();
            CC obj = new CC(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);

        }
        else if(e.getSource()==b8)
        {
            frame.dispose();
            DTH obj = new DTH(acc_num,pin,balamo);
            //obj.getContentPane().setBackground(new Color(21, 112, 208, 203));
            // obj.setVisible(true);


        }
    }
    
}


// else if(e.getSource() == b1)
// {
    // accBal obj = new accBal();
    // obj.setVisible(true);
// }