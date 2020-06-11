package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class take_attendence extends JFrame implements ActionListener 
{
    JLabel l1,l2,l3,l4;
    JButton bt1;
    Choice ch1,ch2,ch3;
    Font f;
    JPanel p1;
    take_attendence()
    {
        super("Take Attendence");
        setSize(290,290);
        setLocation(100,100);
        setResizable(false);
        
        f=new Font("Arial",Font.BOLD,14);
        
        l1=new JLabel("First Half");
        l2=new JLabel("Second Half");
        l3=new JLabel("Employe ID");
        
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        
        ch1=new Choice();
        ch1.add("Present");
        ch1.add("Absent");
        
        ch1.setFont(f);
        
        ch2=new Choice();
        ch2.add("Present");
        ch2.add("Absent");
        
        ch2.setFont(f);
        
        ch3=new Choice();
        try
        {
            ConnectionClass obj=new ConnectionClass();
            String q2="select Eid from new_employee";
            ResultSet rest2=obj.stm.executeQuery(q2);
            while(rest2.next())
            {
                ch3.add(rest2.getString("Eid"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ch3.setFont(f);
        
        bt1=new JButton("Submit");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);
        
        bt1.setFont(f);
        
        p1=new JPanel();
        p1.setLayout(new GridLayout(3,2,10,10));
        p1.add(l3);
        p1.add(ch3);
        p1.add(l1);
        p1.add(ch1);
        p1.add(l2);
        p1.add(ch2);
        
        setLayout(new BorderLayout(30,30));
        add(p1,"Center");
        add(bt1,"South");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1)
        {
            try
            {
                int id=Integer.parseInt(ch3.getSelectedItem());
                String first_half=ch1.getSelectedItem();
                String second_half=ch2.getSelectedItem();
                String dt=new java.util.Date().toString();

                ConnectionClass obj=new ConnectionClass();
                String q="insert into attendence values('"+id+"','"+first_half+"','"+second_half+"','"+dt+"')";
                obj.stm.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Record insert");
                setVisible(false);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        new take_attendence().setVisible(true);
    }
}
