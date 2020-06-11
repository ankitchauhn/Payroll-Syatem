package payroll_project;

import java.sql.*;
public class ConnectionClass 
{
    Connection con;
    Statement stm;
    ConnectionClass()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("Jdbc:mysql://localhost:330/payroll","root","ankit@123");
            stm=con.createStatement();
            if(con.isClosed())
            {
                System.out.println("connection closed");
            }
            else
            {
                System.out.println("connection create");
            }    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        new ConnectionClass();
    }
}
