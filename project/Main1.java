//Main Module 
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import myPack.*;
//import myPack.register;

class Main1 extends JFrame implements ActionListener {
    JPanel heading, login;
    ImageIcon backgroundImg;
    JLabel background;
    JTextField uname;
    JPasswordField pass;
    int acc_num;
    JLabel lg, pw;
    JButton log, reg;
    JLabel binfo;
    String u1 = "V", p1 = "v4", u2, p2;
    Connection con;
    Statement st;
    Boolean em,ps;
    

    Main1() {
        //font
        Font f = new Font("Algerian", Font.BOLD, 30);
        Font tf = new Font("Times New Roman", Font.BOLD, 18);
        Font lf = new Font("Times New Roman", Font.BOLD, 14);

        //header
        //   JPanel heading ;
        heading = new JPanel();
        heading.setBackground(new Color(0, 0, 0, 80));
        heading.setBounds(0, 0, 900, 100);
        JLabel name = new JLabel("National Bank");
        name.setForeground(Color.WHITE);
        name.setFont(f);
        heading.add(name);

        //Login Panel 
        // JPanel login ;
        login = new JPanel();
        login.setLayout(null);
        login.setBackground(new Color(0, 0, 0, 60));
        login.setBounds(50, 175, 400, 350);
        lg = new JLabel("Enter Username");
        lg.setForeground(Color.WHITE);
        lg.setBounds(50, 30, 300, 10);
        lg.setFont(lf);

        uname = new JTextField(70);
        uname.setBounds(50, 50, 300, 50);
        uname.setBackground(new Color(10, 10, 10));
        uname.setForeground(Color.WHITE);
        uname.setFont(tf);

        pw = new JLabel("Enter Password");
        pw.setForeground(Color.WHITE);
        pw.setBounds(50, 130, 300, 10);
        pw.setFont(lf);

        pass = new JPasswordField(70);
        pass.setBounds(50, 150, 300, 50);
        pass.setBackground(new Color(10, 10, 10));
        pass.setForeground(Color.WHITE);
        pass.setFont(tf);
        pass.addActionListener(this);

        reg = new JButton("Register ");
        reg.setBounds(50, 250, 100, 50);
        reg.setBackground(new Color(104, 255, 230));
        reg.addActionListener(this);

        log = new JButton("Login ");
        log.setBounds(200, 250, 100, 50);
        log.setBackground(new Color(104, 255, 230));
        log.addActionListener(this);

        login.add(uname);
        login.add(lg);
        login.add(pw);
        login.add(pass);
        login.add(reg);
        login.add(log);

        //frame
        setSize(900, 600);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //background
        backgroundImg = new ImageIcon("bg2.jpg");

        Image img = backgroundImg.getImage();
        Image temp_img = img.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(temp_img);
        background = new JLabel(" ", backgroundImg, JLabel.CENTER);

        background.add(heading);
        background.add(login);
        background.setBounds(0, 0, 900, 600);
        add(background);

/*
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int wd=getWidth();
            int hg=getHeight();
            background = new JLabel(" ", backgroundImg, JLabel.CENTER);
            //System.out.println(wd+"  "+hg);
            background.setBounds(0,0,wd,hg);
            }
        });

*/

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == log || e.getSource()==pass)
        {
            String ema=uname.getText();
            String pas=pass.getText();
            
            try
            {
                
                String q="select username,pass,acc_num from regtab";
                Class.forName("com.mysql.cj.jdbc.Driver");
                //con=DriverManager.getConnection("jdbc:mysql://localhost:3308/login","root","viresh@123");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_test","root","N@vi0404");
                st=con.createStatement();
                ResultSet rs = st.executeQuery(q);
                while(rs.next()){
                if(ema.equals(rs.getString(1)))
                {
                    em=true;
                    if(pas.equals(rs.getString(2)))
                    {  
                        em=true;
                        ps=true;
                        acc_num=rs.getInt(3);
                        break;
                    }
                    else
                    {
                        em=true;
                        ps=false;
                        break;
                    }
                }
                else
                {
                    em=false;
                }
            }
            if(em==true)
            {
                if(ps==true)
                {
                    JOptionPane.showMessageDialog(this,"Login Successful");
                    homePage obj = new homePage(acc_num);
                    // obj.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(this,"Wrong Password");
            }
            else
                JOptionPane.showMessageDialog(this,"Incorrect Email OR Password ");
                st.close();
            }
            catch(Exception v)
            {
                System.out.println(v);
            }

        }

         if(e.getSource()==reg)
         {
            register r = new register();
            r.setVisible(true);
         }

}
    public static void main(String[] args) {
        Main1 obj = new Main1();
        obj.setVisible(true);
    }
}

 