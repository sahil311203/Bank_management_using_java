package myPack;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 

public class CheckBal extends JFrame implements ActionListener
{
    JButton back,Cbal,mSte;
    JLabel l1 ;
    int ac_num,pin,cpin;
    long amo;
    Panel p;
    JScrollPane js;
    JTable jb;
    String upid;
    Connection conn;
    PreparedStatement ps;

    JFrame frame = new JFrame();
    public CheckBal(int num,int pi,long am)
    {
        ac_num=num;
        pin=pi;
        amo=am;
        
        back = new JButton("Back");
        back.addActionListener(this);

        Cbal=new JButton("Check Balance");
        mSte=new JButton("Mini Statement");
        Cbal.addActionListener(this);
        mSte.addActionListener(this);

        Cbal.setBounds(120,80,100,20);
        mSte.setBounds(250,80,100,20);
        
        l1 = new JLabel("Check Bank Transactions");
        l1.setFont(new Font("Verdana", Font.BOLD, 20));        
        back.setBounds(10,2,90,22);
        l1.setBounds(100,20,320,50);
                
        p=new Panel();
        p.setLayout(new BorderLayout());
        jb=new JTable();
        js=new JScrollPane(jb);

        frame.add(back);
        frame.add(l1);
        frame.add(Cbal);
        frame.add(mSte);
        p.setBounds(50,120,400,100);
        p.add(js);
        frame.add(p);

        
        frame.setSize(500,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(21, 112, 208, 203));
        // frame.setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
        p.setVisible(false);
        
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back )
        {
            //setVisible(false);
            frame.dispose();
            homePage obj = new homePage(ac_num);
        }
        if(e.getSource()==Cbal)
        {
            cpin=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Pin : "));
            if(cpin==pin)
            {
                JOptionPane.showMessageDialog(this,"Your Bank Balance is: "+amo);
            }
            else
                JOptionPane.showMessageDialog(this,"plz Enter valid pin");
            // num = t1.getText();
            // pin = t2.getText();
            // int x = Integer.parseInt(pin);
            // if(pi==x)
            // {
            // l5.setText(num);
            // l6.setText("5000");
            // }
            // repaint();
        }

        if(e.getSource()==mSte)
        {
            cpin=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Pin : "));
            if(cpin==pin)
            {
                p.setVisible(true);                
                try
                {
                    String q="select * from banktan where acc_num="+ac_num;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //conn=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                    ps = conn.prepareStatement(q);
                    ResultSet rs = ps.executeQuery();
                    ResultSetMetaData rsmd=rs.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) jb.getModel();
                    int cols=rsmd.getColumnCount();
                    String[] ColName=new String[cols];
                    for(int i=0;i<cols;i++)
                        ColName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(ColName);
                    String coldata[];
                    coldata =new String[cols];
                    int x=1;
                    while(rs.next())
                    {
                        x=1;
                        for(int g=0;g<cols;g++)
                        {
                            coldata[g]=rs.getString(x);
                            x++;
                        }
                        model.addRow(coldata);
                    }
                   
                    
                }
                catch(Exception a)
                {
                    System.out.println(a);
                }
            }
            else
                JOptionPane.showMessageDialog(this,"plz Enter valid pin");            
        }
    }

}