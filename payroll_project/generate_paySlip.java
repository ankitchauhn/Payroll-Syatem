package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class generate_paySlip extends JFrame implements ActionListener
{
    JButton bt1;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextArea ta;
    Choice ch;
    JPanel p1;
    Font f;
    generate_paySlip()
    {
        super("Generate Pay Slip");
        setSize(500,500);
        setLocation(100,100);
        setResizable(false);
        
        f=new Font("arial",Font.BOLD,16);
        
        l2=new JLabel("Employe Id ");
        
        l2.setFont(f);
        ch=new Choice();
        try
        {
            ConnectionClass obj=new ConnectionClass();
            String q="select * from new_employee";
            ResultSet rest=obj.stm.executeQuery(q);
            while(rest.next())
            {
                ch.add(rest.getString("Eid"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        bt1=new JButton("Print");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);
        bt1.setFont(f);
        
        ta=new JTextArea();
        JScrollPane sp=new JScrollPane(ta);
        ta.setEditable(false);
        ta.setFont(f);
        
        p1=new JPanel();
        p1.setLayout(new GridLayout(1,3,10,10));
        p1.add(l2);
        p1.add(ch);
        p1.add(bt1);
        setLayout(new BorderLayout());
        add(sp,"Center");
        add(p1,"South");
    }
    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource()==bt1)
        {
            ta.setText("----------Pay Slip----------");
            try
            {
                ConnectionClass obj1=new ConnectionClass();
                int id=Integer.parseInt(ch.getSelectedItem());
                String q1="select * from new_employee where Eid='"+id+"'";
                ResultSet rest1=obj1.stm.executeQuery(q1);
                while(rest1.next())
                {
                    ta.append("\n\nEmploye Id : "+Integer.parseInt(rest1.getString("Eid")));
                    ta.append("\nEmploye Name : "+rest1.getString("name"));
                    ta.append("\n------------------------------------\n\n");
                }
                String q2="select * from salery where Eid='"+id+"'";
                ResultSet rest2=obj1.stm.executeQuery(q2);
                while(rest2.next())
                {
                    ta.append("\nHRA : "+Float.parseFloat(rest2.getString("hra")));
                    ta.append("\nDA : "+Float.parseFloat(rest2.getString("da")));
                    ta.append("\nMED : "+Float.parseFloat(rest2.getString("mid")));
                    ta.append("\nPF : "+Float.parseFloat(rest2.getString("pf")));
                    ta.append("\nBASIC SALERY : "+Float.parseFloat(rest2.getString("basic")));
                    ta.append("\n------------------------------------\n\n");
                    float gross_salery=Float.parseFloat(rest2.getString("hra"))+Float.parseFloat(rest2.getString("da"))+Float.parseFloat(rest2.getString("mid"))+Float.parseFloat(rest2.getString("pf"))+Float.parseFloat(rest2.getString("basic"));
                    double tax=(gross_salery*2.1)/100;
                    ta.append("\nGross Salery : "+gross_salery);
                    ta.append("\nTotal : "+gross_salery);
                    ta.append("\nTax 2.1% of salery : "+tax);
                }
            }
            catch(Exception exx)
            {
              exx.printStackTrace();
            }
        }
    }
    public static void main(String arge[])
    {
        new generate_paySlip().setVisible(true);
    }
}
