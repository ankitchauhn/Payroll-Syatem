package payroll_project;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class list_attendence extends JFrame implements ActionListener
{
    JTable t;
    JButton bt1;
    String x[]={"Employ Id","First Half","Second Half","Date"};
    String y[][]= new String[20][4];
    int i=0,j=0;
    Font f;
    list_attendence()
    {
        super("List Attendence");
        setSize(900,400);
        setLocation(100,100);
        setResizable(false);
        
        f=new Font("arial",Font.BOLD,14);
        
        try
        {
           ConnectionClass obj=new ConnectionClass();
           String q="Select * from attendence";
           ResultSet rest=obj.stm.executeQuery(q);
           while(rest.next())
           {
               y[i][j++]=rest.getString("Eid");
               y[i][j++]=rest.getString("first_half");
               y[i][j++]=rest.getString("second_half");
               y[i][j++]=rest.getString("date");
               i++;
               j=0;
           }
           t=new JTable(y,x);
           t.setFont(f);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        JScrollPane js=new JScrollPane(t);
        add(js);
        
        bt1=new JButton("print");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.setFont(f);
        add(bt1,"South");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1)
        {
            try
            {
                t.print();
            }
            catch(Exception ex)
            {
               ex.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        new list_attendence().setVisible(true);
    }
}
