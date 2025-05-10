/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univ.DAO;

import com.univ.DB.DBConnection;
import com.univ.DTO.customerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author IT
 */
public class customerDAO {
    public int insert(customerDTO data)
{
    int x=0;
    Connection con1=null;
    try{
        con1=DBConnection.getConn();
        PreparedStatement ps=con1.prepareStatement("insert into customer (customer_name,mob,type) value(?,?,?)");
        ps.setString(1,data.getCustomer_name());
        ps.setInt(2,data.getMob());
        ps.setInt(3,data.getType());
        x=ps.executeUpdate();
    
    }catch(Exception ee)
    {
    System.out.println(ee);
    }
    return x;
}
    
}
