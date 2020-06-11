package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login_page extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4;
    JButton bt1,bt2;
    JTextField tf1;
    JPasswordField pf;
    JPanel p1,p2;
    Font f;
    
    login_page()
    {
        super("login page");
        setSize(380,180);
        setLocation(500,200);
        setResizable(false);
//        setUndecorated(true);
        f=new Font("arial",Font.BOLD,14);
        
        l1=new JLabel("UserName");
        l2=new JLabel("Psaaword");
        
        l1.setFont(f);
        l2.setFont(f);
        
        tf1=new JTextField();
        
        tf1.setFont(f);
        
        pf=new JPasswordField();
        
        pf.setFont(f);
        
        bt1=new JButton("Login");
        bt2=new JButton("Cancle");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt1.setFont(f);
        bt2.setFont(f);
        
        p1=new JPanel();
        p1.setLayout(new GridLayout(3,2,10,10));
        
        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(pf);
        p1.add(bt1);
        p1.add(bt2);
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("payroll_project/icons/login 2.png"));
        Image image=img.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT);
        ImageIcon img1=new ImageIcon(image);
        l3=new JLabel(img1);
        
        setLayout(new BorderLayout(30,30));
        
        add(l3,"West");
        add(p1,"Center");
        
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1)
        {
            String u_name=tf1.getText();
            String p_name=pf.getText();
            try
            {
                ConnectionClass obj=new ConnectionClass();
                String q="select * from login where username='"+u_name+"' and password='"+p_name+"'";
                ResultSet res=obj.stm.executeQuery(q);
                if(res.next())
                {
                    new home_payroll().setVisible(true);
                    this.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"invalid username or password");
                    this.setVisible(false);
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==bt2)
        {
            JOptionPane.showMessageDialog(null,"Are you sure");
            setVisible(false);
        }
        
    }
    public static void main(String args[])
    {
        new login_page().setVisible(true);
    }
}
