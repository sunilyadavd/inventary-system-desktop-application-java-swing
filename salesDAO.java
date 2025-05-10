/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DAO;

import com.univ.DB.DBConnection;
import com.univ.DTO.salesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author IT
 */
public class salesDAO {
       public int insert(salesDTO data)
{
    int x=0;
    Connection con1=null;
    try{
        con1=DBConnection.getConn();
        PreparedStatement ps=con1.prepareStatement
        ("insert into sales(inv_no, customer_id, product_id, qty, total_amount,sale_date) value(?,?,?,?,?,?)");
        ps.setString(1,data.getInv_no());
        ps.setInt(2,data.getCustomer_id());
        ps.setInt(3,data.getProduct_id());
        ps.setInt(4,data.getQuantity());
        ps.setInt(5,data.getTotal_amount());
        ps.setString(6,data.getSale_date());
        x=ps.executeUpdate();
   
    
    }catch(Exception ee)
    {
    System.out.println(ee);
    }
    return x;
}

    public String getInvNo()
    {
    String invno="";
    try{
        Connection con1=DBConnection.getConn();
        Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select inv_no from sales");
        while(rs.next())
        {
           invno=rs.getString(1);
        }
        if(invno.isEmpty())
        {
            invno="ind-101";
        }else
        {
            String[] s=new String[2];
            s=invno.split("-");
            int x=Integer.parseInt(s[1]);
            x=x+1;
            invno="ind-"+x;
            
        }
    
        }catch(Exception ee)
        {
        System.out.println(ee);
        }
    return invno;
    }//get inv no closed
    public List getproduct()
    {
        List lst=new ArrayList();
        Vector v1=new Vector();
        Vector v2=new Vector();
        Vector v3=new Vector();
        try{
            
        Connection con1=DBConnection.getConn();
        Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select product_id,product_name,purchase_price from product");
        
        while(rs.next())
        {
            v1.addElement(rs.getInt(1));
            v2.addElement(rs.getString(2));
            v3.addElement(rs.getInt(3));
        }
        lst.add(v1);
        lst.add(v2);
        lst.add(v3);
   
    
         }catch(Exception ee)
         {
             System.out.println(ee);
         }
    return lst;
    }// get product closed
    
  //get customer
    public List getCustomer()
    {
        List lst1=new ArrayList();
        Vector c1=new Vector();
        Vector c2=new Vector();
        try
        {
             Connection con1=DBConnection.getConn();
        Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select customer_id,customer_name from customer where type=2");
        
        while(rs.next())
        {
            c1.addElement(rs.getInt(1));
            c2.addElement(rs.getString(2));
        }
        lst1.add(c1);
        lst1.add(c2);
        }catch(Exception ee)
        {
            System.out.println(ee);
        }
    return lst1;
    }//get customer closed
    
}
