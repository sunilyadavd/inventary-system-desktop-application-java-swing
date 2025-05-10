/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DAO;

import com.univ.DB.DBConnection;
import com.univ.DTO.purchaseDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author IT
 */
public class purchaseDAO {
    
    public String getInvNo()
    {
    String invno="";
    try{
        Connection con1=DBConnection.getConn();
        Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select inv_no from purchase");
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
        ResultSet rs= stm.executeQuery("select customer_id,customer_name from customer where type=1");
        
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
    
    public int insert(purchaseDTO data)
{
    int x=0;
    Connection con1=null;
    try{
        con1=DBConnection.getConn();
        PreparedStatement ps=con1.prepareStatement
        ("insert into purchase(inv_no, customer_id, product_id, qty, total_amount,purchase_date) value(?,?,?,?,?,?)");
        ps.setString(1,data.getInv_no());
        ps.setInt(2,data.getCustomer_id());
        ps.setInt(3,data.getProduct_id());
        ps.setInt(4,data.getQuantity());
        ps.setInt(5,data.getTotal_amount());
        ps.setString(6,data.getPurchase_date());
        x=ps.executeUpdate();
        if(x==1)
        {
            Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select qty from stock where product_id="+data.getProduct_id());
        int old=0;
        if(rs.next())
        {
            old=rs.getInt(1);
        }
        int nw=old+data.getQuantity();
        ps=con1.prepareStatement("update stock set qty=? where product_id="+data.getProduct_id());
        ps.setInt(1,nw);
        int n=ps.executeUpdate();
        if(n==1)
        {
            System.out.println("quantity added in stock also");
        }
        }
    
    }catch(Exception ee)
    {
    System.out.println(ee);
    }
    return x;
}
    
     public List getproductreport()
    {
        List lst=new ArrayList();
        Vector v1=new Vector();
        Vector v2=new Vector();
        try{
            
        Connection con1=DBConnection.getConn();
        Statement stm=con1.createStatement();
        ResultSet rs= stm.executeQuery("select product_id,product_name from product");
        
        while(rs.next())
        {
            v1.addElement(rs.getInt(1));
            v2.addElement(rs.getString(2));
        }
        lst.add(v1);
        lst.add(v2);
   
    
         }catch(Exception ee)
         {
             System.out.println(ee);
         }
    return lst;
    }
}
