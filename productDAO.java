/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DAO;

import com.univ.DB.DBConnection;
import com.univ.DTO.productDTO;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author IT
 */
public class productDAO {
    public int insert(productDTO data)
{
    int x=0;
    Connection con1=null;
    try{
        con1=DBConnection.getConn();
        Statement stmt=con1.createStatement();
        int prid=0;
        ResultSet rs=stmt.executeQuery("select product_id from product");
        while(rs.next())
        {
            prid=rs.getInt(1);
        }
        PreparedStatement ps=con1.prepareStatement("insert into product (product_name,purchase_price,sale_price) value(?,?,?)");
        ps.setString(1,data.getProduct_name());
        ps.setInt(2,data.getPurchase_price());
        ps.setInt(3,data.getSale_price());
        x=ps.executeUpdate();
        
        if(x==1)
        {
            prid = prid +1;
            stmt.executeUpdate("insert into stock(product_id,qty) values("+prid+",0)");
            
        }
    
    }catch(Exception ee)
    {
    System.out.println(ee);
    }
    return x;
}//insert close
    
}
