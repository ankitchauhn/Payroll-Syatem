package payroll_project;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class list_employee extends JFrame implements ActionListener
{
    JTable tb;
    String x[]={"Eid","name","gender","address","state","city","email","phone"};
    String y[][]=new String[20][8];
    JButton bt1;
    int i=0;
    int j=0;
    Font f;
    list_employee()
    {
        super("List Employe");
        setSize(900,400);
        setLocation(100,100);
        setResizable(false);
        
        f=new Font("Arial",Font.BOLD,14);
        
        try
        {
            ConnectionClass obj=new ConnectionClass();
            String q="select * from new_employee";
            ResultSet rest=obj.stm.executeQuery(q);
            while(rest.next())
            {
                y[i][j++]=rest.getString("Eid");
                y[i][j++]=rest.getString("name");
                y[i][j++]=rest.getString("gender");
                y[i][j++]=rest.getString("address");
                y[i][j++]=rest.getString("state");
                y[i][j++]=rest.getString("city");
                y[i][j++]=rest.getString("email");
                y[i][j++]=rest.getString("phone");
                i++;
                j=0;
            }
            tb=new JTable(y,x);
            tb.setFont(f);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        JScrollPane js=new JScrollPane(tb);
        add(js);
        bt1=new JButton("print");
        add(bt1,"South");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);
        bt1.setFont(f);
        
//        add(bt1,"South");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1)
        {
            try
            {
                tb.print();
            }
            catch(Exception exx)
            {
                exx.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        new list_employee().setVisible(true);
    }
}
