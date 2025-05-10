/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DAO;

import com.univ.DB.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author IT
 */
public class reportDAO {
    
    public int getStock(int id)
    {
        int stk=0;
        ResultSet rs1=null;
        Connection con2=null;
         try{
              con2=DBConnection.getConn();
              Statement stmt=con2.createStatement();
              rs1= stmt.executeQuery("select qty from stock where product_id="+id);
              while(rs1.next())
              {
                   stk=rs1.getInt(1);
              }
            }catch(Exception tt)
            {
                System.out.println(tt);
            }
    return stk;
    }//get stock close
    public ResultSet getpurchase(int y)
    {
        ResultSet rs1=null;
        Connection con=null;
        try
        {
        con=DBConnection.getConn();
        Statement stm=con.createStatement();
        rs1=stm.executeQuery("select qty ,purchase_date from purchase where product_id="+y);
        }
        catch(Exception ee)
        {
            System.out.println(ee);
        }
    return rs1;
    }//getpurchase close
    
        public ResultSet getsale(int y)
    {
        ResultSet rs1=null;
        Connection con=null;
        try
        {
        con=DBConnection.getConn();
        Statement stm=con.createStatement();
        rs1=stm.executeQuery("select qty ,sale_date from sales where product_id="+y);
        }
        catch(Exception ee)
        {
            System.out.println(ee);
        }
    return rs1;
    }
    
}
