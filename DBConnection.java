/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DB;
import java.sql.*;

public class DBConnection implements DBConfig{
    public static Connection getConn()
    {
        Connection con=null;
        try
        {
            Class.forName(Driver);
            con=DriverManager.getConnection(Url,Unm,Pw);
        
        }catch(Exception ee)
        {
        System.out.print(ee);
        }
        return con;
    }
}
